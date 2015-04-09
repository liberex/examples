package com.example.liberex.rest;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ApplicationPath("/rs")
public class RestApplication extends Application {
    Logger logger = LoggerFactory.getLogger(RestApplication.class);

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> serviceClasses = new HashSet<Class<?>>();
        serviceClasses.add(AppResource.class);
        return serviceClasses;
    }

    @Override
    public Set<Object> getSingletons() {
        logger.debug("Initializing singletons");
        Set<Object> s = new HashSet<Object>();
        s.add(new XmlJaxbProvider());
        return s;
    }
}
