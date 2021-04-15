package Manager;

import java.util.LinkedList;

public class EventManager {
    LinkedList<Listener> listeners = new LinkedList<Listener>();

    public void subscribe(Listener listener) {
        listeners.add(listener);
    }

    public void unsubscribe(Listener listener) {
        listeners.add(listener);
    }

    public void notify(Event event) {
        for (Listener listener : listeners) {
            listener.signal(event);
        }
    }

}
