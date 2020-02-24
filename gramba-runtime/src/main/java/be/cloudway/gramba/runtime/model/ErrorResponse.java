package be.cloudway.gramba.runtime.model;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ErrorResponse {
    private String errorMessage;
    private List<String> stackTrace;
    private String errorType;

    public ErrorResponse(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public ErrorResponse (Exception ex) {
        errorMessage = ex.getMessage();
        errorType = ex.getClass().getName();
        stackTrace = Arrays.stream(ex.getStackTrace())
                .map(StackTraceElement::toString)
                .collect(Collectors.toList());
    }

    public List<String> getStackStrace() {
        return stackTrace;
    }

    public void setStackStrace(List<String> stackStrace) {
        this.stackTrace = stackStrace;
    }

    public String getErrorType() {
        return errorType;
    }

    public void setErrorType(String errorType) {
        this.errorType = errorType;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
