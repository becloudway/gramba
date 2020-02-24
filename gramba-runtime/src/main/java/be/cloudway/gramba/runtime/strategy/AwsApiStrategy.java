package be.cloudway.gramba.runtime.strategy;

import be.cloudway.gramba.runtime.Invocation;
import be.cloudway.gramba.runtime.model.ApiResponse;

public interface AwsApiStrategy {
    void postInitError (Exception ex);
    ApiResponse getNextInvocation ();
    ApiResponse postInvocationResponse(Invocation invocation, Object response);
    void postInvocationError (Invocation invocation, Exception ex);
}
