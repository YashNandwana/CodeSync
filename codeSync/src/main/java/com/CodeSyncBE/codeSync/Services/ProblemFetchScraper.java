package com.CodeSyncBE.codeSync.Services;

import com.CodeSyncBE.codeSync.Controllers.WebSocketController;
import com.CodeSyncBE.codeSync.Models.ProblemData;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ProblemFetchScraper {
    private static final Logger logger = LoggerFactory.getLogger(WebSocketController.class);
    public ProblemData scrapeLeetCodeProblem(String problemUrl) throws Exception {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(problemUrl))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        Thread.sleep(5000);
        // Log the exact HTML content
        String htmlContent = response.body();
        Document doc = Jsoup.parse(htmlContent);
        logger.info(String.valueOf(doc));

        String title = doc.title();

        Element descriptionElement = doc.select("meta[name=description]").first();
        String description = (descriptionElement != null) ? descriptionElement.attr("content") : "";
        logger.info(description);
        ProblemData problemData = new ProblemData();
        problemData.setTitle(title);
        problemData.setDescription(description);

        return problemData;
    }
}
