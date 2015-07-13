package com.makarov.ui.tracker.app;

import com.makarov.ui.tracker.library.annotations.LoggingModel;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by makarov on 12/07/15.
 */

public class Track implements LoggingModel {

    private String mId;

    public Track(String id){
        mId = id;
    }

    /*
       ...
     */

    @Override
    public Map<String, String> getModelLogState() {
        Map<String, String> logMap = new HashMap<>();
        logMap.put("trackId", mId);
        return logMap;
    }
}
