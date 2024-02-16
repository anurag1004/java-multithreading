import java.util.List;

class SomeClass{
    int data;
    Semaphore semaphore;

    public SomeClass(int data, int cnt){
        this.data = data;
        this.semaphore = new Semaphore(cnt);// counting semaphore
    }
    public void incr(int i){
        System.out.println(Thread.currentThread().getName()+" waiting:incr");
        semaphore.waits(); // entry
        System.out.println(Thread.currentThread().getName()+" enters:incr");
        this.data += i; // cs
        try {
            Thread.sleep(4000);
        }catch (Exception ignored){}
        semaphore.signals(); // exit
        System.out.println(Thread.currentThread().getName()+" exits:incr");
    }
    public void decr(int i){
        System.out.println(Thread.currentThread().getName()+" waiting:decr");
        semaphore.waits();
        System.out.println(Thread.currentThread().getName()+" enters:decr");
        this.data -= i;
        try {
            Thread.sleep(4000);
        }catch (Exception ignored){}
        semaphore.signals();
        System.out.println(Thread.currentThread().getName()+" exits:decr");
    }
}
public class Main {
    public static void main(String[] args) {
        System.out.println("main starts");
        int n = 0;
        // at a time max n threads are allowed to enter cs
        SomeClass shared = new SomeClass(10,n);
        Thread t1 = new Thread(()->{
            for(int i=1;i<=1;i++){
                shared.incr(i);
            }
        },"t1");
        Thread t2 = new Thread(()->{
            for(int i=1;i<=1;i++){
                shared.incr(i);
            }
        },"t2");
        Thread t3 = new Thread(()->{
            for(int i=1;i<=1;i++){
                shared.decr(i);
            }
        },"t3");
        Thread t4 = new Thread(()->{
            for(int i=1;i<=1;i++){
                shared.decr(i);
            }
        },"t4");
        List<Thread> threadList = List.of(t1,t2,t3,t4);
        threadList.forEach(Thread::start);
        // wait for both th threads to finish the jobs
        threadList.forEach(thread -> {
            try {
                thread.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        System.out.println("shared: "+shared.data);
        System.out.println("main exits");
    }
}
