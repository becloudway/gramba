package be.cloudway.gramba.runtime.dev.addons.mock;

import be.cloudway.gramba.runtime.GrambaRuntime;
import be.cloudway.gramba.runtime.dev.addons.helpers.MockHandler;
import be.cloudway.gramba.runtime.eventrunner.RunOnceEventRunner;
import be.cloudway.gramba.runtime.strategy.AwsApiStrategy;
import be.cloudway.gramba.runtime.dev.addons.strategy.MockAwsApiStrategy;
import org.junit.jupiter.api.Test;

public class MockRunnerTest {

    private AwsApiStrategy mockStrategy = new MockAwsApiStrategy("test");
    private GrambaRuntime grambdaRuntime = new GrambaRuntime(new RunOnceEventRunner(new MockHandler(), mockStrategy));

    @Test
    public void test () {
        grambdaRuntime.runEventRunner();
    }
}
