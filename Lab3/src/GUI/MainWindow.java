package GUI;

import javax.swing.*;

public class MainWindow {

    private JButton createWatchButton;
    private JTextField watch_name;
    private JPanel panel;

    public MainWindow() {
        createWatchButton.addActionListener(e -> createClockWindow());
    }

    void createClockWindow() {
        String name = watch_name.getText();
        if (name.isEmpty())
            return;
        JFrame frame = new JFrame(name);
        frame.setContentPane(new WatchWindow().getPanel());
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    public JPanel getPanel() {
        return panel;
    }
}
