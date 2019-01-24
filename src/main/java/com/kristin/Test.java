package com.kristin;


import com.kristin.test.LogUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Kristin
 * @since 2018/7/27 23:09
 **/

public class Test {
    public static void main(String[] args) {

        LogUtil.info("logcontent");

        LogUtil.info("INFO", "logcontent2");

        Logger logger = LoggerFactory.getLogger("INFO");

        try {
            throw new RuntimeException("=========");
        } catch (Exception e) {
            logger.info("error", e);
        }

    }
}