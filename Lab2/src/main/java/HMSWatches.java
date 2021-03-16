public class HMSWatches extends HMWatches {
    int seconds = 0;

    public HMSWatches(String name_) {
        super(name_);
    }

    @Override
    public void setSeconds(int seconds_) throws Exception {
        if (seconds_ < 0 || seconds_ > 59)
            throw new Exception("Invalid seconds");
        this.seconds = seconds_;
    }

    @Override
    public void addSeconds(int seconds_) {
        this.seconds = (this.seconds + seconds_) % 60;
    }

    public String toString() {
        return super.toString() + ":" + seconds;
    }
}
