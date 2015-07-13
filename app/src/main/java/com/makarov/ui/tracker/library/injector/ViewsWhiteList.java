package com.makarov.ui.tracker.library.injector;

/**
 * Created by makarov on 13/07/15.
 */
public interface ViewsWhiteList {

    boolean contains(int viewId);

    void putView(int viewId, String nameView);

    String getName(int viewId);
}
