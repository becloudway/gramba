package be.cloudway.gramba.runtime.aws.runtime.implementation;

import be.cloudway.gramba.runtime.helpers.UrlType;

public class AwsUrlBuilder {

    private static final String PROTOCOL = "http://";
    private static final String VERSION = "2018-06-01";
    private static final String PREFIX = "/runtime/";

    public static final String ENV_AWS_LAMBDA_RUNTIME_API = "AWS_LAMBDA_RUNTIME_API";

    private static final String PATH_SEPARATOR = "/";

    private String apiEndpoint;

    public String getApiEndpoint () {
        if (apiEndpoint == null) {
            String stringBuilder = PROTOCOL + System.getenv(ENV_AWS_LAMBDA_RUNTIME_API) +
                    PATH_SEPARATOR +
                    VERSION +
                    PREFIX;
            apiEndpoint = stringBuilder;
        }

        return apiEndpoint;
    }

    public String getUrl (UrlType urlType, String requestId) {
        StringBuilder suffix = new StringBuilder(getApiEndpoint());

        if (urlType.equals(UrlType.INIT_ERROR)){
            return suffix.append("init/error").toString();
        }

        suffix.append("invocation");
        suffix.append(PATH_SEPARATOR);

        if (urlType.equals(UrlType.NEXT_INVOCATION)) {
            return suffix.append("next").toString();
        }

        suffix.append(requestId).append(PATH_SEPARATOR);
        if (urlType.equals(UrlType.RESPONSE)) {
            return suffix.append("response").toString();
        } else if (urlType.equals(UrlType.ERROR)) {
            return suffix.append("error").toString();
        }

        // TODO: Improve error handling here
        throw new RuntimeException("Do this better");
    }

    public String getUrl (UrlType urlType) {
        return getUrl(urlType, "");
    }
}
