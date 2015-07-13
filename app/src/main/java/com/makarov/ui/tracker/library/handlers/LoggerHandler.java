package com.makarov.ui.tracker.library.handlers;

import java.lang.reflect.Method;

import com.makarov.ui.tracker.library.event.Event;

/**
 * Created by makarov on 13/07/15.
 */
public interface LoggerHandler {
    Event buildEvent(Method method, Object object, Object[] arg) throws Exception;
}
