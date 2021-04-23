package Alarm;

import Manager.Event;
import Manager.EventType;
import Watch.IWatch;

import javax.persistence.*;

@Entity
@Table(name = "hms_alarms")
public class HMSAlarm extends HMAlarm {
    @Column
    protected int seconds = 0;

    @Override
    public void setSeconds(int seconds) throws Exception {
        if (seconds < 0 || seconds > 59)
            throw new Exception("Invalid seconds");
        this.seconds = seconds;
    }

    @Override
    public int getSeconds() {
        return seconds;
    }

    @Override
    public void signal(Event event) {
        if (event.type == EventType.UPDATE_WATCH) {
            IWatch watch = event.watch;
            int hours = watch.getHours();
            int minutes = watch.getMinutes();
            int seconds = 0;
            try {
                seconds = watch.getSeconds();
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (hours == this.hours && minutes == this.minutes && seconds == this.seconds)
                eventManager.notify(new Event(EventType.ALARM_TRIGGER, this));
            return;
        }
    }

    @Override
    public String toString() {
        return hours + ":" + minutes + ":" + seconds;
    }

    @Override
    public boolean equals(IAlarm alarm) {
        HMSAlarm HMSAlarm = (HMSAlarm)alarm;
        return this.id == HMSAlarm.id && this.hours == HMSAlarm.hours && this.minutes == HMSAlarm.minutes && this.seconds == HMSAlarm.seconds;
    }
}
