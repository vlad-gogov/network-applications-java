package Watch;

import Observer.AEvent;

public class EventTime extends AEvent {
    public int hours = 0;
    public int minutes = 0;
    public int seconds = 0;


    public EventTime(int hours, int minutes) {
        this.hours = hours;
        this.minutes = minutes;
    }

    public EventTime(int hours, int minutes, int seconds) {
        this.hours = hours;
        this.minutes = minutes;
        this.seconds = seconds;
    }
}
