package Observer;

import java.util.LinkedList;

public class EventManager {
    LinkedList<IListener> listeners = new LinkedList<IListener>();

    public EventManager() {}

    public void subscribe(IListener listener) {
        listeners.add(listener);
    }

    public void unsubscribe(IListener listener) {
        listeners.remove(listener);
    }

    public void broadcast(AEvent event) {
        for (IListener listener : listeners) {
            listener.signal(event);
        }
    }
}
