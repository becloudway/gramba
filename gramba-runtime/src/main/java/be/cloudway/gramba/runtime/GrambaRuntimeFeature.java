package be.cloudway.gramba.runtime;

import be.cloudway.gramba.runtime.model.ApiResponse;
import be.cloudway.gramba.runtime.model.ErrorResponse;
import com.oracle.svm.core.annotate.AutomaticFeature;
import org.graalvm.nativeimage.Feature;
import org.graalvm.nativeimage.RuntimeReflection;

@AutomaticFeature
public class GrambaRuntimeFeature implements Feature {
    @Override
    public void beforeAnalysis(BeforeAnalysisAccess access) {
        registerForReflection(ErrorResponse.class);
        registerForReflection(ApiResponse.class);

        registerForReflection(java.util.LinkedHashMap.class);
        registerForReflection(java.util.ArrayList.class);
        registerForReflection(java.util.HashSet.class);
        registerForReflection(java.util.Map.class);

        registerForReflection(Boolean[].class);
        registerForReflection(Boolean.class);

        registerForReflection(String[].class);
        registerForReflection(String.class);

        registerForReflection(Integer[].class);
        registerForReflection(Integer.class);

        registerForReflection(Double[].class);
        registerForReflection(Double.class);

        registerForReflection(Long[].class);
        registerForReflection(Long.class);

        registerForReflection(Float[].class);
        registerForReflection(Float.class);
    }

    private static void registerForReflection(Class<?> clazz) {
        RuntimeReflection.register(clazz);
        RuntimeReflection.register(clazz.getConstructors());
        RuntimeReflection.register(clazz.getMethods());
    }
}
