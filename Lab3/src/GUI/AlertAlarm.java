package GUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AlertAlarm extends JFrame{
    private JPanel panel;
    private JLabel text_alarm;

    public AlertAlarm(String text) {
        text_alarm.setText(text);
    }

    public JPanel getPanel() {
        return panel;
    }
}
