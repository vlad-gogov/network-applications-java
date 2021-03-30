package Observer;

public interface IPublisher {
    void addListener(IListener listener);
    void removeListener(IListener listener);
}
