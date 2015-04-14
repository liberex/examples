package com.example.liberex.service;

import java.io.UnsupportedEncodingException;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.example.liberex.config.AppConfig;
import com.example.liberex.util.AppException;
import com.example.liberex.util.ErrorUtil;
import com.ibm.cics.server.Program;

public class WebServiceBase {
    private static final Logger logger = LoggerFactory.getLogger(WebServiceBase.class);

    @Inject
    AppConfig appConfig;

    private byte[] toByteArray(String text) {
        try {
            return text.getBytes(appConfig.getCharSet());
        }
        catch (UnsupportedEncodingException e) {
            throw AppException.wrap(e, "While converting a string to a byte array");
        }
    }

    private String toString(byte[] bytes) {
        try {
            return new String(bytes, appConfig.getCharSet());
        }
        catch (UnsupportedEncodingException e) {
            throw AppException.wrap(e, "While converting a byte array to string");
        }
    }

    protected String invokeCicsProgram(String cmd, String input) {
        try {
            byte[] commarea = toByteArray(input);
            Program program = new Program();
            program.setName(cmd);
            program.link(commarea);
            return new String(commarea, appConfig.getCharSet());
        }
        catch (Throwable e) {
            throw AppException.wrap(e, "Failure to invoke the CICS program " + cmd)
                    .withCode(ErrorUtil.CICS_INVOCATION_ERROR)
                    .addParam("programName", cmd)
                    .addParam("input", input);
        }
    }
}
