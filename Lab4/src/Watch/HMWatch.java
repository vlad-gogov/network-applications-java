package Watch;

import Manager.EventManager;
import Manager.Event;
import Manager.Listener;
import Manager.EventType;

public class HMWatch implements IWatch {
    protected int hours = 0;
    protected int minutes = 0;

    EventManager manager = new EventManager();

    @Override
    public void setHours(int hours) throws Exception{
        if (hours < 0|| hours > 11)
            throw new Exception("Invalid hours");
        this.hours = hours;
        this.broadcast();
    }

    @Override
    public void setMinutes(int minutes) throws Exception {
        if (minutes < 0 || minutes > 59)
            throw new Exception("Invalid minutes");
        this.minutes = minutes;
        this.broadcast();
    }

    @Override
    public void setSeconds(int seconds) throws Exception {
        throw new Exception("Seconds are unsupported");
    }

    @Override
    public void addHours(int hours) throws Exception {
        if (hours < 0)
            throw new Exception("Invalid hours");
        this.hours = (this.hours + hours) % 12;
        this.broadcast();
    }

    @Override
    public void addMinutes(int minutes) throws Exception {
        if (minutes < 0)
            throw new Exception("Invalid minutes");
        int m = this.minutes + minutes;
        if (m >= 60) {
            this.minutes = m % 60;
            this.addHours(m / 60);
        } else {
            this.minutes += minutes;
            this.broadcast();
        }
    }

    @Override
    public void addSeconds(int seconds) throws Exception {
        throw new Exception("Seconds are unsupported");
    }

    @Override
    public int getTick() {
        return 60000;
    }

    @Override
    public void tick() {
        try {
            this.addMinutes(1);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getHours() {
        return hours;
    }

    @Override
    public int getMinutes() {
        return minutes;
    }

    @Override
    public int getSeconds() throws Exception {
        throw new Exception("Seconds are unsupported");
    }

    @Override
    public void broadcast() {
        manager.notify(new Event(EventType.UPDATE_WATCH, this));
    }

    @Override
    public void addListener(Listener listener) {
        manager.subscribe(listener);
    }

}
