package Client;

import GUI.ClientWindow;
import Manager.*;
import Watch.BWatch;
import Watch.EWatch;
import Watch.IWatch;
import Watch.TimeController;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class ClientController implements Listener {
    public EventManager eventManager = new EventManager();
    IWatch watch = BWatch.build(EWatch.HMSWatch);
    TimeController watchController = null;
    Thread thread;

    Gson gson = new GsonBuilder().registerTypeAdapter(Event.class, new EventSerialize())
                    .registerTypeAdapter(Event.class, new EventDeserialize())
                    .setPrettyPrinting().create();

    private int port = 3159;
    private InetAddress host;

    private Socket c_socket;
    private OutputStream ostream;
    private DataOutputStream dostream;
    private InputStream istream;
    private DataInputStream distream;

    public void ClientController() {
    }

    public void addWatchSubscriber(ClientWindow window) {
        watch.addListener(window);
    }

    public synchronized void addAlarm(Event event) {
        eventManager.notify(event);
    }


    public void connect() {
        try {
            host = InetAddress.getLocalHost();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }

        try {
            c_socket = new Socket(host, port);
            System.out.println("Client started");

            ostream = c_socket.getOutputStream();
            dostream = new DataOutputStream(ostream);

            thread = new Thread(() -> {
                try {
                    istream = c_socket.getInputStream();
                    distream = new DataInputStream(istream);
                    while (true) {
                        String data = distream.readUTF();
                        Event event = gson.fromJson(data, Event.class);
                        if (event.type == EventType.ADD_ALARM) {
                            addAlarm(event);
                            continue;
                        }
                        if (event.type == EventType.DELETE_ALARM) {
                            eventManager.notify(event);
                            continue;
                        }
                        if (event.type == EventType.UPDATE_WATCH) {
                            if (watchController != null)
                                watchController.stop();
                            watch = event.watch;
                            watch.addListener(this);
                            watchController = new TimeController(watch);
                            if (event.run)
                                watchController.start();
                            eventManager.notify(event);
                            continue;
                        }
                        if (event.type == EventType.START_WATCH_CLIENT) {
                            watchController.start();
                            continue;
                        }
                        if (event.type == EventType.STOP_WATCH_CLIENT) {
                            watchController.stop();
                            continue;
                        }
                        if (event.type == EventType.PAUSE_WATCH_CLIENT) {
                            watchController.pause();
                            continue;
                        }
                        if (event.type == EventType.CONTINUE_WATCH_CLIENT) {
                            watchController.cont();
                            continue;
                        }
                        if (event.type == EventType.ALARM_TRIGGER) {
                            eventManager.notify(event);
                            continue;
                        }
                        if (event.type == EventType.ADD_MESSAGE) {
                            eventManager.notify(event);
                            continue;
                        }
                    }

                } catch (IOException e) {
                }
            });
        thread.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addSubscriber(Listener eventListener) {
        eventManager.subscribe(eventListener);
    }

    @Override
    public void signal(Event event) {
        if (event.type == EventType.CLIENT_CONNECT) {
            connect();
            return;
        }
        if (event.type == EventType.CLIENT_DISCONNECT) {
            try {
                c_socket.close();
                watchController.stop();
            } catch (IOException e) {
            }
            return;
        }
        if (event.type == EventType.ADD_ALARM) {
            send(event);
            return;
        }
        if (event.type == EventType.DELETE_ALARM) {
            send(event);
            return;
        }
        if (event.type == EventType.UPDATE_WATCH) {
            eventManager.notify(event);
            return;
        }
    }

    private void send(Event event) {
        if (thread != null) {
            String data = gson.toJson(event);
            System.out.println(data);
            try {
                dostream.writeUTF(data);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
