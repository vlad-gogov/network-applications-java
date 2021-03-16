public interface ITimer {
    void setHours(int hours_) throws Exception;
    void setMinutes(int minutes_) throws Exception;
    void setSeconds(int seconds_) throws Exception;
    void addHours(int hours_);
    void addMinutes(int minutes_);
    void addSeconds(int seconds_) throws Exception;
}
