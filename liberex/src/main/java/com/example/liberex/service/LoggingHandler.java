package com.example.liberex.service;

import java.io.ByteArrayOutputStream;
import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.xml.namespace.QName;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;
import javax.xml.ws.handler.MessageContext;
import javax.xml.ws.handler.soap.SOAPHandler;
import javax.xml.ws.handler.soap.SOAPMessageContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.DOMException;

import com.example.liberex.util.ServiceUtil;

public class LoggingHandler implements SOAPHandler<SOAPMessageContext> {
    private static final Logger logger = LoggerFactory.getLogger(LoggingHandler.class);

    public Set<QName> getHeaders()
    {
        return Collections.emptySet();
    }

    public boolean handleMessage(SOAPMessageContext msgContext)
    {
        Boolean outboundProperty = (Boolean)
                msgContext.get(MessageContext.MESSAGE_OUTBOUND_PROPERTY);
        if (outboundProperty.booleanValue()) {
            // OUTBOUND
            setNamespacePrefixes(msgContext);
            logger.debug("Outbound:\nHeaders:\n{}\nMessage:\n{}", getHttpHeaders(msgContext),
                    getMessageAsStrign(msgContext));
        }
        else {
            // INBOUND
            setNamespacePrefixes(msgContext);
            logger.debug("Inbound:\nHeaders:\n{}\nMessage:\n{}", getHttpHeaders(msgContext),
                    getMessageAsStrign(msgContext));
        }

        return true;
    }

    private void setNamespacePrefixes(SOAPMessageContext msgContext) {
        try {
            msgContext.getMessage().getSOAPHeader().setPrefix("soap");
            msgContext.getMessage().getSOAPHeader().setPrefix("soap");
            msgContext.getMessage().getSOAPHeader().removeNamespaceDeclaration("SOAP-ENV");
            msgContext.getMessage().getSOAPBody().removeNamespaceDeclaration("SOAP-ENV");
            msgContext.getMessage().getSOAPBody().setPrefix("soap");
        }
        catch (DOMException | SOAPException e) {
            logger.warn("Failed to set the SOAP header prefix.");
        }
    }

    private String getHttpHeaders(SOAPMessageContext msgContext) {
        @SuppressWarnings("unchecked")
        Map<String, List<String>> map = (Map<String, List<String>>) msgContext.get(MessageContext.HTTP_REQUEST_HEADERS);
        if (map == null) {
            return "  N/A";
        }
        StringBuilder sb = new StringBuilder();
        for (Entry<String, List<String>> e : map.entrySet()) {
            sb.append("  ").append(e.getKey())
                    .append(": ").append(e.getValue())
                    .append("\n");
        }
        return sb.toString();
    }

    private String getMessageAsStrign(SOAPMessageContext msgContext) {
        SOAPMessage msg = msgContext.getMessage();
        try {
            ByteArrayOutputStream os = new ByteArrayOutputStream();
            msg.writeTo(os);
            Reader r = new StringReader(os.toString());
            Writer w = new StringWriter();
            ServiceUtil.prettyPrintXml(r, w);
            return w.toString();
        }
        catch (Exception e) {
            logger.error("While logging the message: {}", e);
        }
        return null;
    }

    public boolean handleFault(SOAPMessageContext msgContext)
    {
        logger.debug("Fault:\n Headers:\n{}\n Message:\n{}", getHttpHeaders(msgContext),
                getMessageAsStrign(msgContext));
        return true;
    }

    public void close(MessageContext messageContext)
    {
    }
}
