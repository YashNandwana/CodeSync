package com.CodeSyncBE.codeSync.Controllers;

import com.CodeSyncBE.codeSync.Models.AI;
import com.CodeSyncBE.codeSync.Services.AiService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/ai")
public class AiHelpController {

    private static final Logger logger = LoggerFactory.getLogger(WebSocketController.class);
    private final AiService aiService;

    @Autowired
    public AiHelpController(AiService aiService) {
        this.aiService = aiService;
    }

    @GetMapping("/help")
    public Mono<String> openAIChat(@RequestBody AI aiRequest) {
//        String requestBody = new ObjectMapper().writeValueAsString(aiRequest);
        logger.info("**********************");
        logger.info(String.valueOf(aiRequest));
        return aiService.callOpenAIEndpoint(aiRequest);
    }
}
