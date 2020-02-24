package be.cloudway.grambda.runtime.dev.addons.strategy;

        import be.cloudway.gramba.runtime.Invocation;
        import be.cloudway.gramba.runtime.helpers.JacksonHelper;
        import be.cloudway.gramba.runtime.model.GrambaConstants;
        import be.cloudway.gramba.runtime.model.ApiResponse;
        import be.cloudway.gramba.runtime.strategy.AwsApiStrategy;

        import java.util.Collections;
        import java.util.HashMap;
        import java.util.List;
        import java.util.Map;

        import static be.cloudway.gramba.runtime.GrambaRuntime.STATIC_REFERENCES;

public class MockAwsApiStrategy implements AwsApiStrategy {
    private JacksonHelper jacksonHelper = STATIC_REFERENCES.jacksonHelper;
    private final String input;

    public MockAwsApiStrategy (String input) {
        this.input = input;
    }

    private Map<String, List<String>> getFakeHeaders () {
        Map<String, List<String>> headers = new HashMap<>();
        headers.put(GrambaConstants.REQUEST_ID_HEADER_KEY, Collections.singletonList("RANDOM"));

        return headers;
    }

    @Override
    public void postInitError(Exception ex) {
        throw new RuntimeException(ex);
    }

    @Override
    public ApiResponse getNextInvocation() {
        return new ApiResponse(input, getFakeHeaders());
    }

    @Override
    public ApiResponse postInvocationResponse(Invocation invocation, Object response) {
        System.out.println(jacksonHelper.fromObj(response));
        return null;
    }

    @Override
    public void postInvocationError(Invocation invocation, Exception ex) {
        throw new RuntimeException(ex);
    }
}
