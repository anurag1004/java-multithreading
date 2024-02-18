import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

class Producer implements Runnable{
    private final BlockingQueue<Integer> queue;

    public Producer(BlockingQueue<Integer> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        try{
            for(int i=0;i<5;i++){
                Thread.sleep(200);
                queue.put(i);
                System.out.println("Produce: "+i);
            }
        }catch (InterruptedException e){
            Thread.currentThread().interrupt();
        }
    }
}
class Consumer implements Runnable{
    private final BlockingQueue<Integer> queue;

    public Consumer(BlockingQueue<Integer> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        try{
            for(int i=0;i<5;i++){
                Thread.sleep(1000);
                int consumerData = queue.take();
                System.out.println("consumed: "+consumerData);
            }
        }catch (InterruptedException e){
            Thread.currentThread().interrupt();
        }
    }
}
public class Main {
    public static void main(String[] args) throws InterruptedException {
        BlockingQueue<Integer> queue = new LinkedBlockingQueue<>(3);
        Thread pThread = new Thread(new Producer(queue), "producer");
        Thread cThread = new Thread(new Consumer(queue), "consumer");
        pThread.start();
        cThread.start();
    }
}
