//package com.seeratsharma.bfhl.service;
//
//import org.springframework.stereotype.Service;
//import org.springframework.web.reactive.function.client.WebClient;
//import org.springframework.beans.factory.annotation.Value;
//
//import java.util.Map;
//
//@Service
//public class AiService {
//
//    @Value("${gemini.api.key}")
//    private String apiKey;
//
//    private final WebClient webClient = WebClient.create();
//
//    public String askAI(String question) {
//        try {
//            //String url = "https://generativelanguage.googleapis.com/v1/models/gemini-1.5-flash:generateContent?key=" + apiKey;
//
//            //String url = "https://generativelanguage.googleapis.com/v1/models/gemini-1.5-flash:generateContent?key=" + apiKey;
//
//            String url = "https://generativelanguage.googleapis.com/v1beta/models/gemini-1.5-flash-latest:generateContent?key=" + apiKey;
//
//            Map<String, Object> requestBody = Map.of(
//                    "contents", new Object[]{
//                            Map.of("parts", new Object[]{
//                                    Map.of("text", question + " Answer in one word.")
//                            })
//                    }
//            );
//
//            Map response = webClient.post()
//                    .uri(url)
//                    .bodyValue(requestBody)
//                    .retrieve()
//                    .bodyToMono(Map.class)
//                    .block();
//
//            Map candidate = (Map) ((java.util.List) response.get("candidates")).get(0);
//            Map content = (Map) candidate.get("content");
//            java.util.List parts = (java.util.List) content.get("parts");
//            Map part = (Map) parts.get(0);
//
//            return part.get("text").toString().trim();
//
//        } catch (Exception e) {
//            e.printStackTrace();
//            return "AI_ERROR";
//        }
//    }
//}

package com.seeratsharma.bfhl.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Map;
import java.util.List;

@Service
public class AiService {

    @Value("${gemini.api.key}")
    private String apiKey;

    private final WebClient webClient = WebClient.create("https://generativelanguage.googleapis.com");

    public String askAI(String question) {
        try {
            Map<String, Object> requestBody = Map.of(
                    "contents", List.of(
                            Map.of(
                                    "parts", List.of(
                                            Map.of("text", question + " Answer in one word.")
                                    )
                            )
                    )
            );

            Map response = webClient.post()
                    .uri("/v1beta/models/gemini-2.5-flash:generateContent?key=" + apiKey)
                    .bodyValue(requestBody)
                    .retrieve()
                    .bodyToMono(Map.class)
                    .block();

            List candidates = (List) response.get("candidates");
            Map candidate = (Map) candidates.get(0);
            Map content = (Map) candidate.get("content");
            List parts = (List) content.get("parts");
            Map part = (Map) parts.get(0);

            return part.get("text").toString().trim();

        } catch (Exception e) {
            e.printStackTrace();
            return "AI_ERROR";
        }
    }
}

