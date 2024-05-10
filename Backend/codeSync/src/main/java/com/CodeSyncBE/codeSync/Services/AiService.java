package com.CodeSyncBE.codeSync.Services;

import com.CodeSyncBE.codeSync.Models.AI;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class AiService {

    @Value("${openai.chatgtp.api.key}")
    private String openAiApiKey;

    public Mono<String> callOpenAIEndpoint(AI chatRequest) {
        // Create WebClient instance
        WebClient webClient = WebClient.create();

        // Convert ChatRequest to JSON string
        String requestBody;
        try {
            requestBody = new ObjectMapper().writeValueAsString(chatRequest);
        } catch (Exception e) {
            return Mono.error(e);
        }

        // Make POST request
        return webClient.post()
                .uri("https://api.openai.com/v1/chat/completions")
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + openAiApiKey)
                .body(BodyInserters.fromValue(requestBody))
                .retrieve()
                .bodyToMono(String.class);
    }
}
