package Watch;

import Timer.ITimer;

public interface IWatch extends ITimer {
    public void addHours(int hours) throws Exception;
    public void addMinutes(int minutes) throws Exception;
    public void addSeconds(int seconds) throws Exception;
}
