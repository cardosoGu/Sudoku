package service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NotifierService {
    private Map<EventEnum, List<EventListener>> listeners = new HashMap<EventEnum, List<EventListener>>() {{
        put(EventEnum.CLEAR_SPACE, new ArrayList<>());
    }};

    public void subscriber(EventEnum event, EventListener eventListener) {
        var selectedListeners = listeners.get(event);
        selectedListeners.add(eventListener);
    }

    public void notifier(EventEnum event) {
         listeners.get(event).forEach(listener -> listener.notify(event));

    }
}