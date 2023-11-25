package com.CodeSyncBE.codeSync.Controllers;

import com.CodeSyncBE.codeSync.models.Code;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
public class WebSocketController {
    private static final Logger logger = LoggerFactory.getLogger(WebSocketController.class);
    @MessageMapping("/update-code")
    @SendTo("/editor/code-updates")
    public String handleCodeUpdate(String code) {
        logger.info(code);
        return code;
    }
}
