package br.com.cart.writer.application.logging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AppLogger {

    private static final Logger logger = LoggerFactory.getLogger(AppLogger.class);

    private AppLogger() {}

    public static void info(String message, String layer) {
        logger.info("[{}] {}", layer, message);
    }

    public static void warn(String message, String layer) {
        logger.warn("[{}] {}", layer, message);
    }

    public static void warn(String message, String layer, Throwable throwable) {
        logger.warn("[{}] {}", layer, message, throwable);
    }

    public static void error(String message, String layer) {
        logger.error("[{}] {}", layer, message);
    }

    public static void error(String message, String layer, Throwable throwable) {
        logger.error("[{}] {}", layer, message, throwable);
    }

    public static void debug(String message, String layer) {
        logger.debug("[{}] {}", layer, message);
    }

    public static void debug(String message, String layer, Throwable throwable) {
        logger.debug("[{}] {}", layer, message, throwable);
    }

    public static void trace(String message, String layer) {
        logger.trace("[{}] {}", layer, message);
    }

    public static void trace(String message, String layer, Throwable throwable) {
        logger.trace("[{}] {}", layer, message, throwable);
    }
}
