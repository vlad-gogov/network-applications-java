package Timer;

public interface ITimer {
    public void setHours(int hours) throws Exception;
    public void setMinutes(int minutes) throws Exception;
    public void setSeconds(int seconds) throws Exception;

    public int getHours();
    public int getMinutes();
    public int getSeconds() throws Exception;
}
