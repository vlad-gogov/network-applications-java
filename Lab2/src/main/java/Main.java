public class Main {
    public static void main(String[] args) {
        ITimer time = BTimer.build(timerType.HMSWatches, "Rolex");
        if (time == null) {
            return;
        }
        try {
            time.setHours(5);
            time.setMinutes(10);
            time.setSeconds(48);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        try {
            time.addHours(5);
            time.addMinutes(10);
            time.addSeconds(48);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        System.out.println(time);
    }
}
