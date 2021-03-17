package sample.Alarm;

public class HMAlarm implements IAlarm{
    int hours = 0;
    int minutes = 0;

    @Override
    public void setHours(int hours) throws Exception {
        if (hours < 0 || hours > 11)
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

    public String toString() {
        return hours + ":" + minutes;
    }
}
