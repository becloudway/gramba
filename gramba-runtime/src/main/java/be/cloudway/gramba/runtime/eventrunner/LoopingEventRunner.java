package be.cloudway.gramba.runtime.eventrunner;

import be.cloudway.gramba.runtime.handler.GrambaLambdaHandler;
import be.cloudway.gramba.runtime.strategy.AwsApiStrategy;

public class LoopingEventRunner extends EventRunner {
    private boolean running = false;

    public LoopingEventRunner(GrambaLambdaHandler requestHandler, AwsApiStrategy awsApiStrategy) {
        super(requestHandler, awsApiStrategy);
    }

    @Override
    public void run () {
        setRunning(true);

        while (isRunning()) {
            this.runNextInvocation();
        }
    }

    public boolean isRunning() {
        return running;
    }

    public void setRunning(boolean running) {
        this.running = running;
    }
}
