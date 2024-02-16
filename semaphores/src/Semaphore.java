import java.util.concurrent.atomic.AtomicInteger;

public class Semaphore {
    private final AtomicInteger counter;
    public Semaphore(int size){
        counter = new AtomicInteger(size);
    }
    public void acquire(){
        synchronized (this) {
            while (counter.get() == 0) {
                try {
                    wait();
                } catch (InterruptedException interruptedException) {
                    Thread.currentThread().interrupt();
                }
            }
            counter.decrementAndGet();
        }
    }
    public void release(){
        synchronized (this){
            counter.incrementAndGet();
            notifyAll();
        }
    }
}
