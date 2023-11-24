package com.CodeSyncBE.codeSync.Controllers;

import com.CodeSyncBE.codeSync.models.Code;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WebSocketController {
    @MessageMapping("/update-code")
    @SendTo("/topic/code-updates")
    public String handleCodeUpdate(@RequestBody String code) {
        return code;
    }
}
