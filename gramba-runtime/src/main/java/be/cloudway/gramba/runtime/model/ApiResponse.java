package be.cloudway.gramba.runtime.model;

import java.util.List;
import java.util.Map;

public class ApiResponse {
    private String response;
    private Map<String,List<String>> headers;

    public ApiResponse(String response, Map<String, List<String>> headers) {
        this.response = response;
        this.headers = headers;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public Map<String, List<String>> getHeaders() {
        return headers;
    }

    public void setHeaders(Map<String, List<String>> headers) {
        this.headers = headers;
    }
}
