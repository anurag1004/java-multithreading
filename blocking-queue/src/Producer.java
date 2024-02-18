import java.util.Random;

public class Producer implements Runnable{
    final BlockingQueue queue;
    int producerInterval;
    public Producer(BlockingQueue queue, int producerInterval){
        this.queue = queue;
        this.producerInterval = producerInterval;
    }
    @Override
    public void run() {
        int cnt = 0;
        while(true) {
            try {
                Thread.sleep(producerInterval);
                int data = new Random().nextInt(100);
                queue.add(data);
                cnt++;
                System.out.printf("Produce: %d, total: %d\n",data, cnt);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
