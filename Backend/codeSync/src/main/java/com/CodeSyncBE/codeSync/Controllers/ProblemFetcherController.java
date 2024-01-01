package com.CodeSyncBE.codeSync.Controllers;

import com.CodeSyncBE.codeSync.ProblemFetchScraper;
import com.CodeSyncBE.codeSync.models.ProblemData;
import org.jsoup.nodes.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/fetch-problem")
public class ProblemFetcherController {
    private static final Logger logger = LoggerFactory.getLogger(WebSocketController.class);
    @GetMapping("/scrape/{problemSlug}")
    public ResponseEntity<ProblemData> scrapeLeetCodeProblem(@PathVariable String problemSlug) {
        String problemUrl = "https://leetcode.com/problems/" + problemSlug + "/description/";
        logger.info(problemUrl);
        Map<String, String> result = new HashMap<>();

        try {
            // Call the web scraping function
            ProblemFetchScraper scraper = new ProblemFetchScraper();
            ProblemData problemData = scraper.scrapeLeetCodeProblem(problemUrl);

            return ResponseEntity.ok(problemData);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}
