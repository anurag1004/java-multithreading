import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

class SimpleTask implements Runnable{
    @Override
    public void run() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println(Thread.currentThread().getName()+" completed!");
    }
}
public class AwaitingTerminationExample {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("main starts");
        ExecutorService exec = Executors.newFixedThreadPool(3);
        for (int i = 0; i < 15; i++) {
            exec.submit(new SimpleTask());
        }
        Thread.sleep(2000);
        // stop gracefully
        exec.shutdown();
        boolean isTerminatedGracefully = exec.awaitTermination(10, TimeUnit.SECONDS); // wait for 10secs for executer to terminate
        if(isTerminatedGracefully){
            System.out.println("Executor service terminated within 10secs");
        }else{
            System.out.println("Executor terminated gracefully within 10 seconds");
        }
        System.out.println("main exits");
    }
}
