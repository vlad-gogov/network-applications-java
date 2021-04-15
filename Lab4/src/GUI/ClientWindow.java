package GUI;

import Alarm.BAlarm;
import Alarm.EAlarm;
import Alarm.IAlarm;
import Manager.Event;
import Manager.Listener;
import Manager.EventManager;
import Manager.EventType;
import Watch.IWatch;

import javax.swing.*;

public class ClientWindow implements Listener {
    private JPanel panel;
    private JButton addAlarmButton;
    private JButton connectButton;
    private JSpinner spinner_hours;
    private JSpinner spinner_minutes;
    private JSpinner spinner_seconds;
    private JList<String> alarms_trigger;
    private JList<String> message;
    private JLabel label1;
    private JLabel hours;
    private JLabel minutes;
    private JLabel seconds;
    private DefaultListModel model_alarms;
    private DefaultListModel model_message;

    protected EventManager eventManager = new EventManager();

    private boolean connect = false;

    public ClientWindow() {
        model_alarms = new DefaultListModel();
        model_message = new DefaultListModel();
        alarms_trigger.setModel(model_alarms);
        message.setModel(model_message);

        connectButton.addActionListener(e -> connect());
        addAlarmButton.addActionListener(e -> addAlarm());
    }

    public void addAlarm() {
            int hours = (int)spinner_hours.getValue();
            int minutes = (int)spinner_minutes.getValue();
            int seconds = (int)spinner_seconds.getValue();
            IAlarm alarm = BAlarm.build(EAlarm.HMSAlarm);
            try {
                alarm.setHours(hours);
                alarm.setMinutes(minutes);
                alarm.setSeconds(seconds);
            } catch (Exception e) {
                e.printStackTrace();
            }
            eventManager.notify(new Event(EventType.ADD_ALARM, alarm));
        }

    public void connect() {
        if (!this.connect) {
            eventManager.notify(new Event(EventType.CLIENT_CONNECT));
            addAlarmButton.setEnabled(true);
            connectButton.setText("Disconnect");
            this.connect = true;
        } else {
            eventManager.notify(new Event(EventType.CLIENT_DISCONNECT));
            addAlarmButton.setEnabled(false);
            connectButton.setText("Connect");
            model_message.removeAllElements();
            model_alarms.removeAllElements();
            this.connect = false;
        }

    }

    public void addSubscriber(Listener listener) {
        eventManager.subscribe(listener);
    }

    @Override
    public void signal(Event event) {
        if (event.type == EventType.UPDATE_WATCH) {
            IWatch watch = event.watch;
            int seconds_ = 0;
            try {
                seconds_ = watch.getSeconds();
            } catch (Exception e) {
                e.printStackTrace();
            }
            hours.setText(String.valueOf(watch.getHours()));
            minutes.setText(String.valueOf(watch.getMinutes()));
            seconds.setText(String.valueOf(seconds_));
            return;
        }
        if (event.type == EventType.ADD_ALARM) {
            int seconds_ = 0;
            try {
                seconds_ = event.alarm.getSeconds();
            } catch (Exception e) {
                e.printStackTrace();
            }
            model_message.addElement("Alarm: " + event.alarm.getHours() + ":" + event.alarm.getMinutes() + ":" + seconds_);
            return;
        }
        if (event.type == EventType.ALARM_TRIGGER) {
            IAlarm alarm = event.alarm;
            int seconds_ = 0;
            try {
                seconds_ = alarm.getSeconds();
            } catch (Exception e) {
                e.printStackTrace();
            }
            model_alarms.addElement("Alarm: " + alarm.getHours() + ":" + alarm.getMinutes() + ":" + seconds_);
            return;
        }
    }

    public JPanel getPanel() {
        return panel;
    }
}
