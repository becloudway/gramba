package be.cloudway.gramba.runtime.dev.addons.commands;

import be.cloudway.gramba.runtime.dev.addons.model.GrambaSettings;
import org.apache.commons.cli.*;

//TODO: Remove this from the core module
public class CommandHandler {

    private Options getOptions () {
        Options options = new Options();

        Option testing = new Option("t", "testMode", false,
                "If set, the AWS API Lambda will be ignored and mock requests can be used.");
        testing.setRequired(false);
        options.addOption(testing);

        Option mockObject = new Option("m", "mockObject", true,
                "A reference to a json file that can act as a request object");
        mockObject.setRequired(false);
        options.addOption(mockObject);

        return options;
    }

    private GrambaSettings validateOptions (CommandLine cmd) {
        GrambaSettings gralaSettings = new GrambaSettings();

        gralaSettings.setTestMode(cmd.hasOption("testMode"));
        gralaSettings.setPathToMockJson(cmd.getOptionValue("mockObject"));

        gralaSettings.setDebugMode(Boolean.valueOf(System.getenv("debug")));

        return gralaSettings;
    }

    public GrambaSettings validateArgs (String... args) {
        Options options = getOptions();

        CommandLineParser parser = new DefaultParser();
        HelpFormatter formatter = new HelpFormatter();
        CommandLine cmd;

        try {
            cmd = parser.parse(options, args);
        } catch (ParseException e) {
            formatter.printHelp("Gramba", options );

            System.exit(1);
            return null;
        }

        return validateOptions(cmd);
    }
}
