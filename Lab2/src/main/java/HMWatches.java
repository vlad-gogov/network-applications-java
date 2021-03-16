public class HMWatches implements ITimer {
    String name;
    int hours = 0;
    int minutes = 0;

    public HMWatches(String name_) {
        this.name = name_;
    }
    
    @Override
    public void setHours(int hours_) throws Exception {
        if (hours_ < 0 || hours_ > 11)
            throw new Exception("Invalid hours");
        this.hours = hours_;
    }
    
    @Override
    public void setMinutes(int minutes_) throws Exception {
        if (minutes_ < 0 || minutes_ > 59)
            throw new Exception("Invalid minutes");
        this.minutes = minutes_;
    }
    
    @Override
    public void setSeconds(int seconds_) throws Exception {
        throw new Exception("Seconds are unsupported");
    }

    @Override
    public void addHours(int hours_) {
        this.hours = (this.hours + hours_) % 12;
    }

    @Override
    public void addMinutes(int minutes_) {
        this.minutes = (this.minutes + minutes_) % 60;
    }

    @Override
    public void addSeconds(int seconds_) throws Exception {
        throw new Exception("Seconds are unsupported");
    }

    public String toString() {
        return hours + ":" + minutes;
    }
}
