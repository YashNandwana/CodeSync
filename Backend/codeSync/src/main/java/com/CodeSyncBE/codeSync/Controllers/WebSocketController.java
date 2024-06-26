package com.CodeSyncBE.codeSync.Controllers;

import com.CodeSyncBE.codeSync.Models.Code;
import com.CodeSyncBE.codeSync.Models.Message;
import com.CodeSyncBE.codeSync.Models.WebRtcPayload;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.json.JSONObject;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.RestController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

@RestController
public class WebSocketController {
    private final SimpMessagingTemplate messagingTemplate;

    public WebSocketController(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }
    private static final Logger logger = LoggerFactory.getLogger(WebSocketController.class);
    List<Code> recievedPayloads = new ArrayList<>();

    @MessageMapping("/update-code")
    public void handleCodeUpdate(@Payload Code payload) {
        String roomId = payload.getRoomId();
        String username = payload.getUsername();
        String code = payload.getCode();
        String messageId = payload.getMessageId();
        recievedPayloads.add(payload);
        logger.info(roomId);
        logger.info(code);
        String destination = "/editor/code-updates/" + roomId;
        messagingTemplate.convertAndSend(destination, code);
    }

    @MessageMapping("/chat-messages")
    public void handleChatMessages(@Payload Message payload) {
        String roomId = payload.getRoomId();
        String username = payload.getUsername();
        String message = payload.getMessage();
        String destination = "/chatroom/messages/" + roomId;
        messagingTemplate.convertAndSend(destination, message);

    }
}
