package p2p.commerce.commerceapi.web.controller;

import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import p2p.commerce.commerceapi.configuration.response.CommonResponse;
import p2p.commerce.commerceapi.configuration.response.ResponseHelper;
import p2p.commerce.commerceapi.web.dto.ChatRequest;
import p2p.commerce.commerceapi.web.model.ChatMessages;
import p2p.commerce.commerceapi.web.service.ChatService;

import java.util.List;

@RestController
@RequestMapping("/api/chat-message")
@AllArgsConstructor
public class ChatController {
    private ChatService chatService;

    @GetMapping("/{conversationId}")
    public CommonResponse<List<ChatMessages>> findChat(@PathVariable("conversationId")String conversationId) {
        return ResponseHelper.ok(chatService.findChatMessage(conversationId));
    }

    @PreAuthorize("hasAnyAuthority('Admin', 'Client')")
    @PostMapping
    public CommonResponse<ChatMessages> postChat(@RequestBody ChatRequest chatRequest) {
        return ResponseHelper.ok(chatService.postChatMessage(chatRequest));
    }
}
