package be.cloudway.gramba.test.lambda;

import be.cloudway.gramba.runtime.handler.GrambaLambdaHandler;
import be.cloudway.gramba.runtime.GrambaRuntime;
import be.cloudway.gramba.runtime.model.ApiResponse;
import be.cloudway.gramba.runtime.aws.runtime.implementation.ContextImpl;
import be.cloudway.gramba.runtime.eventrunner.RunOnceEventRunner;
import be.cloudway.gramba.runtime.helpers.JacksonHelper;
import be.cloudway.grambda.runtime.dev.addons.commands.CommandHandler;
import be.cloudway.grambda.runtime.dev.addons.commands.FileLoader;
import be.cloudway.grambda.runtime.dev.addons.model.GrambaSettings;
import be.cloudway.grambda.runtime.dev.addons.strategy.MockAwsApiStrategy;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;

import java.util.List;
import java.util.Map;

public class GrambaHandler implements GrambaLambdaHandler<APIGatewayProxyResponseEvent, ContextImpl> {
    private static final RequestHandler handler = new RequestHandler();
    private static final GrambaRuntime gramba = new GrambaRuntime(new GrambaHandler());

    @Override
    public APIGatewayProxyResponseEvent customHandler(ApiResponse body, Map<String, List<String>> headers, JacksonHelper jacksonHelper) {
        APIGatewayProxyRequestEvent awsApiRequest =
                jacksonHelper.toObject(body.getResponse(), APIGatewayProxyRequestEvent.class);

        return handler.handleRequest(awsApiRequest, getContext());
    }

    @Override
    public ContextImpl getContext() {
        return new ContextImpl();
    }

    public static void main (String... args) {
        if (args.length > 0) {
            GrambaSettings settings = new CommandHandler().validateArgs(args);
            if (settings.isTestMode()) {
                FileLoader fileLoader = new FileLoader();
                String testFile = fileLoader.loadFileAsString(settings.getPathToMockJson());

                MockAwsApiStrategy mock = new MockAwsApiStrategy(testFile);
                new GrambaRuntime(new RunOnceEventRunner(new GrambaHandler(), mock)).runEventRunner();

                return;
            }
        }

        gramba.runEventRunner();
    }
}
