package com.makarov.ui.tracker.library.injector;

import android.util.Log;
import android.view.View;

import com.makarov.ui.tracker.library.annotations.LoggerView;
import com.makarov.ui.tracker.library.event.Event;
import com.makarov.ui.tracker.library.handlers.GroupCheckHandler;
import com.makarov.ui.tracker.library.handlers.LoggerHandler;
import com.makarov.ui.tracker.library.handlers.OnAfterTextChanged;
import com.makarov.ui.tracker.library.handlers.OnCheckedChangedHandler;
import com.makarov.ui.tracker.library.handlers.OnClickHandler;
import com.makarov.ui.tracker.library.handlers.WhiteListHandler;
import com.makarov.ui.tracker.library.repository.IEventRepository;
import com.makarov.ui.tracker.library.repository.SimpleRepository;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by makarov on 12/07/15.
 */

public class ViewEventsTracker implements ViewsWhiteList {

    private final String TAG = ViewEventsTracker.class.getSimpleName();

    private Map<Integer, LoggerHandler>  buildersList = new HashMap<>();
    private Map<Integer, String> viewsList = new HashMap<>();
    private final IEventRepository mRepository;

    public static final int CLICK = 1;
    public static final int CHECKED_CHANGED = 2;
    public static final int RADIO_GROUP = 3;
    public static final int AFTER_TEXT_CHANGED = 4;

    void initHandlers(){
        registerMethodHandler(CLICK, new OnClickHandler());
        registerMethodHandler(CHECKED_CHANGED, new OnCheckedChangedHandler());
        registerMethodHandler(RADIO_GROUP, new GroupCheckHandler());
        registerMethodHandler(AFTER_TEXT_CHANGED, new OnAfterTextChanged());
    }

    void registerMethodHandler(Integer codeEvent, WhiteListHandler builder){
        builder.setWhiteList(this);
        buildersList.put(codeEvent, builder);
    }

    ViewEventsTracker(){
        mRepository = new SimpleRepository();
        initHandlers();
    }

    ViewEventsTracker(IEventRepository repository){
        mRepository = repository;
        initHandlers();
    }

    void inject(Object obj) {
        Class cls = obj.getClass();

        try {
            for (Field f : cls.getFields()) {
                if (!(f.get(obj) instanceof View))
                    continue;

                Annotation annotation = f.getAnnotation(LoggerView.class);
                if (annotation != null) {
                    View view = (View) f.get(obj);
                    putView(view.getId(), ((LoggerView) annotation).value());
                }
            }
        } catch (IllegalAccessException e) {
            Log.e(TAG, "error inject", e);
        }
    }

    void addEvent(int code, Method method, Object object, Object[] arg) throws Exception{
        LoggerHandler builder = getHandler(code);
        Event event = builder.buildEvent(method, object, arg);

        if(event != null)
            mRepository.addEvent(event);
    }

    void clearHistory(){
        mRepository.clear();
    }

    Collection<Event> getEvents(){
        return mRepository.getEvents();
    }

    LoggerHandler getHandler(int code){
        return buildersList.get(code);
    }

    @Override
    public boolean contains(int viewId) {
        return viewsList.containsKey(viewId);
    }

    @Override
    public void putView(int viewId, String nameView) {
        viewsList.put(viewId, nameView);
    }

    @Override
    public String getName(int viewId) {
        return viewsList.get(viewId);
    }
}

