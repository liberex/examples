package com.example.liberex.config;

import javax.inject.Named;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ibm.cics.server.IsCICS;

@Named
public class AppConfig {
    private static final Logger logger = LoggerFactory.getLogger(AppConfig.class);

    public String getVersion() {
        return "1.0";
    }

    public String getCharSet() {
        String charset = "UTF8";
        if (isCicsEnabled()) {
            return "CP037";
        }
        return charset;
    }

    static public boolean isCicsEnabled() {
        try {
            int cicsStatus = IsCICS.getApiStatus();
            if (cicsStatus == IsCICS.CICS_REGION_AND_API_ALLOWED) {
                return true;
            }
        }
        catch (Throwable e) {
            logger.warn("CICS not installed.");
        }
        return false;
    }

}
