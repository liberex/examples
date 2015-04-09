package com.example.liberex.util;

import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletResponse;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import javax.xml.ws.handler.MessageContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.example.liberex.xdo.AbstractRequest;

public class ServiceUtil {
    private static final Logger logger = LoggerFactory.getLogger(ServiceUtil.class);

    public static void logRequest(MessageContext mctx, AbstractRequest rq) {
        for (Entry<String, Object> e : mctx.entrySet()) {
            logger.debug("Message context entry: {} - {}", e.getKey(), e.getValue());
        }

        logger.debug("Service: {}", mctx.get(MessageContext.WSDL_SERVICE));
        logger.debug("Operation: {}", mctx.get(MessageContext.WSDL_OPERATION));
        logger.debug("HTTP Method: {}", mctx.get(MessageContext.HTTP_REQUEST_METHOD));

        @SuppressWarnings("unchecked")
        Map<String, Object> headers = (Map<String, Object>) mctx.get(MessageContext.HTTP_REQUEST_HEADERS);
        for (Entry<String, Object> h : headers.entrySet()) {
            logger.debug("HTTP Header: {}: {},{}", h.getKey(), h.getValue());
        }
    }

    public static void setError(HttpServletResponse response, int code, String message) {
        try {
            response.sendError(code, message);
        }
        catch (IOException e) {
            throw AppException.wrap(e, "While setting the error in the HTTP response.");
        }
    }

    public static void prettyPrintXml(Reader in, Writer out) throws TransformerException, IOException {
        int indentSize = 2;
        Source xmlInput = new StreamSource(in);
        Result xmlOutput = new StreamResult(out);
        TransformerFactory tf = TransformerFactory.newInstance();
        tf.setAttribute("indent-number", indentSize);

        Transformer t = tf.newTransformer();
        t.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
        t.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
        t.setOutputProperty(OutputKeys.INDENT, "yes");
        t.transform(xmlInput, xmlOutput);
    }

}
