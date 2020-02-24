package be.cloudway.gramba.runtime.eventrunner;

import be.cloudway.gramba.runtime.handler.GrambaLambdaHandler;
import be.cloudway.gramba.runtime.strategy.AwsApiStrategy;

public class RunOnceEventRunner extends EventRunner {

    public RunOnceEventRunner(GrambaLambdaHandler requestHandler, AwsApiStrategy awsApiStrategy) {
        super(requestHandler, awsApiStrategy);
    }

    @Override
    public void run() {
        this.runNextInvocation();
    }
}
