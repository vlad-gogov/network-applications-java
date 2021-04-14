package Manager;

import Alarm.IAlarm;
import Watch.IWatch;

public class Event {
    public EventType type;

    public IAlarm alarm = null;
    public IWatch watch = null;
    public boolean run = false;

    public Event(EventType type) {
        this.type = type;
    }

    public Event(EventType type, IAlarm alarm) {
        this.type = type;
        this.alarm = alarm;
    }

    public Event(EventType type, IWatch watch) {
        this.type = type;
        this.watch = watch;
    }

    public Event(EventType type, IWatch watch, boolean run) {
        this.type = type;
        this.watch = watch;
        this.run = run;
    }
}
