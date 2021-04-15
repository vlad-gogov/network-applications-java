package Server;

import Alarm.IAlarm;
import Manager.Event;
import Manager.Listener;
import Manager.EventManager;
import Manager.EventType;
import Watch.BWatch;
import Watch.EWatch;
import Watch.IWatch;
import Watch.TimeController;

import java.util.LinkedList;

public class ServerModel implements Listener {
    protected EventManager eventManager = new EventManager();

    private IWatch watch = BWatch.build(EWatch.HMSWatch);
    private TimeController controller = new TimeController(watch);
    private LinkedList<IAlarm> alarms = new LinkedList<IAlarm>();

    public ServerModel() {
    }

    public void addSubscriber(Listener listener) {
        eventManager.subscribe(listener);
    }

    public void addAlarm(IAlarm alarm) {
        alarm.addSubcriber(this);
        addWatchSubscriber(alarm);
        eventManager.notify(new Event(EventType.ADD_ALARM, alarm));
        alarms.add(alarm);
    }

    public LinkedList<IAlarm> getAllAlarms() {
        return alarms;
    }

    public IWatch getWatch() {
        return watch;
    }

    public boolean getStartWatch() {
        return controller.isStart();
    }

    public void addWatchSubscriber(Listener listener) {
        watch.addListener(listener);
    }

    @Override
    public void signal(Event event) {
        if (event.type == EventType.SET_WATCH) {
            try {
                watch.setHours(event.watch.getHours());
                watch.setMinutes(event.watch.getMinutes());
                watch.setSeconds(event.watch.getSeconds());
            } catch (Exception e) {
                e.printStackTrace();
            }
            eventManager.notify(new Event(EventType.UPDATE_WATCH, watch, controller.isStart()));
            return;
        }
        if (event.type == EventType.START_WATCH) {
            eventManager.notify(new Event(EventType.START_WATCH_CLIENT, watch));
            controller.start();
            return;
        }
        if (event.type == EventType.STOP_WATCH) {
            eventManager.notify(new Event(EventType.STOP_WATCH_CLIENT, watch));
            controller.stop();
            return;
        }
        if (event.type == EventType.PAUSE_WATCH) {
            eventManager.notify(new Event(EventType.PAUSE_WATCH_CLIENT, watch));
            controller.pause();
            return;
        }
        if (event.type == EventType.CONTINUE_WATCH) {
            eventManager.notify(new Event(EventType.CONTINUE_WATCH_CLIENT, watch));
            controller.cont();
            return;
        }
        if (event.type == EventType.ALARM_TRIGGER) {
            eventManager.notify(event);
            return;
        }
        if (event.type == EventType.UPDATE_WATCH) {
            IWatch watch_ = event.watch;
            try {
                watch.setHours(watch_.getHours());
                watch.setMinutes(watch_.getMinutes());
                watch.setSeconds(watch_.getSeconds());
            } catch (Exception e) {
                e.printStackTrace();
            }
            return;
        }
    }
}
