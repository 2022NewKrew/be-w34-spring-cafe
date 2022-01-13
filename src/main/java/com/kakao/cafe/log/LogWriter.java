package com.kakao.cafe.log;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LogWriter {
    private static Logger logger = LoggerFactory.getLogger(LogWriter.class);
    public static void writeLogger(String log) {
        logger.info(log);
    }
}
