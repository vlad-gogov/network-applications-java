package Alarm;

import Manager.Event;
import Manager.Listener;
import Manager.EventManager;
import Manager.EventType;
import Watch.IWatch;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Table(name = "hm_alarms")
public class HMAlarm implements IAlarm {
    @Transient
    EventManager eventManager = new EventManager();

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    public int id;

    @Column
    protected int hours = 0;
    @Column
    protected int minutes = 0;

    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

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

    public void addSubscriber(Listener listener) {
        eventManager.subscribe(listener);
    }

    @Override
    public void removeSubscriber(Listener listener) {
        eventManager.unsubscribe(listener);
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

    @Override
    public String toString() {
        return hours + ":" + minutes;
    }

    @Override
    public boolean equals(IAlarm alarm) {
        HMAlarm HMAlarm = (HMAlarm)alarm;
        return this.id == HMAlarm.id && this.hours == HMAlarm.hours && this.minutes == HMAlarm.minutes;
    }
}
