package com.example.liberex.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

import javax.xml.ws.handler.MessageContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AppSessionUtil {
    private static final Logger logger = LoggerFactory.getLogger(AppSessionUtil.class);

    static public class AppSession extends HashMap<String, String> {
        private static final long serialVersionUID = 1L;
        public String id;
    }

    static public Map<String, AppSession> sessions = new ConcurrentHashMap<>();

    static public Object getSession(String id) {
        return sessions.get(id);
    }

    public static AppSession getSession(MessageContext mc, boolean create) {
        Map<String, Object> headers = (Map<String, Object>) mc.get(MessageContext.HTTP_REQUEST_HEADERS);
        List<String> cookies = (List<String>) headers.get("Cookie");
        String sessionId = null;
        if (cookies != null) {
            for (String cookie : cookies) {
                // JSESSIONID=0000lzUOQ8hIvX6VQUlF8ebMZtG:f0558fa0-3bcc-40c4-ac9d-8ea08f56dc1d
                String parts[] = cookie.split("=");
                if (parts.length == 2 && "JSESSIONID".equals(parts[0])) {
                    sessionId = parts[1];
                }
            }
        }
        logger.debug("SessionId: {}", sessionId);
        AppSession session = sessions.get(sessionId);
        if (session == null && create) {
            session = new AppSession();
            session.id = Integer.toString((new Random()).nextInt());
        }
        return session;
    }
}
