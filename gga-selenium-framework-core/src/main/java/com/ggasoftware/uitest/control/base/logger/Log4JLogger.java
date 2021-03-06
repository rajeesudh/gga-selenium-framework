package com.ggasoftware.uitest.control.base.logger;

import com.ggasoftware.uitest.control.base.logger.enums.BusinessInfoTypes;
import com.ggasoftware.uitest.control.base.logger.enums.LogInfoTypes;
import com.ggasoftware.uitest.control.base.logger.enums.LogLevels;
import org.apache.log4j.Logger;

import static com.ggasoftware.uitest.utils.Timer.nowTimeShort;
import static org.apache.log4j.LogManager.getLogger;

/**
 * Created by Roman_Iovlev on 6/9/2015.
 */
public class Log4JLogger extends AbstractLogger {

    private Logger log4j = getLogger("New logger");

    public Log4JLogger() {
        super();
    }

    public Log4JLogger(LogLevels logLevel) {
        super(logLevel);
    }

    @Override
    public void inLog(String message, LogLevels logLevel, LogInfoTypes logInfoType) {
        switch (logLevel) {
            case FATAL:
                log4j.fatal(format("%s: %s", nowTimeShort(), message));
                break;
            case ERROR:
                log4j.error(format("%s: %s", nowTimeShort(), message));
                break;
            case WARNING:
                log4j.warn(format("%s: %s", nowTimeShort(), message));
                break;
            case INFO:
                log4j.info(format("%s: %s", nowTimeShort(), message));
                break;
            case DEBUG:
                log4j.debug(format("%s: %s", nowTimeShort(), message));
                break;
        }
    }

    @Override
    public void inLog(String message, BusinessInfoTypes infoType) {
        log4j.info(format("[%s] %s: %s", infoType, nowTimeShort(), message));
    }

}
