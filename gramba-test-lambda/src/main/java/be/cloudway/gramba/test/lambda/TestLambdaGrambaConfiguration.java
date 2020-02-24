package be.cloudway.gramba.test.lambda;

import be.cloudway.easy.reflection.dependency.configuration.ScanForReflectionInterface;
import be.cloudway.gramba.annotations.GrambaConfigurationTarget;

import java.util.ArrayList;
import java.util.List;

@GrambaConfigurationTarget
public class TestLambdaGrambaConfiguration implements ScanForReflectionInterface {
    @Override
    public List<String> scanPackages() {
        List<String> reflectedPackages = new ArrayList<>();
        reflectedPackages.add("be.cloudway.gramba.test.lambda");

        return reflectedPackages;
    }
}
