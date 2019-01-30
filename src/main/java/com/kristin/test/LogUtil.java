package com.kristin.test;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

public class LogUtil {
    private static final Map<String, Logger> map = new HashMap<>();

    public LogUtil() {
    }

    private static Logger getLogger(String logName) {
        if (map.get(logName) == null) {
            Logger logger = LoggerFactory.getLogger(logName);
            map.put(logName, logger);
            return logger;
        } else {
            return map.get(logName);
        }
    }

    public static void info(String logContent) {
        Logger logger = getLogger("info");
        logger.info(logContent);
    }

    public static void info(String logName, String logContent) {
        Logger logger = getLogger(logName);
        logger.info(logContent);
    }

    public static void info(String logName, Object logContent) {
        Logger logger = getLogger(logName);
        logger.info(logName, logContent);
    }

    public static void info(String logName, Object logContent, Throwable t) {
        Logger logger = getLogger(logName);
        logger.info(logName, logContent, t);
    }

    public static void debug(String logContent) {
        Logger logger = getLogger("debug");
        logger.debug(logContent);
    }

    public static void debug(String logName, String logContent) {
        Logger logger = getLogger(logName);
        logger.debug(logContent);
    }

    public static void debug(String logName, Object logContent) {
        Logger logger = getLogger(logName);
        logger.debug(logName, logContent);
    }

    public static void debug(String logName, Object logContent, Throwable t) {
        Logger logger = getLogger(logName);
        logger.debug(logName, logContent, t);
    }

    public static void trace(String logContent) {
        Logger logger = getLogger("trace");
        logger.trace(logContent);
    }

    public static void trace(String logName, String logContent) {
        Logger logger = getLogger(logName);
        logger.trace(logContent);
    }

    public static void trace(String logName, Object logContent) {
        Logger logger = getLogger(logName);
        logger.trace(logName, logContent);
    }

    public static void trace(String logName, Object logContent, Throwable t) {
        Logger logger = getLogger(logName);
        logger.trace(logName, logContent, t);
    }

    public static void warn(String logContent) {
        Logger logger = getLogger("warn");
        logger.warn(logContent);
    }

    public static void warn(String logName, String logContent) {
        Logger logger = getLogger(logName);
        logger.warn(logContent);
    }

    public static void warn(String logName, Object logContent) {
        Logger logger = getLogger(logName);
        logger.warn(logName, logContent);
    }

    public static void warn(String logName, Object logContent, Throwable t) {
        Logger logger = getLogger(logName);
        logger.warn(logName, logContent, t);
    }

    public static void error(String logContent) {
        Logger logger = getLogger("error");
        logger.error(logContent);
    }

    public static void error(String logName, String logContent) {
        Logger logger = getLogger(logName);
        logger.error(logContent);
    }

    public static void error(String logName, Object logContent) {
        Logger logger = getLogger(logName);
        logger.error(logName, logContent);
    }

    public static void error(String logName, Object logContent, Throwable t) {
        Logger logger = getLogger(logName);
        logger.error(logName, logContent, t);
    }
}
