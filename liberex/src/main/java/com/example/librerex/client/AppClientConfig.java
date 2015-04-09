package com.example.librerex.client;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AppClientConfig {
    private static final Logger logger = LoggerFactory.getLogger(AppClientConfig.class);

    Properties props = new Properties();

    public void readConfig(InputStream is) {
        try {
            props.load(is);
        }
        catch (IOException e) {
            logger.error("Failed to read client properties.");
        }
    }

    static public AppClientConfig readConfig(File cfgFile) {
        AppClientConfig config = new AppClientConfig();
        try (InputStream is = new FileInputStream(cfgFile)) {
            config.readConfig(is);
        }
        catch (Exception e) {
            logger.error("Cannot read properties from file: " + cfgFile.getAbsolutePath());
        }
        return config;
    }

    public String get(String name) {
        return props.getProperty(name);
    }
}
