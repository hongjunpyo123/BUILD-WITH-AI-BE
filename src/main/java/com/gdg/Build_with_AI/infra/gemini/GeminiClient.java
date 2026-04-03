package com.gdg.Build_with_AI.infra.gemini;

import com.gdg.Build_with_AI.infra.gemini.dto.request.GeminiRequest;
import com.gdg.Build_with_AI.infra.gemini.dto.response.GeminiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@RequiredArgsConstructor
public class GeminiClient {

    @Value("${gemini.key}")
    private String apiKey;

    private final RestTemplate restTemplate;

    public GeminiResponse rqGeminiText(String text) {
        String url = "https://generativelanguage.googleapis.com/v1beta/models/" + GeminiModel.GEMINI_20_FLASH.getModelId() + ":generateContent?key="
                + apiKey;
        GeminiRequest geminiReqDto = new GeminiRequest(text);
        GeminiResponse geminiResDto = restTemplate.postForObject(url, geminiReqDto, GeminiResponse.class);
        return geminiResDto;
    }

    public GeminiResponse rqGeminiTextWithImage(String text, String mimeType, String data){
        String url = "https://generativelanguage.googleapis.com/v1beta/models/" + GeminiModel.GEMINI_20_FLASH.getModelId() + ":generateContent?key="
                + apiKey;

        GeminiRequest geminiReqDto = new GeminiRequest(text, mimeType, data);
        GeminiResponse geminiResDto = restTemplate.postForObject(url, geminiReqDto, GeminiResponse.class);
        return geminiResDto;
    }

}
