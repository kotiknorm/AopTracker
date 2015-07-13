package com.makarov.ui.tracker.library;

import com.makarov.ui.tracker.library.annotations.LoggingModel;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by makarov on 13/07/15.
 */
public class Utils {

    public static List<LoggingModel> getFieldsByNames(Object obj, String[] fieldsName) throws Exception {
        List<LoggingModel> list = new ArrayList<>();
        Class c = obj.getClass();

        for (String fieldName : fieldsName) {
            Field field = c.getField(fieldName);
            LoggingModel loggingModel = (LoggingModel) field.get(obj);
            list.add(loggingModel);
        }

        return list;
    }

}
