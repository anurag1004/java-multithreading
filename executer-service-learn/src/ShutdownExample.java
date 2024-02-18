import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class LongRunningTask implements Runnable{

    @Override
    public void run() {
        try{
            Thread.sleep(4000);
        }catch (InterruptedException ignored){}
        System.out.println(Thread.currentThread().getName()+" finished!");
    }
}
public class ShutdownExample {
    public static void main(String[] args) throws InterruptedException {
        ExecutorService executor = Executors.newFixedThreadPool(2);
        for(int i=0;i<7;i++){
            executor.submit(new LongRunningTask());
        }
        Thread.sleep(8000);
        // attempt to shut down immediately
        List<Runnable> uncompletedTasks = executor.shutdownNow(); // non-blocking
        for(Runnable task: uncompletedTasks){
            System.out.println(task.toString());
        }
    }
}
