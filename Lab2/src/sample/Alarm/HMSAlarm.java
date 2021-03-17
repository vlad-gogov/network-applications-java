package sample.Alarm;

public class HMSAlarm extends HMAlarm{
    int seconds = 0;

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

    public String toString() {
        return super.toString() + ":" + seconds;
    }
}
