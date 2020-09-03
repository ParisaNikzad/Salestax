package com.tax.salestax.config;

import java.util.logging.*;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

/**
 * Writing log
 * @author parisanikzad
 */
public class Log {

    public static final Logger aLogger = Logger.getLogger("myLogger");
    private static Log log = null;

    /**
     * Singleton class
     *
     * @return Log instance
     */
    public static Log getInstance(){
        if(log == null){
            getLoggerReady();
            log = new Log();
        }
        return log;
    }

    /**
     * Create log
     */
    private static void getLoggerReady(){
        try{
            FileHandler fh = new FileHandler("_log.log");
            fh.setFormatter(new SimpleFormatter());
            aLogger.addHandler(fh);
            aLogger.setUseParentHandlers(false);
            aLogger.setLevel(Level.ALL);
        } catch(Exception e){
            System.out.print("Error: Logger creation issue: "+e);
        }
    }
}
