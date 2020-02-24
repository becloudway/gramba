package be.cloudway.gramba.runtime;

import be.cloudway.gramba.runtime.aws.runtime.implementation.AwsUrlBuilder;
import be.cloudway.gramba.runtime.helpers.JacksonHelper;

public class StaticReferences {
    public final JacksonHelper jacksonHelper = new JacksonHelper();
    public final AwsUrlBuilder awsUrlBuilder = new AwsUrlBuilder();
}
