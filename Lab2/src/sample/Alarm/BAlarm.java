package sample.Alarm;

public class BAlarm {
    public static IAlarm build(alarmType type) {
        if (type == alarmType.HMAlarm)
            return new HMAlarm();
        else if (type == alarmType.HMSAlarm)
            return new HMSAlarm();
        else
            return null;
    }
}
