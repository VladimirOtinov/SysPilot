package com.vladimir.syspilot.logging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SysPilotLogger {
    private static final Logger logger = LoggerFactory.getLogger(SysPilotLogger.class);

    public static Logger getLogger() {
        return logger;
    }
}
