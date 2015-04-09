package com.example.liberex.rest;

import java.util.ArrayList;

import com.example.liberex.util.ErrorUtil;
import com.example.liberex.xdo.AbstractResponse;
import com.example.liberex.xdo.CodeMessage;

public class AbstractResource {
    static public void addWarning(AbstractResponse rs, int code, String msg, String ... params) {
        if (rs.getWarnings() == null) {
            rs.setWarnings(new ArrayList<CodeMessage>());
        }
        rs.getWarnings().add(ErrorUtil.createError(code, msg, params));
    }

    static public void addWarning(AbstractResponse rs, Exception e) {
        if (rs.getWarnings() == null) {
            rs.setWarnings(new ArrayList<CodeMessage>());
        }
        rs.getWarnings().add(ErrorUtil.convertExceptionToError(e));
    }
}
