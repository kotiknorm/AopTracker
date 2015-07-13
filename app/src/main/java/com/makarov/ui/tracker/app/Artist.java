package com.makarov.ui.tracker.app;

import java.util.HashMap;
import java.util.Map;

import com.makarov.ui.tracker.library.annotations.LoggingModel;

/**
 * Created by makarov on 12/07/15.
 */
public class Artist implements LoggingModel {

    private final  String mId;
    private final String mName;

    public Artist(String id, String name){
        mId = id;
        mName = name;
    }

    /*
       ...
     */

    @Override
    public Map<String, String> getModelLogState() {
        Map<String, String> logMap = new HashMap<>();
        logMap.put("artistId", mId);
        logMap.put("artistName", mName);
        return logMap;
    }
}
