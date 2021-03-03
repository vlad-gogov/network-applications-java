package Watches;

public class Watches extends BaseWatch {
    protected int hours, minutes;

    public Watches(String name, double price) {
        super(name, price);
        this.hours = 0;
        this.minutes = 0;
    }

    public void setTime(int hours, int minutes) throws Exception {
        if (hours < 0 || hours > 11)
            throw new Exception("Invalid hour");
        if (minutes < 0 || minutes > 59)
            throw new Exception("Invalid minutes");
        this.hours = hours;
        this.minutes = minutes;
    }

    public void addTime(int hours, int minutes) {
        this.hours = (this.hours + hours) % 12;
        this.minutes = (this.minutes + minutes) % 60;
    }

    public int[] getTime() {
        return new int[]{this.hours, this.minutes};
    }
}
