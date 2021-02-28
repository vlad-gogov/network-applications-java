package Watches;

public class Watches {
    private String name;
    private double price;
    private int hours, minutes;

    public Watches(String name_, double price_) {
        name = name_;
        price = price_;
        hours = 0;
        minutes = 0;
    }

    public void setTime(int hours_, int minutes_) throws Exception {
        if (hours_ < 0 || hours_ > 11)
            throw new Exception("Invalid hour");
        if (minutes_ < 0 || minutes_ > 59)
            throw new Exception("Invalid minutes");
        hours = hours_;
        minutes = minutes_;
    }

    public void addTime(int hours_, int minutes_) {
        hours = (hours + hours_) % 12;
        minutes = (minutes + minutes_) % 60;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int[] getTime() {
        return new int[]{hours, minutes};
    }
}
