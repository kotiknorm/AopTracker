package com.makarov.ui.tracker.library.handlers;

import android.widget.RadioGroup;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.makarov.ui.tracker.library.annotations.AttachState;
import com.makarov.ui.tracker.library.annotations.LoggingModel;
import com.makarov.ui.tracker.library.event.Event;

/**
 * Created by makarov on 13/07/15.
 */
public class GroupCheckHandler extends WhiteListHandler {

    @Override
    public Event buildEvent(Method method, Object object, Object[] arg) throws Exception {
        int id = ((RadioGroup) arg[0]).getId();
        if (getWhiteList().contains(id)) {
            String nameView = getWhiteList().getName(id);
            String nameEvent = method.getName();
            List<LoggingModel> list = new ArrayList<>();
            Map<String, String> params = new HashMap<>();
            params.put("isChecked", String.valueOf(arg[1]));

            Annotation annotation = method.getAnnotation(AttachState.class);
            if (annotation != null) {
                String[] fieldsName = ((AttachState) annotation).value();
                list = getFieldsByNames(object, fieldsName);
            }

            Event event = new Event(nameView, nameEvent, list, null);
            return event;
        }
        return null;
    }

}
