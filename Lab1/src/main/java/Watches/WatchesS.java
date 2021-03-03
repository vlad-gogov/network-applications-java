package Watches;

public class WatchesS extends Watches{
    protected int seconds;

    public WatchesS(String name, double price) {
        super(name, price);
        this.seconds = 0;
    }

    public void setTime(int hours, int minutes, int seconds) throws Exception {
        super.setTime(hours, minutes);
        if (seconds < 0 || seconds > 59)
            throw new Exception("Invalid seconds");
        this.seconds = seconds;
    }

    public void addTime(int hours, int minutes, int seconds) {
        super.addTime(hours, minutes);
        this.seconds = (this.seconds + seconds) % 60;
    }

    public int[] getTime() {
        return new int[]{this.hours, this.minutes, this.seconds};
    }
}