package be.cloudway.gramba.test.lambda;

import be.cloudway.gramba.annotations.Reflected;

@Reflected
public class ResponseObject {
    private String response;

    public ResponseObject() {
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }
}
