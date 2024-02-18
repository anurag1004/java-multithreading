import java.util.ArrayList;
import java.util.List;

public class Consumer implements Runnable{
    BlockingQueue queue;
    int consumeInterval;
    List<Integer> consumedData;
    public Consumer(BlockingQueue queue, int consumeInterval){
        this.queue = queue;
        this.consumeInterval = consumeInterval;
        this.consumedData = new ArrayList<>();
    }
    @Override
    public void run() {
        while(true){
            try{
                Thread.sleep(consumeInterval);
                int consumed = queue.remove();
                consumedData.add(queue.remove());
                System.out.printf("Consumed: %d, total: %d\n",consumed, consumedData.size());
            }catch (InterruptedException e){
                Thread.currentThread().interrupt();
            }
        }
    }
}
