package be.cloudway.grambda.runtime.api;

import be.cloudway.gramba.runtime.model.ApiResponse;
import be.cloudway.gramba.runtime.api.HttpClient;
import com.sun.net.httpserver.HttpExchange;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class HttpClientTest {

    private static final HttpMockServer httpMockServer = new HttpMockServer();
    private final HttpClient httpClient = new HttpClient();

    private final String RESPONSE = "{\"success\": true}";

    @BeforeEach
    public void setup() throws IOException {
        httpMockServer.createMockServer(8090);
        httpMockServer.addHandler("/", (HttpExchange exchange) -> {
            byte[] response = RESPONSE.getBytes();
            try {
                exchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, response.length);
                exchange.getResponseBody().write(response);
                exchange.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        httpMockServer.addHandler("/echo", (HttpExchange exchange) -> {
            try {
                byte[] response = httpMockServer.responseBodyToString(exchange).getBytes();
                exchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, response.length);
                exchange.getResponseBody().write(response);
                exchange.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        httpMockServer.start();
    }

    @Test
    public void shouldOpenAConnection() {
        HttpURLConnection httpURLConnection = httpClient.getConnection("http://localhost:8090/test", "POST");
        assertThat(httpURLConnection).isNotNull();
    }

    @Test
    public void shouldBeAbleToGetDataFromAWebServer() {
        ApiResponse apiResponse = httpClient.get("http://localhost:8090/");
        assertThat(apiResponse.getResponse()).isEqualTo(RESPONSE);
    }

    @Test
    public void shouldBeAbleToPostData() {
        ApiResponse apiResponse = httpClient.post("http://localhost:8090/echo", "test", httpClient.getDefaultHeaders());
        assertThat(apiResponse.getResponse()).isEqualTo("test");
    }

    @Test
    public void shouldReturnTheRightDefaultHeaders() {
        Map<String, String> headers = httpClient.getDefaultHeaders();

        assertThat(headers.get("content-type")).isEqualTo("application/json");
        assertThat(headers.get("accept")).isEqualTo("application/json, */*");
    }

    @AfterEach
    public void breakDown() {
        httpMockServer.stop();
    }
}
