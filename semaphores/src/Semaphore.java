import java.util.concurrent.atomic.AtomicInteger;

public class Semaphore {
    private final AtomicInteger counter;
    public Semaphore(int size){
        counter = new AtomicInteger(size);
    }
    public void waits(){
        synchronized (this) {
            while (counter.get() == 0) {
                try {
                    counter.wait();
                } catch (Exception ignored) {
                }
            }
            counter.decrementAndGet();
        }
    }
    public void signals(){
        counter.incrementAndGet();
    }
}
