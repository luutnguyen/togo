package togo.backend.base;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class TogoLogger {

    static {
        String log4jConfig = System.getProperty("log4j.configurationFile");
        if (log4jConfig == null || log4jConfig.trim().equals("")) {
            log4jConfig = "conf/log4j2.xml";
            System.setProperty("log4j.configurationFile", log4jConfig);
        }
    }

    private final Logger logger;

    protected TogoLogger(Logger logger) {
        this.logger = logger;
    }

    public static TogoLogger getLogger(Class clazz) {
        return new TogoLogger(LogManager.getLogger(clazz));
    }

    public void error(String message, Object... params) {
        log(Level.ERROR, message, params);
    }

    public void warn(String message, Object... params) {
        log(Level.WARN, message, params);
    }

    public void info(String message, Object... params) {
        log(Level.INFO, message, params);
    }

    public void error(String message, Throwable throwable) {
        log(Level.ERROR, message, throwable);
    }

    public void debug(String message, Object... params) {
        log(Level.DEBUG, message, params);
    }

    private void log(Level level, String message, Object... params) {
        logger.log(level, message, params);
    }
}
