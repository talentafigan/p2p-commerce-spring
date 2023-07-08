package p2p.commerce.commerceapi.web.serviceImpl;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import p2p.commerce.commerceapi.configuration.data.AuthenticationFacade;
import p2p.commerce.commerceapi.configuration.exception.BussinesException;
import p2p.commerce.commerceapi.web.dto.ChatRequest;
import p2p.commerce.commerceapi.web.model.ChatMessages;
import p2p.commerce.commerceapi.web.model.Users;
import p2p.commerce.commerceapi.web.repository.*;
import p2p.commerce.commerceapi.web.service.ChatService;

import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class ChatServiceImpl implements ChatService {

    private ModelMapper modelMapper;
    private AuthenticationFacade authenticationFacade;
    private StatusRepository statusRepository;
    private AdminRepository adminRepository;
    private ClientRepository clientRepository;
    private ConsultationRepository consultationRepository;
    private ChatRepository chatRepository;


    @Transactional(readOnly = true)
    @Override
    public List<ChatMessages> findChatMessage(String conversationId) {
        if (!consultationRepository.existsByConversationIdAndStatus(conversationId, statusRepository.findById(1).get())) throw new BussinesException("Conversation NOT FOUND");
        return chatRepository.findAllByConversationId(conversationId);
    }

    @Transactional
    @Override
    public ChatMessages postChatMessage(ChatRequest chatRequest) {
        if (!consultationRepository.existsByConversationIdAndStatus(chatRequest.getConversationId(), statusRepository.findById(1).get())) throw new BussinesException("Illegal Conversation");
        Users user = authenticationFacade.getAuthentication();
        ChatMessages chat = modelMapper.map(chatRequest, ChatMessages.class);
        chat.setCreator(user);
        chat.setStatus(statusRepository.findById(1).get());
        ChatMessages charResp = chatRepository.save(chat);
        if (user.getUserType().getUserTypeName().equals("Admin")) {
            charResp.setCreators(adminRepository.findByUser(user));
        } else  {
            charResp.setCreators(clientRepository.findByUser(user));
        }
        return charResp;
    }
}
