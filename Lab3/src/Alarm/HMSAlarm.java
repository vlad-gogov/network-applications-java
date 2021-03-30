package Alarm;

import Observer.AEvent;
import Watch.EventTime;

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
    public void signal(AEvent event) {
        EventTime time = (EventTime)event;
        if (time.hours == this.hours && time.minutes == this.minutes && time.seconds == this.seconds) {
            slot.call();
        }
    }
}
