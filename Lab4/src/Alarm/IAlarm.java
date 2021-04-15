package Alarm;

import Manager.Listener;
import Timer.ITimer;

public interface IAlarm extends ITimer, Listener {
    public void addSubcriber(Listener eventListener);
}
