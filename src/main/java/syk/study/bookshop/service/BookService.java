package syk.study.bookshop.service;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import syk.study.bookshop.entity.BookRequestDto;
import syk.study.bookshop.entity.BookResponseDto;
import syk.study.bookshop.repository.BookRepository;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class BookService {
    private final BookRepository bookRepository;

    public void registerBook(BookRequestDto bookRequestDto) {
        // isbn 으로 책 정보 얻기

        // username 으로 사용자 정보 얻기


    }


    public BookResponseDto getData(String keyword, String keywordType) throws IOException {
        String encodedKeyword;
        // 검색 키워드 UTF-8 으로 인코딩
        try {
            encodedKeyword = URLEncoder.encode(keyword, StandardCharsets.UTF_8);
        } catch (Exception e) {
            throw new RuntimeException("검색어 인코딩 실패");
        }

        // api 에서 데이터 받아오기
        String url = "https://openapi.naver.com/v1/search/book_adv?d_" + keywordType + "=" + encodedKeyword;
        Map<String, String> requestHeaders = new HashMap<>();
        requestHeaders.put("X-Naver-Client-Id", "kkNA172GeJMdHwEi09UM");
        requestHeaders.put("X-Naver-Client-Secret", "3ZhUJlBd6V");

        String stringResult = get(url, requestHeaders); // String 형태로 받아온다
        ObjectMapper objectMapper = new ObjectMapper();
        BookResponseDto result = objectMapper.readValue(stringResult, BookResponseDto.class);
        return result;
    };

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
