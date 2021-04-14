package Watch;


public class TimeController {
    IWatch watch;
    boolean pause = false;
    boolean started = false;
    Thread thread;

    public TimeController(IWatch watch) {
        this.watch = watch;
    }

    public void start() {
        started = true;
        if (thread == null) {
            thread = new Thread(() -> {
                try {
                    int tick = watch.getTick();
                    while (true) {
                        if (pause) {
                            synchronized (watch) {
                                watch.wait();
                            }
                            pause = false;
                        }
                        Thread.sleep(tick);
                        watch.tick();
                    }
                } catch (InterruptedException e) {
                }
            });
            thread.start();
        } else {
            cont();
        }
    }

    public void stop() {
        started = false;
        if (thread != null) {
            thread.interrupt();
            thread = null;
            pause = false;
            resetTime();
        }
    }

    public void resetTime() {
        try {
            this.watch.setHours(0);
            this.watch.setMinutes(0);
            this.watch.setSeconds(0);
        } catch (Exception e) {}
    }

    public void pause() {
        started = false;
        pause = true;
    }

    public void cont() {
        started = true;
        synchronized (watch) {
            watch.notifyAll();
        }
    }

    public boolean isStart() {
        return started;
    }
}
