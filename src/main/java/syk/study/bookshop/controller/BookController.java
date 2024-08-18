package syk.study.bookshop.controller;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

@RestController
public class BookController {
    @GetMapping("/books")
    public ResponseEntity<?> bookSearch(@RequestParam(name="title") String title) throws IOException {
        // 검색 키워드 인코딩
        String encodedTitle;
        try {
            encodedTitle = URLEncoder.encode(title, StandardCharsets.UTF_8);
        } catch (Exception e) {
            throw new RuntimeException("검색어 인코딩 실패");
        }
        // naver api 에서 데이터 받아오기
        String url = "https://openapi.naver.com/v1/search/book?query=" + encodedTitle;
        Map<String, String> requestHeaders = new HashMap<>();
        requestHeaders.put("X-Naver-Client-Id", "kkNA172GeJMdHwEi09UM");
        requestHeaders.put("X-Naver-Client-Secret", "3ZhUJlBd6V");
        String result = get(url, requestHeaders);
        return ResponseEntity.ok(result);
    }

    private static String get(String url, Map<String, String> requestHeaders) {
        HttpURLConnection con = connect(url);
        try {
            con.setRequestMethod("GET");
            for (Map.Entry<String, String> header : requestHeaders.entrySet()) {
                con.setRequestProperty(header.getKey(), header.getValue());
            }

            int responseCode = con.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                return readBody(con.getInputStream());
            } else {
                return readBody(con.getErrorStream());
            }
        } catch (IOException e) {
            throw new RuntimeException("API 요청과 응답 실패");
        } finally {
            con.disconnect();
        }
    }

    private static HttpURLConnection connect(String apiUrl) {
        try {
            URL url = new URL(apiUrl);
            return (HttpURLConnection)url.openConnection();

        } catch (MalformedURLException e) {
            throw new RuntimeException("URL 접근이 잘못되었습니다." + e);
        } catch (IOException e) {
            throw new RuntimeException("연결 실패" + e);
        }
    }

    private static String readBody(InputStream body){
        InputStreamReader streamReader = new InputStreamReader(body);


        try (BufferedReader lineReader = new BufferedReader(streamReader)) {
            StringBuilder responseBody = new StringBuilder();


            String line;
            while ((line = lineReader.readLine()) != null) {
                responseBody.append(line);
            }


            return responseBody.toString();
        } catch (IOException e) {
            throw new RuntimeException("API 응답을 읽는 데 실패했습니다.", e);
        }
    }

}
