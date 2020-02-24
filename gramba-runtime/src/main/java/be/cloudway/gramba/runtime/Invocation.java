package be.cloudway.gramba.runtime;

import be.cloudway.gramba.runtime.model.ApiResponse;

public class Invocation {
    private String runtimeApiEndpoint;
    private String requestId;
    private ApiResponse webResponse;

    public Invocation(String runtimeApiEndpoint, String requestId, ApiResponse webResponse) {
        this.runtimeApiEndpoint = runtimeApiEndpoint;
        this.requestId = requestId;
        this.webResponse = webResponse;
    }

    public String getRuntimeApiEndpoint() {
        return runtimeApiEndpoint;
    }

    public void setRuntimeApiEndpoint(String runtimeApiEndpoint) {
        this.runtimeApiEndpoint = runtimeApiEndpoint;
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public ApiResponse getWebResponse() {
        return webResponse;
    }

    public void setWebResponse(ApiResponse webResponse) {
        this.webResponse = webResponse;
    }
}
