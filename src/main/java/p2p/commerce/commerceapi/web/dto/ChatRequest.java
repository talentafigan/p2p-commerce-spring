package p2p.commerce.commerceapi.web.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChatRequest {
    private String content;
    private String attachment;
    private String conversationId;
}
