package GUI;

import Alarm.BAlarm;
import Alarm.EAlarm;
import Alarm.IAlarm;
import Observer.AEvent;
import Observer.ICallable;
import Observer.IListener;
import Observer.IPublisher;
import Watch.*;

import javax.swing.*;
import java.util.LinkedList;

public class WatchWindow implements IListener {
    private JSpinner spinner_time_hours;
    private JSpinner spinner_time_minutes;
    private JSpinner spinner_time_seconds;
    private JLabel time_hours;
    private JLabel time_minutes;
    private JLabel time_seconds;
    private JButton setTimeButton;
    private JButton setAlarmButton;
    private JSpinner spinner_alarm_hours;
    private JSpinner spinner_alarm_minutes;
    private JSpinner spinner_alarm_seconds;
    private JTextField notify_alarm;
    private JPanel panel;
    private JButton startButton;
    private JButton stopButton;
    private JButton pauseButton;
    private JList alarms_list;
    private SpinnerModel model;

    private IWatch watch;
    private TimeController controller;
    private LinkedList<IAlarm> alarms;
    private ICallable alarm_slot;
    private DefaultListModel model_alarms;

    public WatchWindow() {
        watch = BWatch.build(EWatch.HMSWatch);
        alarms = new LinkedList<IAlarm>();
        int min = 0;
        int max = 59;
        int step = 1;
        int init = 0;
        try {
            model = new SpinnerNumberModel(init, min, max, step);
            spinner_time_seconds.setModel(model);
            model = new SpinnerNumberModel(init, min, max, step);
            spinner_time_minutes.setModel(model);
            model = new SpinnerNumberModel(init, min, max, step);
            spinner_alarm_seconds.setModel(model);
            model = new SpinnerNumberModel(init, min, max, step);
            spinner_alarm_minutes.setModel(model);
            max = 11;
            model = new SpinnerNumberModel(init, min, max, step);
            spinner_time_hours.setModel(model);
            model = new SpinnerNumberModel(init, min, max, step);
            spinner_alarm_hours.setModel(model);
        } catch (Exception e) {
        }
        controller = new TimeController(watch);
        model_alarms = new DefaultListModel();
        alarms_list.setModel(model_alarms);
        alarm_slot = () -> {
            try {
                String text = "Alarm " + watch.getHours() + ":" + watch.getMinutes() + ":" + watch.getSeconds();
                notify_alarm.setText(text);
                JFrame alarm_alert = new JFrame("Alert alarm");
                alarm_alert.setContentPane(new AlertAlarm(text).getPanel());
                alarm_alert.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                alarm_alert.pack();
                alarm_alert.setVisible(true);
            } catch (Exception e) {
            }
        };

        IPublisher publisher = (IPublisher) watch;
        publisher.addListener(this);
        controller = new TimeController(watch);

        setTimeButton.addActionListener(e -> setTime());
        startButton.addActionListener(e -> controller.start());
        pauseButton.addActionListener(e -> controller.pause());
        stopButton.addActionListener(e -> controller.stop());
        setAlarmButton.addActionListener(e -> addAlarm());
    }

    @Override
    public void signal(AEvent event) {
        EventTime time = (EventTime)event;
        time_hours.setText(String.valueOf(time.hours));
        time_minutes.setText(String.valueOf(time.minutes));
        time_seconds.setText(String.valueOf(time.seconds));
    }

    @Override
    public void setSlot(ICallable slot) {
    }

    public void setTime() {
        int hours = (int)spinner_time_hours.getValue();
        int minutes = (int)spinner_time_minutes.getValue();
        int seconds = (int)spinner_time_seconds.getValue();
        try {
            watch.setHours(hours);
            watch.setMinutes(minutes);
            watch.setSeconds(seconds);
        } catch (Exception e) {
        }
    }

    public void addAlarm() {
        int hours = (int)spinner_alarm_hours.getValue();
        int minutes = (int)spinner_alarm_minutes.getValue();
        int seconds = (int)spinner_alarm_seconds.getValue();
        IAlarm alarm = BAlarm.build(EAlarm.HMSAlarm);
        try {
            alarm.setHours(hours);
            alarm.setMinutes(minutes);
            alarm.setSeconds(seconds);
        } catch (Exception e) {
        }
        alarms.add(alarm);
        IPublisher pub = (IPublisher) watch;
        IListener lis = (IListener)alarm;
        lis.setSlot(alarm_slot);
        pub.addListener(lis);
        model_alarms.addElement(hours + ":" + minutes + ":" + seconds);
    }

    public JPanel getPanel() {
        return panel;
    }

}


