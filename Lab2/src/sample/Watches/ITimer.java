package sample.Watches;

import sample.Alarm.IAlarm;

import java.util.Vector;

public interface ITimer {
    void setHours(int hours) throws Exception;
    void setMinutes(int minutes) throws Exception;
    void setSeconds(int seconds) throws Exception;

    void addHours(int hours);
    void addMinutes(int minutes);
    void addSeconds(int seconds) throws Exception;

    Vector<IAlarm> isAlarm();
    public void addAlarm(IAlarm alarm);
}
