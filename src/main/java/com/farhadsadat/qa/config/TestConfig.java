package com.farhadsadat.qa.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/** Loads environment-aware framework settings from the classpath. */
public final class TestConfig {
    private final Properties properties;

    private TestConfig(Properties properties) {
        this.properties = properties;
    }

    public static TestConfig load() {
        String environment = System.getProperty("environment", "local");
        String resource = "config-" + environment + ".properties";
        Properties properties = new Properties();

        try (InputStream input = TestConfig.class.getClassLoader().getResourceAsStream(resource)) {
            if (input == null) {
                throw new IllegalStateException("Configuration not found: " + resource);
            }
            properties.load(input);
            return new TestConfig(properties);
        } catch (IOException exception) {
            throw new IllegalStateException("Unable to load " + resource, exception);
        }
    }

    public String get(String key) {
        String override = System.getProperty(key);
        if (override != null && !override.isBlank()) {
            return override;
        }

        String value = properties.getProperty(key);
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("Missing required configuration: " + key);
        }
        return value;
    }
}

