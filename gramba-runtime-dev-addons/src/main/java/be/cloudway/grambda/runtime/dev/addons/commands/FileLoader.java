package be.cloudway.grambda.runtime.dev.addons.commands;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class FileLoader {
    public String loadFileAsString(String fullOrRelativePath) {
        final File f = new File(fullOrRelativePath);

        if (!f.exists()) {
            throw new RuntimeException("MockObject not found");
        }

        String result;

        try (Scanner scanner = new Scanner(f)) {
            scanner.useDelimiter("\\Z");
            result = scanner.next();

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);

        }

        return result;
    }
}
