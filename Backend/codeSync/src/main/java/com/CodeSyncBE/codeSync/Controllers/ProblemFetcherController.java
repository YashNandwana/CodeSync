package com.CodeSyncBE.codeSync.Controllers;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.IOException;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/fetch-problem")
public class ProblemFetcherController {
    private static final Logger logger = LoggerFactory.getLogger(WebSocketController.class);

    @GetMapping("/scrape/{problemSlug}")
    public ResponseEntity<String> scrapeLeetCodeProblem(@PathVariable String problemSlug) {
        File htmlFile = new File("D:\\Final Year Project\\codeSync\\" + problemSlug + ".html");
        try {
            ProcessBuilder installBuilder = new ProcessBuilder("go", "install", "github.com/ISKalsi/leet-scrape/v2/cmd/leetscrape@latest");
            Process installProcess = installBuilder.start();
            int installExitCode = installProcess.waitFor();

            if (installExitCode != 0) {
                logger.error("Failed to install leetscrape.");
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to install leetscrape.");
            }
            ProcessBuilder scrapeBuilder = new ProcessBuilder("leetscrape", "--name", problemSlug, "question");
            Process scrapeProcess = scrapeBuilder.start();
            int scrapeExitCode = scrapeProcess.waitFor();
            if (scrapeExitCode != 0) {
                logger.error("Failed to scrape LeetCode problem.");
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to scrape LeetCode problem.");
            }

            Document doc = Jsoup.parse(htmlFile, "UTF-8");
            String content = doc.body().text();
            return ResponseEntity.ok(content);
        } catch (IOException | InterruptedException e) {
            logger.error("Error occurred: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error occurred.");
        } finally {
            if (htmlFile.exists()) {
                boolean deleted = htmlFile.delete();
                if (!deleted) {
                    logger.warn("Failed to delete temporary HTML file: {}", htmlFile.getAbsolutePath());
                }
            }
        }
    }
}
