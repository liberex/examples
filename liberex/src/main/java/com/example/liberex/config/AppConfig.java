package com.example.liberex.config;

import java.util.Properties;

import javax.inject.Named;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ibm.cics.server.IsCICS;

@Named
public class AppConfig {
    private static final Logger logger = LoggerFactory.getLogger(AppConfig.class);

    public String getCharSet() {
        String charset = "UTF8";
        if (isCicsEnabled()) {
            return System.getProperty("com.ibm.cics.jvmserver.local.ccsid");
        }
        return charset;
    }
    
    public Properties getOtherProperties() {
	Properties props = new Properties();
	props.put("isCicsEnabled", Boolean.toString(isCicsEnabled()));
        props.put("shortDescription", "My App @ 4:44");
	return props;
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
