package Alarm;

import Manager.Event;
import Manager.EventType;
import Watch.IWatch;

public class HMSAlarm extends HMAlarm {
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
}
