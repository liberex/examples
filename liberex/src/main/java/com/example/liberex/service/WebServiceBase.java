package com.example.liberex.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.example.liberex.util.AppException;
import com.example.liberex.util.ErrorUtil;
import com.ibm.cics.server.Program;

public class WebServiceBase {
    private static final Logger logger = LoggerFactory.getLogger(WebServiceBase.class);

    public static String invokeCicsProgram(String cmd, String input, String charSet) {
        try {
            byte[] commarea = input.getBytes(charSet);
            Program program = new Program();
            program.setName(cmd);
            program.link(commarea);
            return new String(commarea, charSet);
        }
        catch (Throwable e) {
            throw AppException.wrap(e, "Failure to invoke the CICS program " + cmd)
                    .withCode(ErrorUtil.CICS_INVOCATION_ERROR)
                    .addParam("programName", cmd)
                    .addParam("input", input);
        }
    }
}

