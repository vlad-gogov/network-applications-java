package Alarm;

import Manager.Event;
import Manager.Listener;
import Manager.EventManager;
import Manager.EventType;
import Watch.IWatch;

public class HMAlarm implements IAlarm {
    EventManager eventManager = new EventManager();
    protected int hours = 0;
    protected int minutes = 0;

    @Override
    public void setHours(int hours) throws Exception {
        if (hours < 0|| hours > 11)
            throw new Exception("Invalid hours");
        this.hours = hours;
    }

    @Override
    public void setMinutes(int minutes) throws Exception {
        if (minutes < 0 || minutes > 59)
            throw new Exception("Invalid minutes");
        this.minutes = minutes;
    }

    @Override
    public void setSeconds(int seconds) throws Exception {
        throw new Exception("Seconds are unsupported");
    }

    @Override
    public int getHours() {
        return hours;
    }

    @Override
    public int getMinutes() {
        return minutes;
    }

    @Override
    public int getSeconds() throws Exception {
        throw new Exception("Seconds are unsupported");
    }

    public void addSubcriber(Listener eventListener) {
        eventManager.subscribe(eventListener);
    }

    @Override
    public void signal(Event event) {
        if (event.type == EventType.UPDATE_WATCH) {
            IWatch watch = event.watch;
            int hours = watch.getHours();
            int minutes = watch.getMinutes();
            if (hours == this.hours && minutes == this.minutes)
                eventManager.notify(new Event(EventType.ALARM_TRIGGER, this));
            return;
        }
    }
}
