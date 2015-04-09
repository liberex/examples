package com.example.liberex.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AppAssert {
    private static final Logger logger = LoggerFactory.getLogger(AppAssert.class);

    static public void isTrue(boolean condition, String msg) {
        if (!condition) {
            logger.error("Condition failed: " + msg);
            throw new AppException(msg)
                    .withCode(ErrorUtil.FAIILED_ASSERTION);
        }
    }

    static public void isNotNull(Object o, String msg) {
        if (o == null) {
            logger.error("Unexpected null object: " + msg);
            throw new AppException(msg);
        }
    }
}
