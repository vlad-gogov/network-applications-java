package Manager;

import java.util.LinkedList;

public class EventManager {
    LinkedList<EventListener> listeners = new LinkedList<EventListener>();

    public void subscribe(EventListener listener) {
        listeners.add(listener);
    }

    public void unsubscribe(EventListener listener) {
        listeners.add(listener);
    }

    public void notify(Event event) {
        for (EventListener listener : listeners) {
            listener.signal(event);
        }
    }

}
