package be.cloudway.gramba.runtime.api;

import be.cloudway.gramba.runtime.model.ApiResponse;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class HttpClient {

    public HttpURLConnection getConnection (String url, String method) {
        try {
            URL conn = new URL(url);
            HttpURLConnection httpURLConnection = (HttpURLConnection) conn.openConnection();
            httpURLConnection.setRequestMethod(method);

            return httpURLConnection;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public Map<String, String> getDefaultHeaders () {
        Map<String, String> defaults = new HashMap<>();

        defaults.put("content-type", "application/json");
        defaults.put("accept", "application/json, */*");

        return defaults;
    }


    public HttpURLConnection setHeaders (HttpURLConnection httpURLConnection, Map<String, String> headers) {
        headers.forEach(httpURLConnection::setRequestProperty);

        return httpURLConnection;
    }

    public ApiResponse get (String url) {
        HttpURLConnection http = getConnection(url, "GET");
        return getWebResponse(http);
    }

    public ApiResponse post (String url, String data, Map<String, String> headers) {
        byte[] requestData = data.getBytes(StandardCharsets.UTF_8);

        int length = requestData.length;

        HttpURLConnection http = getConnection(url, "POST");
        http.setDoOutput(true);

        setHeaders(http, headers);

        http.setFixedLengthStreamingMode(length);
        http.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
        try {
            http.connect();

            try(OutputStream os = http.getOutputStream()) {
                os.write(requestData);
            }

            return getWebResponse(http);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            http.disconnect();
        }

    }

    public ApiResponse getWebResponse (HttpURLConnection urlConnection) {
        InputStream response;

        try {
            response = urlConnection.getInputStream();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        StringBuilder stringBuilder = new StringBuilder();
        try (Scanner scanner = new Scanner(response)) {
            if (scanner.useDelimiter("\\A").hasNext()) {
                stringBuilder.append(scanner.useDelimiter("\\A").next());
            }
        }

        return new ApiResponse(stringBuilder.toString(), urlConnection.getHeaderFields());
    }
}

