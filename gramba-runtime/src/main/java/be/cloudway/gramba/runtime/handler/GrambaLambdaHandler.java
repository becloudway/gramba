package be.cloudway.gramba.runtime.handler;

import be.cloudway.gramba.runtime.helpers.JacksonHelper;
import be.cloudway.gramba.runtime.model.ApiResponse;
import com.amazonaws.services.lambda.runtime.Context;

import java.util.List;
import java.util.Map;

public interface GrambaLambdaHandler<T, C extends Context> {
    T customHandler (ApiResponse body, Map<String, List<String>>  headers, JacksonHelper jacksonHelper);
    C getContext();
}
