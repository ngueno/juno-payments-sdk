package com.ngueno.juno.sdk.test;

import static org.junit.jupiter.api.Assertions.fail;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public abstract class BaseFixtureHelper {

    public static String getResource(String... resources) {
        return readFromResource(getResourcePath(resources));
    }

    public static Path getResourcePath(String... resources) {
        Path resourcePath = getBaseResourcePath();

        for (String resource : resources) {
            resourcePath = resourcePath.resolve(resource);
        }
        return resourcePath;
    }

    private static Path getBaseResourcePath() {
        return Paths.get("src", "test", "resources");
    }

    private static String readFromResource(Path path) {
        try {
            return Files.readString(path);
        } catch (IOException e) {
            fail("Unable to load resource", e);
        }
        return null;
    }
}
