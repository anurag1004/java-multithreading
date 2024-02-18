import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class RunnableTask1 implements Runnable{

    @Override
    public void run() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println(Thread.currentThread().getName()+" completed!");
    }
}
class RunnableTask2 implements Runnable{

    @Override
    public void run() {
        try {
            Thread.sleep(800);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println(Thread.currentThread().getName()+" completed!");
    }
}
class RunnableTask3 implements Runnable{

    @Override
    public void run() {
        try {
            Thread.sleep(670);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println(Thread.currentThread().getName()+" completed!");
    }
}
public class RunnableTaskExample {
    public static void main(String[] args) {
        System.out.println("main starts");
        ExecutorService executor = Executors.newFixedThreadPool(5); // by default it create non-daemon threads
        List<Runnable> tasks = List.of(new RunnableTask1(),new RunnableTask2(),new RunnableTask3());
        for(Runnable task: tasks){
            executor.submit(task); // non-blocking
        }
        executor.shutdown(); // non-blocking
        for(int i=0;i<5;i++){
            System.out.printf("printing from main %d\n",i);
        }
        System.out.println("main exist");
    }
}
