package com.example.liberex.util;

import java.util.Map.Entry;

import javax.xml.ws.WebServiceException;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.exception.ExceptionUtils;

import com.example.liberex.xdo.CodeMessage;
import com.example.liberex.xdo.Param;

/**
 * Base class for CRM errors. Also defines a few errors common across components.
 */
public class ErrorUtil {
    private static final int STACK_TRACE_MAX_SIZE = 500;

    public static final int UNKNOWN_EXCEPTION = 1000;
    public static final int NOT_IMPLEMENTED = 1001;
    public static final int FAIILED_ASSERTION = 1002;
    public static final int CONNECTION_ERROR = 1003;
    public static final int CICS_INVOCATION_ERROR = 1004;

    public static void addExceptionDetails(CodeMessage err, Throwable t) {
        addParam(err, "exceptionClass", t.getClass().getName());
        addParam(err, "exceptionMessage", t.getMessage());
        addParam(err, "stackTrace", getStackTrace(t));
    }

    private static String getStackTrace(Throwable t) {
        return StringUtils.abbreviate(ExceptionUtils.getStackTrace(t), STACK_TRACE_MAX_SIZE);
    }

    public static void addParam(CodeMessage err, String name, Object o) {
        String value = "";
        if (o == null) {
            value = "null";
        }
        else if (o instanceof Exception) {
            value = ((Exception) o).getMessage();
        }
        else {
            value = o.toString();
        }
        err.getParams().add(new Param()
                .withName(name)
                .withValue(value)
                );
    }

    public static Throwable getRootCause(Throwable e) {
        Throwable root = e;
        while (root.getCause() != null) {
            root = root.getCause();
        }
        return root;
    }

    public static String toString(AppException e) {
        StringBuilder sb = new StringBuilder();
        sb.append(e.getCode()).append('-');
        sb.append(e.getMessage());

        if (e.getParams() != null) {
            sb.append(':');
            boolean isFirst = true;
            for (Entry<String, String> p : e.getParams().entrySet()) {
                if (!isFirst) {
                    sb.append(',');
                }
                sb.append(p.getKey()).append(":").append(p.getValue());
                isFirst = false;
            }
        }
        return sb.toString();
    }

    static public CodeMessage convertExceptionToError(Throwable e) {
        CodeMessage err = null;
        if (e instanceof AppException) {
            AppException ae = (AppException) e;
            err = createError(ae.getCode(), ae.getMessage());
            for (Entry<String, String> p : ((AppException) e).getParams().entrySet()) {
                err.getParams().add(new Param().withName(p.getKey()).withValue(p.getValue()));
            }
            if (ae.getCause() != null) {
                addExceptionDetails(err, ae.getCause());
            }
        }
        else if (e instanceof WebServiceException) {
            err = createError(CONNECTION_ERROR, e.getCause().getMessage());
            addExceptionDetails(err, e);
        }
        else {
            err = createError(CONNECTION_ERROR, e.getMessage());
            addExceptionDetails(err, e);
        }
        return err;
    }

    static public CodeMessage createError(int code, String message, String... params) {
        CodeMessage m = new CodeMessage();
        m.setCode(code);
        m.setMessage(message);
        for (int i = 0; i < params.length / 2; i++) {
            Param p = new Param()
                    .withName(params[2 * i])
                    .withValue(params[2 * i + 1]);
            m.getParams().add(p);
        }
        return m;
    }
}
