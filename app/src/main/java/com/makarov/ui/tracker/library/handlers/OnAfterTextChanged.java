package com.makarov.ui.tracker.library.handlers;

import com.makarov.ui.tracker.library.annotations.AttachState;
import com.makarov.ui.tracker.library.annotations.LoggingModel;
import com.makarov.ui.tracker.library.event.Event;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by makarov on 13/07/15.
 */

public class OnAfterTextChanged extends WhiteListHandler {

    private final String TEXT_WATCHER_FIELD = "text";

    @Override
    public Event buildEvent(Method method, Object object, Object[] arg) throws Exception {
            String nameEvent = method.getName();
            List<LoggingModel> list = new ArrayList<>();
            Map<String, String> params = new HashMap<>();
            params.put(TEXT_WATCHER_FIELD, String.valueOf(arg[0]));

            Annotation annotation = method.getAnnotation(AttachState.class);
            if (annotation != null) {
                String[] fieldsName = ((AttachState) annotation).value();
                list = getFieldsByNames(object, fieldsName);
            }

            Event event = new Event("textView", nameEvent, list, params);
            return event;
    }

}

