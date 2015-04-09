package com.example.librerex.client;

import java.io.File;

import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.example.liberex.util.AppAssert;
import com.example.liberex.xdo.AbstractRequest;
import com.example.liberex.xdo.SystemRef;

public class AbstractJIntTest extends AbstractJTest {
    private static final Logger logger = LoggerFactory.getLogger(AbstractJIntTest.class);
    private static final String APP_CLIENT_ENV = "app.client.env";

    // predefined environments
    protected static final String ENV_LOCAL = "local";
    protected static final String ENV_DEV = "dev";
    protected static final String ENV_QA = "qa";

    // the default environment is "local" used by developers on their own machines
    private static final String DEFAULT_ENV = ENV_LOCAL;

    // runtime environment for this test
    private static String env;
    private static AppClientConfig clientConfig;

    static protected void setEnv(String testEnv) {
        // command line system property overwrites this constructor's parameter
        String env = System.getProperty(APP_CLIENT_ENV);
        if (env == null || env.length() == 0)
        {
            // the environment is not set with an environment var
            if (testEnv == null)
            {
                logger.debug("No environment defined, it will be set to the default value.");
                env = DEFAULT_ENV;
            }
            else {
                env = testEnv;
            }

            System.setProperty(APP_CLIENT_ENV, env);
        }

        // look for the configuration directory in the system property
        String appConfigDir = System.getProperty(APP_CLIENT_CONFIG_DIR);

        // if not, default to the area of this project
        if (appConfigDir == null) {
            appConfigDir = "src/main/config/env/" + env;
        }
        System.setProperty(APP_CLIENT_CONFIG_DIR, appConfigDir);

        logger.debug("Environment: " + System.getProperty(APP_CLIENT_ENV));
        logger.debug("App config dir: " + System.getProperty(APP_CLIENT_CONFIG_DIR));
    }

    protected String getClientConfig(String key) {
        if (clientConfig == null) {
            clientConfig = AppClientConfig.readConfig(new File(
                    System.getProperty(APP_CLIENT_CONFIG_DIR) + "/liberex-client.properties"));
        }
        return clientConfig.get(key);
    }

    static protected String getEnv() {
        return env;
    }

    static protected boolean inEnv(String runtimeEnv) {
        return env.equals(runtimeEnv);
    }

    static protected <T extends AbstractRequest> T createRequest(Class<T> clasz) {
        T rs = null;
        try {
            rs = (T) clasz.newInstance();
            rs.setRequestDttm(DateTime.now());
        }
        catch (InstantiationException | IllegalAccessException e) {
            AppAssert.isTrue(false, "Could not instantiate object of class " + clasz.getName());
        }
        rs.withSources(new SystemRef().withType("UNITTEST")
                .withId(System.getProperty("app.id"))
                .withOperation(""));
        return rs;
    }

}
