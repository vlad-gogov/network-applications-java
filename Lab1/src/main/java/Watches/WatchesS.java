package Watches;

public class WatchesS {
    private String name;
    private double price;
    private int hours, minutes, seconds;

    public WatchesS(String name_, double price_) {
        name = name_;
        price = price_;
        hours = 0;
        minutes = 0;
        seconds = 0;
    }

    public void setTime(int hours_, int minutes_, int seconds_) throws Exception {
        if (hours_ < 0 || hours_ > 11)
            throw new Exception("Invalid hour");
        if (minutes_ < 0 || minutes_ > 59)
            throw new Exception("Invalid minutes");
        if (seconds_ < 0 || seconds_ > 59)
            throw new Exception("Invalid seconds");
        hours = hours_;
        minutes = minutes_;
        seconds = seconds_;
    }

    public void addTime(int hours_, int minutes_, int seconds_) {
        hours = (hours + hours_) % 12;
        minutes = (minutes + minutes_) % 60;
        seconds = (seconds + seconds_) % 60;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int[] getTime() {
        return new int[]{hours, minutes, seconds};
    }
}