package be.cloudway.grambda.runtime.helpers;

import be.cloudway.gramba.runtime.aws.runtime.implementation.AwsUrlBuilder;
import be.cloudway.gramba.runtime.helpers.UrlType;
import org.junit.Rule;

import org.junit.contrib.java.lang.system.EnvironmentVariables;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class AwsUrlBuilderTest {

    private final AwsUrlBuilder awsUrlBuilder = new AwsUrlBuilder();
    private final String BASE_PATH = "http://cloudway.be/2018-06-01/runtime/";

    private final String REQUEST_ID = "request_123";

    @Rule
    public final EnvironmentVariables environmentVariables = new EnvironmentVariables();

    @BeforeEach
    public void setup () {
        environmentVariables.set(AwsUrlBuilder.ENV_AWS_LAMBDA_RUNTIME_API, "cloudway.be");
    }

    @Test
    public void shouldReturnAProperBasePath () {
        String endpoint = awsUrlBuilder.getApiEndpoint();
        assertThat(endpoint).isEqualTo(BASE_PATH);
    }

    @Test
    public void shouldReturnAProperNextInvocationUrl () {
        String endpoint = awsUrlBuilder.getUrl(UrlType.NEXT_INVOCATION);
        assertThat(endpoint).isEqualTo(BASE_PATH + "invocation/next");
    }

    @Test
    public void shouldReturnAProperResponseUrl () {
        String endpoint = awsUrlBuilder.getUrl(UrlType.RESPONSE, REQUEST_ID);
        assertThat(endpoint).isEqualTo(BASE_PATH + "invocation/" + REQUEST_ID + "/next");
    }

    @Test
    public void shouldReturnAProperResponseErrorUrl () {
        String endpoint = awsUrlBuilder.getUrl(UrlType.ERROR, REQUEST_ID);
        assertThat(endpoint).isEqualTo(BASE_PATH + "invocation/" + REQUEST_ID + "/error");
    }

    @Test
    public void shouldReturnAProperInvocationErrorUrl () {
        String endpoint = awsUrlBuilder.getUrl(UrlType.INIT_ERROR, REQUEST_ID);
        assertThat(endpoint).isEqualTo(BASE_PATH + "init/error");
    }
}
