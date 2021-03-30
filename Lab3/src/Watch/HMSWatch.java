package Watch;

public class HMSWatch  extends HMWatch {
    protected int seconds = 0;

    protected void broadcast() {
        manager.broadcast(new EventTime(this.hours, this.minutes, this.seconds));
    }

    @Override
    public void setSeconds(int seconds) throws Exception {
        if (seconds < 0 || seconds > 59)
            throw new Exception("Invalid seconds");
        this.seconds = seconds;
        this.broadcast();
    }

    @Override
    public void addSeconds(int seconds) throws Exception {
        if (seconds < 0)
            throw new Exception("Invalid seconds");
        int s = this.seconds + seconds;
        if (s >= 60) {
            this.seconds = s % 60;
            this.addMinutes(s / 60);
        } else {
            this.seconds += seconds;
            this.broadcast();
        }
    }

    @Override
    public int getSeconds() {
        return seconds;
    }
}
