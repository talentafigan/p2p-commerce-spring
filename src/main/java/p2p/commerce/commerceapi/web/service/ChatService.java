package p2p.commerce.commerceapi.web.service;


import p2p.commerce.commerceapi.web.dto.ChatRequest;
import p2p.commerce.commerceapi.web.model.ChatMessages;

import java.util.List;

public interface ChatService {
    List<ChatMessages> findChatMessage(String conversationId);
    ChatMessages postChatMessage(ChatRequest chatRequest);
}
