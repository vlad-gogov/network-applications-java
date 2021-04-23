package Watch;

public class HMSWatch  extends HMWatch {
    protected int seconds = 0;

    public HMSWatch() {

    }

    public HMSWatch(int hours, int minutes, int seconds) {
        try {
            setHours(hours);
            setMinutes(minutes);
            setSeconds(seconds);
        } catch (Exception e) {
            e.printStackTrace();
        }
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

    @Override
    public int getTick() {
        return 1000;
    }

    @Override
    public void tick() {
        try {
            this.addSeconds(1);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
