package Alarm;

import Manager.EventListener;
import Timer.ITimer;

public interface IAlarm extends ITimer, EventListener {
    public void addSubcriber(EventListener eventListener);
}
