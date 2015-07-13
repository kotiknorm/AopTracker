package com.makarov.ui.tracker.library.event;

import com.makarov.ui.tracker.library.annotations.LoggingModel;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by makarov on 12/07/15.
 */

public class Event {

    private String mNameView;
    private String mNameEvent;
    private List<LoggingModel> mModelList;
    private Map<String, String> mMethodParameters;
    private Date mDate;

    public Event(String nameView, String nameEvent, List<LoggingModel> list, Map<String, String> methodParameters){
        mDate = new Date();
        mNameView= nameView;
        mModelList = list;
        mNameEvent = nameEvent;
        mMethodParameters = methodParameters;
    }

    @Override
    public String toString() {
        return "Event{" +
                "mNameView='" + mNameView + '\'' +
                ", mNameEvent='" + mNameEvent + '\'' +
                ", mModelList=" + mModelList +
                ", mMethodParameters=" + mMethodParameters +
                ", mDate=" + mDate +
                '}';
    }
}
