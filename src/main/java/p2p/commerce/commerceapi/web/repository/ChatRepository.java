package p2p.commerce.commerceapi.web.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import p2p.commerce.commerceapi.web.model.ChatMessages;

import java.util.List;

@Repository
public interface ChatRepository extends JpaRepository<ChatMessages, Integer> {
    List<ChatMessages> findAllByConversationId(String conversationId);
}
