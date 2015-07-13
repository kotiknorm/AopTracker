package com.makarov.ui.tracker.library.handlers;

import java.util.List;

import com.makarov.ui.tracker.library.Utils;
import com.makarov.ui.tracker.library.annotations.LoggingModel;
import com.makarov.ui.tracker.library.injector.ViewsWhiteList;

/**
 * Created by makarov on 13/07/15.
 */

public abstract class WhiteListHandler implements LoggerHandler {

    private ViewsWhiteList mWhiteList;

    public void setWhiteList(ViewsWhiteList mWhiteList) {
        this.mWhiteList = mWhiteList;
    }

    protected ViewsWhiteList getWhiteList(){
        return mWhiteList;
    }

    protected List<LoggingModel> getFieldsByNames(Object obj, String[] fieldsName) throws Exception {
        return Utils.getFieldsByNames(obj, fieldsName);
    }

}
