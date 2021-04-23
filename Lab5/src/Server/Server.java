package Server;

import GUI.ServerWindow;

import javax.swing.*;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    int count_clients;

    int port = 3159;
    InetAddress host;

    ServerWindow window;
    ServerModel model = new ServerModel();

    public Server() {
        try {
            host = InetAddress.getLocalHost();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addWindow(ServerWindow window) {
        this.window = window;
        model.addWatchSubscriber(window);
        model.addSubscriber(window);
        window.addSubscriber(model);
    }

    public void runServer() {
        try {
            model.getDBAlarms();
            ServerSocket s_socket = new ServerSocket(port, 0, host);
            System.out.println("Server started");

            while (true) {
                Socket c_socket = s_socket.accept();

                count_clients++;
                new ServerContoler(c_socket, model, window);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Server Window");
        Server server = new Server();
        ServerWindow window = new ServerWindow();
        server.addWindow(window);
        frame.setContentPane(window.getPanel());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        server.runServer();
    }
}
