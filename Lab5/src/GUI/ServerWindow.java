package GUI;


import Alarm.IAlarm;
import Manager.Event;
import Manager.Listener;
import Manager.EventManager;
import Manager.EventType;
import Watch.*;

import javax.swing.*;

public class ServerWindow implements Listener {
    protected EventManager eventManager = new EventManager();

    private JPanel panel;
    private JSpinner spinner_hours;
    private JSpinner spinner_minutes;
    private JSpinner spinner_seconds;
    private SpinnerNumberModel model;
    private JButton setTimeButton;
    private JList<IAlarm> alarms;
    private JButton startButton;
    private JButton pauseButton;
    private JButton stopButton;
    private JButton continueButton;
    private JLabel hours_server;
    private JLabel minutes_server;
    private JLabel seconds_server;
    private JLabel label1;
    private JList<IAlarm> message;

    private DefaultListModel<IAlarm> model_alarm;
    private DefaultListModel<IAlarm> model_message;

    public ServerWindow() {
        model_alarm = new DefaultListModel<>();
        model_message = new DefaultListModel<>();
        message.setModel(model_message);
        alarms.setModel(model_alarm);

        int min = 0;
        int max = 59;
        int step = 1;
        int init = 0;
        try {
            model = new SpinnerNumberModel(init, min, max, step);
            spinner_seconds.setModel(model);
            model = new SpinnerNumberModel(init, min, max, step);
            spinner_minutes.setModel(model);
            max = 11;
            model = new SpinnerNumberModel(init, min, max, step);
            spinner_hours.setModel(model);
        } catch (Exception e) {
        }

        setTimeButton.addActionListener(e -> setTime());
        startButton.addActionListener(e -> eventManager.notify(new Event(EventType.START_WATCH)));
        stopButton.addActionListener(e -> eventManager.notify(new Event(EventType.STOP_WATCH)));
        continueButton.addActionListener(e -> eventManager.notify(new Event(EventType.CONTINUE_WATCH)));
        pauseButton.addActionListener(e -> eventManager.notify(new Event(EventType.PAUSE_WATCH)));
    }

    public void setTime() {
        int hours = (int)spinner_hours.getValue();
        int minutes = (int)spinner_minutes.getValue();
        int seconds = (int)spinner_seconds.getValue();
        eventManager.notify(new Event(EventType.SET_WATCH, new HMSWatch(hours, minutes, seconds)));

        hours_server.setText(String.valueOf(hours));
        minutes_server.setText(String.valueOf(minutes));
        seconds_server.setText(String.valueOf(seconds));
    }

    public JPanel getPanel() {
        return panel;
    }

    public void addSubscriber(Listener listener) {
        eventManager.subscribe(listener);
    }

    @Override
    public void signal(Event event) {
        if (event.type == EventType.UPDATE_WATCH) {
            hours_server.setText(String.valueOf(event.watch.getHours()));
            minutes_server.setText(String.valueOf(event.watch.getMinutes()));
            int seconds = 0;
            try {
                seconds = event.watch.getSeconds();
            } catch (Exception e) {
                e.printStackTrace();
            }
            seconds_server.setText(String.valueOf(seconds));
            return;
        }
        if (event.type == EventType.ADD_ALARM) {
            model_alarm.addElement(event.alarm);
            return;
        }
        if (event.type == EventType.DELETE_ALARM) {
            for (int i = 0; i < model_alarm.size(); i++) {
                if (model_alarm.get(i).equals(event.alarm))
                    model_alarm.remove(i);
            }
            return;
        }
        if (event.type == EventType.ALARM_TRIGGER) {
            model_message.addElement(event.alarm);
            return;
        }
    }

}
