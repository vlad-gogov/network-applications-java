package Watch;

import Manager.Listener;
import Timer.ITimer;

public interface IWatch extends ITimer {
    public void addHours(int hours) throws Exception;
    public void addMinutes(int minutes) throws Exception;
    public void addSeconds(int seconds) throws Exception;
    public int getTick();
    public void tick();

    public void broadcast();
    public void addListener(Listener listener);
}
