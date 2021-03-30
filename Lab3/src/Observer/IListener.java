package Observer;

import java.util.concurrent.Callable;

public interface IListener {
    void signal(AEvent event);
    void setSlot(ICallable slot);
}
