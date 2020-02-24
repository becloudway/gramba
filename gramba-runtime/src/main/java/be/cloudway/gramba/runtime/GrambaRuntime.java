package be.cloudway.gramba.runtime;

import be.cloudway.gramba.runtime.eventrunner.EventRunnerInterface;
import be.cloudway.gramba.runtime.eventrunner.LoopingEventRunner;
import be.cloudway.gramba.runtime.handler.GrambaLambdaHandler;
import be.cloudway.gramba.runtime.strategy.AwsApiStrategy;
import be.cloudway.gramba.runtime.strategy.DefaultAwsApiStrategy;

public class GrambaRuntime {
    public final static StaticReferences STATIC_REFERENCES = new StaticReferences();

    private final EventRunnerInterface eventRunner;

    public GrambaRuntime(EventRunnerInterface eventRunner) {
        this.eventRunner = eventRunner;
    }

    public GrambaRuntime(GrambaLambdaHandler requestHandler) {
        this(requestHandler, new DefaultAwsApiStrategy());
    }

    public GrambaRuntime(GrambaLambdaHandler requestHandler, AwsApiStrategy strategy) {
        this(new LoopingEventRunner(requestHandler, strategy));
    }

    public void runEventRunner () {
        eventRunner.run();
    }
}
