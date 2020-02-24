package be.cloudway.gramba.runtime.handler;

import be.cloudway.gramba.runtime.aws.runtime.implementation.ContextImpl;

public abstract class DefaultGrambaLambdaHandler implements GrambaLambdaHandler {
    @Override
    public ContextImpl getContext () {
        return new ContextImpl();
    }
}
