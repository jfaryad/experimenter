package org.experimenter.repository.util;

import org.experimenter.repository.model.Model;
import org.slf4j.Logger;

public class Logging {

    /**
     * Depending on the level of logging enabled, the method logs the message and the model with various levels of
     * detail.
     * 
     * @param logger
     *            the {@link Logger} to be used
     * @param msg
     *            the message to be logged
     * @param obj
     *            the {@link Model} to be logged
     */
    public static void logTraceDebugModel(Logger logger, String msg, Model obj) {
        if (logger.isTraceEnabled())
            logger.trace(msg + obj.toDebugString());
        else if (logger.isDebugEnabled())
            logger.debug(msg + obj);
    }

    /**
     * Depending on the level of logging enabled, the method logs the message and the object with the appropriate level.
     * 
     * @param logger
     *            the {@link Logger} to be used
     * @param msg
     *            the message to be logged
     * @param obj
     *            the {@link Object} to be logged
     */
    public static void logTraceDebug(Logger logger, String msg, Object obj) {
        if (logger.isTraceEnabled())
            logger.trace(msg + obj);
        else if (logger.isDebugEnabled())
            logger.debug(msg + obj);
    }

    /**
     * Depending on the level of logging enabled, the method logs the message with the appropriate level.
     * 
     * @param logger
     *            the {@link Logger} to be used
     * @param msg
     *            the message to be logged
     */
    public static void logTraceDebug(Logger logger, String msg) {
        if (logger.isTraceEnabled())
            logger.trace(msg);
        else if (logger.isDebugEnabled())
            logger.debug(msg);
    }

}
