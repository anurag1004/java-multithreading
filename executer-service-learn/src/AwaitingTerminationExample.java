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
        ExecutorService exec = Executors.newFixedThreadPool(2);
        for (int i = 0; i < 7; i++) {
            exec.submit(new SimpleTask());
        }
        Thread.sleep(2000);
        // stop gracefully
        exec.shutdown();
        int timeout = 15;
        boolean isTerminatedGracefully = exec.awaitTermination(timeout, TimeUnit.SECONDS); // wait for 10secs for executer to terminate
        if(!isTerminatedGracefully){
            System.out.printf("Executor service didnot terminated within %dsecs\n",timeout);
        }else{
            System.out.printf("Executor terminated gracefully within %dseconds\n",timeout);
        }
        System.out.println("main exits");
    }
}
