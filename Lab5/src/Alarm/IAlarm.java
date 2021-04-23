package Alarm;

import Manager.Listener;
import Timer.ITimer;

public interface IAlarm extends ITimer, Listener {
    public void addSubscriber(Listener eventListener);
    public void removeSubscriber(Listener eventListener);
    public String toString();
    public boolean equals(IAlarm alarm);
    public int getId();
    public void setId(int id);
}
