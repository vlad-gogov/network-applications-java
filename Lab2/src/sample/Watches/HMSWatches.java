package sample.Watches;

import sample.Alarm.IAlarm;

import java.util.Vector;

public class HMSWatches extends HMWatches {
    int seconds = 0;

    public HMSWatches(String name) {
        super(name);
    }

    @Override
    public void setSeconds(int seconds) throws Exception {
        if (seconds < 0 || seconds > 59)
            throw new Exception("Invalid seconds");
        this.seconds = seconds;
    }

    @Override
    public void addSeconds(int seconds) {
        int s = this.seconds + seconds;
        if (s > 60) {
            int m = s / 60;
            s = m * 60 - seconds;
            addMinutes(m);
        }
        this.seconds = s;
    }

    @Override
    public Vector<IAlarm> isAlarm() {
        Vector<IAlarm> vectorAlarm = new Vector<IAlarm>();
        for (int i = 0; i < alarms.size(); i++) {
            IAlarm alarm = alarms.elementAt(i);
            int seconds = 0;
            try {
                seconds = alarm.getSeconds();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
            if (alarm.getHours() == this.hours && alarm.getMinutes() == this.minutes && seconds == this.seconds) {
                vectorAlarm.add(alarm);
            }
        }
        return vectorAlarm;
    }

    public String toString() {
        return super.toString() + ":" + seconds;
    }
}
