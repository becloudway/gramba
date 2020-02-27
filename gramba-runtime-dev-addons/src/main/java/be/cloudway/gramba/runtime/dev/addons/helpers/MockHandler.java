package be.cloudway.gramba.runtime.dev.addons.helpers;

import be.cloudway.gramba.runtime.handler.GrambaLambdaHandler;
import be.cloudway.gramba.runtime.model.ApiResponse;
import be.cloudway.gramba.runtime.aws.runtime.implementation.ContextImpl;
import be.cloudway.gramba.runtime.helpers.JacksonHelper;

import java.util.List;
import java.util.Map;

public class MockHandler implements GrambaLambdaHandler<Object, ContextImpl> {
    @Override
    public Object customHandler(ApiResponse body, Map<String, List<String>> headers, JacksonHelper jacksonHelper) {
        return body;
    }

    @Override
    public ContextImpl getContext() {
        return new ContextImpl();
    }
}
