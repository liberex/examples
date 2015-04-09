package com.example.librerex.client;


import java.io.File;
import java.io.IOException;
import java.util.TimeZone;

import org.apache.commons.io.FileUtils;
import org.joda.time.DateTimeZone;

/**
 * Shared functionality for testing based on data files.
 */
public abstract class AbstractJTest {
    private static final String APP_CLIENT_TIME_ZONE = "America/New_York";
    protected static final String APP_CLIENT_CONFIG_DIR = "app.client.config.dir";

    static protected final File inDir = new File("src/test/data/in");
    static protected File outDir = new File("target/test/data/out");
    static protected File ctrlDir = new File("src/test/data/ctrl");
    static private File appClientConfigDir = new File("src/main/config/env/local");

    static protected void setUp() {
        outDir.mkdirs();

        if (System.getProperty(APP_CLIENT_CONFIG_DIR) == null) {
            System.setProperty(APP_CLIENT_CONFIG_DIR, appClientConfigDir.getPath());
        }
        TimeZone.setDefault(TimeZone.getTimeZone(APP_CLIENT_TIME_ZONE));
        DateTimeZone.setDefault(DateTimeZone.forID(APP_CLIENT_TIME_ZONE));
    }

    static protected boolean areEqual(File expected, File actual) throws IOException {
        return FileUtils.contentEquals(expected, actual);
    }

    static protected File getCtrlFile(File output) {
        String path = output.getPath();
        path = path.replace(outDir.getPath(), ctrlDir.getPath());
        return new File(path);
    }

    static protected boolean sameAsCtrlFile(File output) throws IOException {
        return areEqual(getCtrlFile(output), output);
    }
}
