package sample.Watches;

import sample.Alarm.IAlarm;
import java.util.Vector;

public class HMWatches implements ITimer {
    String name;
    int hours = 0;
    int minutes = 0;

    Vector<IAlarm> alarms = new Vector<IAlarm>();

    public HMWatches(String name) {
        this.name = name;
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
    public void addHours(int hours) {
        this.hours = (this.hours + hours) % 12;
    }

    @Override
    public void addMinutes(int minutes) {
        this.minutes = (this.minutes + minutes) % 60;
    }

    @Override
    public void addSeconds(int seconds) throws Exception {
        throw new Exception("Seconds are unsupported");
    }

    @Override
    public void addAlarm(IAlarm alarm) {
        alarms.add(alarm);
    }

    @Override
    public Vector<IAlarm> isAlarm() {
        Vector<IAlarm> arrayAlarm = new Vector<IAlarm>();
        for (int i = 0; i < alarms.size(); i++) {
            IAlarm alarm = alarms.elementAt(i);
            try {
                if (alarm.getHours() == this.hours && alarm.getMinutes() == this.minutes) {
                    arrayAlarm.add(alarm);
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        return arrayAlarm;
    }

    public String toString() {
        return hours + ":" + minutes;
    }
}
