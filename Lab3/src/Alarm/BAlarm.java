package Alarm;

import Timer.ITimer;

public class BAlarm {
    public static IAlarm build(EAlarm type) {
        if (type == EAlarm.HMAlarm)
            return new HMAlarm();
        else if (type == EAlarm.HMSAlarm)
            return new HMSAlarm();
        else
            return null;
    }
}
