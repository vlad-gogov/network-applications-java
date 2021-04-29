package Server;

import Alarm.IAlarm;
import GUI.ServerWindow;
import Manager.*;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;
import java.net.Socket;
import java.util.LinkedList;

public class ServerContoler extends Thread implements Listener {

    protected EventManager eventManager = new EventManager();

    int id;
    Socket c_socket;
    ServerModel model;

    OutputStream ostream;
    DataOutputStream dostream;
    InputStream istream;
    DataInputStream distream;

    Gson gson = new GsonBuilder().registerTypeAdapter(Event.class, new EventSerialize())
            .registerTypeAdapter(Event.class, new EventDeserialize())
            .setPrettyPrinting().create();


    public ServerContoler(Socket c_socket, ServerModel model, ServerWindow window) {
        this.c_socket = c_socket;
        this.model = model;
        try {
            ostream = c_socket.getOutputStream();
            dostream = new DataOutputStream(ostream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        model.addSubscriber(this);
        eventManager.subscribe(window);
        sendAlarms(model.getAllAlarms());
        send(new Event(EventType.UPDATE_WATCH, model.getWatch(), model.getStartWatch()));
        this.start();
    }

    @Override
    public void run() {
        try {
            istream = c_socket.getInputStream();
            distream = new DataInputStream(istream);

            while (true) {
                String data = distream.readUTF();
                Event event = gson.fromJson(data, Event.class);
                if (event.type == EventType.ADD_ALARM_SIGNAL) {
                    model.addAlarm(event.alarm);
                }
                if (event.type == EventType.DELETE_ALARM_SIGNAL) {
                    model.deleteAlarm(event.alarm);
                }
                else if (event.type == EventType.CLIENT_DISCONNECT) {
                    break;
                }
            }
            c_socket.close();
        } catch (IOException e) {
        }
    }

    public void send(Event event) {
        String data = gson.toJson(event);
        try {
            dostream.writeUTF(data);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendAlarms(LinkedList<IAlarm> alarms) {
        for (IAlarm alarm : alarms)
            send(new Event(EventType.ADD_ALARM, alarm));
    }

    @Override
    public void signal(Event event) {
        send(event);
    }
}
