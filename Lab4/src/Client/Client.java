package Client;

import GUI.ClientWindow;

import javax.swing.*;

public class Client {
    private ClientController controller = new ClientController();

    public Client() {
    }

    public void relation(ClientWindow window) {
        window.addSubscriber(controller);
        controller.addWatchSubscriber(window);
        controller.addSubscriber(window);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Client Window");
        Client client = new Client();
        ClientWindow window = new ClientWindow();
        client.relation(window);
        frame.setContentPane(window.getPanel());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

}
