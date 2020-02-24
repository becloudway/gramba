package be.cloudway.gramba.test.lambda;


import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;

import static be.cloudway.gramba.runtime.GrambaRuntime.STATIC_REFERENCES;

public class RequestHandler implements com.amazonaws.services.lambda.runtime.RequestHandler<APIGatewayProxyRequestEvent, APIGatewayProxyResponseEvent> {

    @Override
    public APIGatewayProxyResponseEvent handleRequest(APIGatewayProxyRequestEvent input, Context context) {
        APIGatewayProxyResponseEvent apiGatewayProxyResponseEvent = new APIGatewayProxyResponseEvent();
        apiGatewayProxyResponseEvent.setStatusCode(200);
        apiGatewayProxyResponseEvent.setBody(STATIC_REFERENCES.jacksonHelper.fromObj(new ResponseObject()));

        return apiGatewayProxyResponseEvent;
    }
}
