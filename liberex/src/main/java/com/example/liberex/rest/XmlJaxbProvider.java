package com.example.liberex.rest;

import java.io.IOException;
import java.io.OutputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.MessageBodyWriter;
import javax.ws.rs.ext.Provider;
import javax.xml.bind.annotation.XmlRootElement;

import com.example.liberex.util.AppException;
import com.example.liberex.util.XdoMarshaller;

@Provider
@Produces(MediaType.APPLICATION_XML)
public class XmlJaxbProvider<T> implements MessageBodyWriter<T> {
    XdoMarshaller marshaller = new XdoMarshaller();

    @Override
    public long getSize(T arg0, Class<?> arg1, Type arg2, Annotation[] arg3, MediaType arg4) {
        return -1;
    }

    @Override
    public boolean isWriteable(Class<?> arg0, Type arg1, Annotation[] arg2, MediaType arg3) {
        return arg0.isAnnotationPresent(XmlRootElement.class);
    }

    @Override
    public void writeTo(T target, Class<?> type, Type genericType, Annotation[] arg3, MediaType arg4,
            MultivaluedMap<String, Object> arg5, OutputStream outputStream) throws IOException, WebApplicationException {
        try {
            marshaller.toXml(target, outputStream);
        }
        catch (Exception e) {
            throw AppException.wrap(e, "While serializing using JAXB for type " + type.getName());
        }
    }
}
