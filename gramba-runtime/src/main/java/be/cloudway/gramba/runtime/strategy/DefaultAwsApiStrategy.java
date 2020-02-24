package be.cloudway.gramba.runtime.strategy;

import be.cloudway.gramba.runtime.Invocation;
import be.cloudway.gramba.runtime.aws.runtime.implementation.AwsUrlBuilder;
import be.cloudway.gramba.runtime.helpers.JacksonHelper;
import be.cloudway.gramba.runtime.helpers.UrlType;
import be.cloudway.gramba.runtime.model.ErrorResponse;
import be.cloudway.gramba.runtime.api.HttpClient;
import be.cloudway.gramba.runtime.model.ApiResponse;

import static be.cloudway.gramba.runtime.GrambaRuntime.STATIC_REFERENCES;

public class DefaultAwsApiStrategy implements AwsApiStrategy {
    private static final HttpClient httpClient = new HttpClient();
    private AwsUrlBuilder awsUrlBuilder = STATIC_REFERENCES.awsUrlBuilder;
    private JacksonHelper jacksonHelper = STATIC_REFERENCES.jacksonHelper;

    @Override
    public void postInitError(Exception ex) {
        httpClient.post(awsUrlBuilder.getUrl(UrlType.INIT_ERROR), jacksonHelper.fromObj(new ErrorResponse(ex)),
                httpClient.getDefaultHeaders());
    }

    @Override
    public ApiResponse getNextInvocation() {
        return httpClient.get(awsUrlBuilder.getUrl(UrlType.NEXT_INVOCATION));
    }

    @Override
    public ApiResponse postInvocationResponse(Invocation invocation, Object response) {
        return httpClient.post(awsUrlBuilder.getUrl(UrlType.RESPONSE, invocation.getRequestId()),
                jacksonHelper.fromObj(response), httpClient.getDefaultHeaders());
    }

    @Override
    public void postInvocationError(Invocation invocation, Exception ex) {
        httpClient.post(awsUrlBuilder.getUrl(UrlType.ERROR, invocation.getRequestId()),
                jacksonHelper.fromObj(new ErrorResponse(ex)), httpClient.getDefaultHeaders());

    }
}
