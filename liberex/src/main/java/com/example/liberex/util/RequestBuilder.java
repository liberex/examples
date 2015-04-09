package com.example.liberex.util;

import org.joda.time.DateTime;

import com.example.liberex.xdo.AbstractRequest;
import com.example.liberex.xdo.SchemaProperties;
import com.example.liberex.xdo.SystemRef;

public class RequestBuilder<T extends AbstractRequest> {
    T rq;

    static public <T extends AbstractRequest> RequestBuilder<T> of(Class<T> clasz) {
        RequestBuilder<T> builder = new RequestBuilder<>();

        try {
            builder.rq = (T) clasz.newInstance();
            builder.rq.setRequestDttm(DateTime.now());
            builder.rq.setApiVersion(new SchemaProperties().getApiVersion());
        }
        catch (InstantiationException | IllegalAccessException e) {
            AppAssert.isTrue(false, "Could not instantiate object of class " + clasz.getName());
        }
        return builder;
    }

    public RequestBuilder<T> withSource(String systemType, String systemId) {
        rq.withSources(new SystemRef().withType(systemType).withId(systemId));
        return this;
    }

    public T build() {
        return (T) rq;
    }
}
