package Watch;


public class TimeController {
    IWatch time;
    boolean started;

    public TimeController(IWatch time) {
        this.time = time;
    }

    public void start() {
        if (this.started)
            return;
        this.started = true;
        new Thread(() -> {
                try {
                    while (true) {
                        Thread.sleep(1000);
                        if (!this.started)
                            return;
                        time.addSeconds(1);
                    }
                } catch (Exception e) {
                    try {
                        while (true) {
                            Thread.sleep(6000);
                            if (!this.started)
                                return;
                            time.addMinutes(1);
                        }
                    } catch (Exception ee) {}
                }
            }).start();
    }

    public void pause() {
        this.started = false;
    }

    public void stop() {
        pause();
        resetTime();
    }

    public void resetTime() {
        try {
            this.time.setHours(0);
            this.time.setMinutes(0);
            this.time.setSeconds(0);
        } catch (Exception e) {}
    }
}
