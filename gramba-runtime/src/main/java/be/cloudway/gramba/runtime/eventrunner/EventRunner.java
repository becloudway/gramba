package be.cloudway.gramba.runtime.eventrunner;

import be.cloudway.gramba.runtime.handler.GrambaLambdaHandler;
import be.cloudway.gramba.runtime.Invocation;
import be.cloudway.gramba.runtime.model.ApiResponse;
import be.cloudway.gramba.runtime.aws.runtime.implementation.AwsUrlBuilder;
import be.cloudway.gramba.runtime.helpers.JacksonHelper;
import be.cloudway.gramba.runtime.model.GrambaConstants;
import be.cloudway.gramba.runtime.strategy.AwsApiStrategy;

import static be.cloudway.gramba.runtime.GrambaRuntime.STATIC_REFERENCES;

public abstract class EventRunner implements EventRunnerInterface{
    private AwsApiStrategy awsApiStrategy;
    private GrambaLambdaHandler requestHandler;

    private JacksonHelper jacksonHelper = STATIC_REFERENCES.jacksonHelper;
    private AwsUrlBuilder awsUrlBuilder = STATIC_REFERENCES.awsUrlBuilder;

    protected EventRunner(GrambaLambdaHandler requestHandler, AwsApiStrategy awsApiStrategy) {
        this.requestHandler = requestHandler;
        this.awsApiStrategy = awsApiStrategy;
    }

    protected void runNextInvocation() {
        try {
            Invocation invocation = this.getInvocation();
            processInvocation(invocation);
        } catch(Exception ex){
            ex.printStackTrace();
            this.awsApiStrategy.postInitError(ex);
        }
    }

    private void processInvocation (Invocation invocation) {
        try {
            Object response = requestHandler.customHandler(invocation.getWebResponse(),
                    invocation.getWebResponse().getHeaders(), jacksonHelper);
            awsApiStrategy.postInvocationResponse(invocation, response);
        } catch (Exception ex) {
            ex.printStackTrace();
            awsApiStrategy.postInvocationError(invocation, ex);
        }
    }

    private Invocation getInvocation () {
        ApiResponse apiResponse = awsApiStrategy.getNextInvocation();

        return new Invocation(awsUrlBuilder.getApiEndpoint(),
                apiResponse.getHeaders().get(GrambaConstants.REQUEST_ID_HEADER_KEY).get(0), apiResponse);
    }
}
