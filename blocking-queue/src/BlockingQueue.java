import java.util.LinkedList;
import java.util.Queue;

public class BlockingQueue {
    final Queue<Integer> queue;
    final int bufferSize;
    public BlockingQueue(int bufferSize){
        this.queue = new LinkedList<>();
        this.bufferSize = bufferSize;
    }
    public int remove(){
        synchronized (queue){
            while (queue.isEmpty()){
                try{
                    queue.wait();
                }catch (InterruptedException e){
                    Thread.currentThread().interrupt();
                }
            }
            int removedItem = queue.poll();
            queue.notifyAll();
            return removedItem;
        }
    }
    public void add(int item){
        synchronized (queue){
            while(queue.size()==bufferSize){
                try{
                    queue.wait();
                }catch (InterruptedException e){
                    Thread.currentThread().interrupt();
                }
            }
            queue.add(item);
            queue.notifyAll();
        }
    }
}
