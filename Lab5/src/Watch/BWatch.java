package Watch;

public class BWatch {
    public static IWatch build(EWatch type) {
        if (type == EWatch.HMWatch)
            return new HMWatch();
        else if (type == EWatch.HMSWatch)
            return new HMSWatch();
        else
            return null;
    }
}
