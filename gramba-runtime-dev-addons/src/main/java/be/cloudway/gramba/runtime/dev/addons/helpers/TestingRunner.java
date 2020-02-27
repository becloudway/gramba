package be.cloudway.gramba.runtime.dev.addons.helpers;

import be.cloudway.gramba.runtime.GrambaRuntime;
import be.cloudway.gramba.runtime.dev.addons.commands.CommandHandler;
import be.cloudway.gramba.runtime.dev.addons.commands.FileLoader;
import be.cloudway.gramba.runtime.dev.addons.model.GrambaSettings;
import be.cloudway.gramba.runtime.dev.addons.strategy.MockAwsApiStrategy;
import be.cloudway.gramba.runtime.eventrunner.RunOnceEventRunner;
import be.cloudway.gramba.runtime.handler.GrambaLambdaHandler;

public class TestingRunner {
    public static boolean doRunTests (GrambaLambdaHandler<?, ?> grambaLambdaHandler, String... args) {
        if (args.length > 0) {
            GrambaSettings settings = new CommandHandler().validateArgs(args);
            if (settings.isTestMode()) {
                FileLoader fileLoader = new FileLoader();
                String testFile = fileLoader.loadFileAsString(settings.getPathToMockJson());

                MockAwsApiStrategy mock = new MockAwsApiStrategy(testFile);
                new GrambaRuntime(new RunOnceEventRunner(grambaLambdaHandler, mock)).runEventRunner();

                return true;
            }
        }

        return false;
    }
}
