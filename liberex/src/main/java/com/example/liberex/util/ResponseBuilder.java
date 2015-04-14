package com.example.liberex.util;

import org.joda.time.DateTime;

import com.example.liberex.xdo.AbstractResponse;
import com.example.liberex.xdo.CodeMessage;
import com.example.liberex.xdo.SystemRef;

public class ResponseBuilder<T extends AbstractResponse> {
    T rs;

    static public <T extends AbstractResponse> ResponseBuilder<T> of(Class<T> clasz) {
        ResponseBuilder<T> builder = new ResponseBuilder<>();
        try {
            builder.rs = clasz.newInstance();
            builder.rs.setResponseDttm(DateTime.now());
        }
        catch (InstantiationException | IllegalAccessException e) {
            AppAssert.isTrue(false, "Could not instantiate object of class " + clasz.getName());
        }
        builder.rs.withSources(new SystemRef().withType("WS")
                .withId(System.getProperty("app.id")));
        return builder;
    }

    public static void updateBeforeResponding(AbstractResponse rs) {
        rs.setResponseDttm(DateTime.now());
    }

    public static void setError(AbstractResponse rs, CodeMessage cm) {
        rs.withErrors(cm);
    }

    public static void setError(AbstractResponse rs, int code, String msg) {
        rs.withErrors((new CodeMessage()).withCode(code).withMessage(msg));
    }

    public T build() {
        return rs;
    }
}
