package com.CodeSyncBE.codeSync.Controllers;

import com.CodeSyncBE.codeSync.models.Code;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
public class WebSocketController {
    private final SimpMessagingTemplate messagingTemplate;

    public WebSocketController(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }
    private static final Logger logger = LoggerFactory.getLogger(WebSocketController.class);
    @MessageMapping("/update-code")
    public void handleCodeUpdate(@Payload Code payload) {
        String roomId = payload.getRoomId();
        String username = payload.getUsername();
        String code = payload.getCode();
        logger.info(roomId);
        logger.info(code);
        String destination = "/editor/code-updates/" + roomId;
        messagingTemplate.convertAndSend(destination, code);
    }
}
