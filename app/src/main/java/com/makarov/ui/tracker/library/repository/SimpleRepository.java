package com.makarov.ui.tracker.library.repository;

import android.util.Log;

import com.makarov.ui.tracker.library.event.Event;

import java.util.Collection;
import java.util.Collections;

/**
 * Created by makarov on 13/07/15.
 */
public class SimpleRepository implements IEventRepository {

    private static final String TAG = SimpleRepository.class.getSimpleName();

    @Override
    public void addEvent(Event event) {
        Log.d(TAG, event.toString());
    }

    @Override
    public Collection<Event> getEvents() {
        return Collections.emptyList();
    }

    @Override
    public void clear() {
        Log.d(TAG, "remove all data");
    }
}
