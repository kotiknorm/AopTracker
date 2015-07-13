package com.makarov.ui.tracker.library.repository;

import java.util.Collection;

import com.makarov.ui.tracker.library.event.Event;

/**
 * Created by makarov on 13/07/15.
 */
public interface IEventRepository {

    void addEvent(Event event);

    Collection<Event> getEvents();

    void clear();

}
