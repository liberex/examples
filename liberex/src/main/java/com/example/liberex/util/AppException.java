package com.example.liberex.util;

import java.util.LinkedHashMap;
import java.util.Map;

public class AppException extends RuntimeException {
    private static final long serialVersionUID = 1L;


    int code = 0;
    Map<String, String> params;

    public AppException(String msg) {
        super(msg);
    }

    public AppException(String msg, Throwable cause) {
        super(msg, cause);
    }

    public AppException withCode(int code) {
        this.code = code;
        return this;
    }

    public int getCode() {
        return code;
    }

    public AppException addParam(String name, String value) {
        if (params == null) {
            params = new LinkedHashMap<>();
        }
        params.put(name, value);
        return this;
    }

    public Map<String, String> getParams() {
        return params;
    }

    /**
     * This wraps an existing exception, typically a checked exception to an AppException
     * @param t
     * @param string
     * @return
     */
    public static AppException wrap(Throwable t, String string) {
        if (t instanceof AppException) {
            // avoid wrapping the same exception multiple times
            return (AppException) t;
        }
        AppException e = new AppException(string, t);
        return e;
    }
}
