package be.cloudway.grambda.runtime.dev.addons.model;

public class GrambaSettings {
    private boolean testMode;
    private boolean debugMode;
    private String pathToMockJson;

    public boolean isTestMode() {
        return testMode;
    }

    public void setTestMode(boolean testMode) {
        this.testMode = testMode;
    }

    public String getPathToMockJson() {
        return pathToMockJson;
    }

    public void setPathToMockJson(String pathToMockJson) {
        this.pathToMockJson = pathToMockJson;
    }

    public boolean isDebugMode() {
        return debugMode;
    }

    public void setDebugMode(boolean debugMode) {
        this.debugMode = debugMode;
    }
}
