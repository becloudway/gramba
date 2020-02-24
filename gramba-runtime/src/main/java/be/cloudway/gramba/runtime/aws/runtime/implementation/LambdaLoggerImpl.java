package be.cloudway.gramba.runtime.aws.runtime.implementation;

import com.amazonaws.services.lambda.runtime.LambdaLogger;

//TODO: Provide a proper logging solution
public class LambdaLoggerImpl implements LambdaLogger {
    @Override
    public void log(String message) {
        System.out.println(message);
    }

    @Override
    public void log(byte[] message) {
        System.out.println("Not implemented: LambdaLoggerImpl.log(byte[])");
    }
}
