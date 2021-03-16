public class BTimer {
    public static ITimer build(timerType type, String name) {
        if (type == timerType.HMWatches)
            return new HMWatches(name);
        else if (type == timerType.HMSWatches)
            return new HMSWatches(name);
        else
            return null;
    }
}
