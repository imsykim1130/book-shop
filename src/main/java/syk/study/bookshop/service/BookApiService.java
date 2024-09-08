package syk.study.bookshop.service;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Unmarshaller;
import org.springframework.stereotype.Service;
import syk.study.bookshop.dto.BookDto;
import syk.study.bookshop.dto.BookSearchResponseDto;
import syk.study.bookshop.xml.Item;
import syk.study.bookshop.xml.Rss;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class BookApiService {

    // 제목으로 책 검색(검색결과 여러개)
    public BookSearchResponseDto getBooksByTitle(String title, String display, String start) throws IOException {
        String encodedTitle;
        String query;

        try {
            encodedTitle = URLEncoder.encode(title, StandardCharsets.UTF_8);
            query = "d_titl=" + encodedTitle + "&display=" + display + "&start=" + start;
        } catch (Exception e) {
            throw new RuntimeException("검색어 인코딩 실패");
        }

        int total = Integer.parseInt(getData(query).getChannel().getTotal());
        List<BookDto> bookDtos = getData(query).getChannel().getItem().stream().map(item -> BookDto.newBookDto(item)).toList();

        return new BookSearchResponseDto(total, bookDtos);
    }


    // isbn 으로 책 검색(검색결과 1개)
    public BookDto getBookByIsbn(String isbn) throws IOException {
        String query = "d_isbn=" + isbn;
        Item item = getData(query).getChannel().getItem().getFirst();
        return BookDto.newBookDto(item);
    }


    // 여기 아래로 naver api 에서 데이터 받아오는 코드
    private Rss getData(String query) throws IOException {
        String url = "https://openapi.naver.com/v1/search/book_adv.xml?" + query;

        // Header
        // naver api 인증 정보 헤더에 포함해야 데이터 받을 수 있다
        Map<String, String> requestHeaders = new HashMap<>();
        requestHeaders.put("X-Naver-Client-Id", "kkNA172GeJMdHwEi09UM");
        requestHeaders.put("X-Naver-Client-Secret", "3ZhUJlBd6V");

        // API 로 부터 데이터 받아오기
        String stringResult = get(url, requestHeaders); // String 형태로 받아온다
        JAXBContext jaxbContext;
        try {
            jaxbContext = JAXBContext.newInstance(Rss.class);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            Rss rss = (Rss) unmarshaller.unmarshal(new StringReader(stringResult));
            return rss;
        } catch (JAXBException e) {
            throw new RuntimeException(e);
        }
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

            // Java Object 로 변환을 위해 필요없는 부분 삭제하고 값을 반환
            return responseBody.toString().substring(38);

        } catch (IOException e) {
            throw new RuntimeException("API 응답을 읽는 데 실패했습니다.", e);
        }
    }
}
