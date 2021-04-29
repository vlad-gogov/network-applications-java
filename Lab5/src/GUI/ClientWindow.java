package GUI;

import Alarm.HMSAlarm;
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
    private JList<IAlarm> alarms_trigger;
    private JList<IAlarm> message;
    private JLabel label1;
    private JLabel hours;
    private JLabel minutes;
    private JLabel seconds;
    private JButton deleteAlarmButton;
    private DefaultListModel<IAlarm> model_alarms;
    private DefaultListModel<IAlarm> model_message;

    protected EventManager eventManager = new EventManager();

    private boolean connect = false;

    public ClientWindow() {
        model_alarms = new DefaultListModel();
        model_message = new DefaultListModel();
        alarms_trigger.setModel(model_alarms);
        message.setModel(model_message);

        connectButton.addActionListener(e -> connect());
        addAlarmButton.addActionListener(e -> addAlarm());
        deleteAlarmButton.addActionListener(e -> deleteAlarm());
    }

    public void deleteAlarm() {
        if (model_message.size() == 0)
            return;
        IAlarm alarm = message.getSelectedValue();
        if (alarm == null)
            return;
        eventManager.notify(new Event(EventType.DELETE_ALARM_SIGNAL, alarm));
    }

    public void addAlarm() {
            int hours = (int)spinner_hours.getValue();
            int minutes = (int)spinner_minutes.getValue();
            int seconds = (int)spinner_seconds.getValue();
            HMSAlarm alarm = new HMSAlarm();
            System.out.println(alarm.getId());
            try {
                alarm.setHours(hours);
                alarm.setMinutes(minutes);
                alarm.setSeconds(seconds);
            } catch (Exception e) {
                e.printStackTrace();
            }
            eventManager.notify(new Event(EventType.ADD_ALARM_SIGNAL, alarm));
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
            model_message.addElement(event.alarm);
            return;
        }
        if (event.type == EventType.DELETE_ALARM) {
            IAlarm alarm = event.alarm;
            for (int i = 0; i < model_message.size(); i++) {
                if (model_message.get(i).equals(alarm))
                    model_message.remove(i);
            }
            return;
        }
        if (event.type == EventType.ALARM_TRIGGER) {
            model_alarms.addElement(event.alarm);
            return;
        }
    }

    public JPanel getPanel() {
        return panel;
    }
}
