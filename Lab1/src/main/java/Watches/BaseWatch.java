package Watches;

public abstract class BaseWatch {
    private String name;
    private double price;

    public BaseWatch(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return this.name;
    }

    public double getPrice() {
        return this.price;
    }
}
