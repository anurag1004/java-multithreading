public class MyThread extends Thread{
    private final int id;
    public MyThread(int id, boolean isDaemon){
        // thread name will be given by JVM
        setDaemon(isDaemon);
        this.id = id;
    }
    public MyThread(String threadName, int id, boolean isDaemon){
        super(threadName);
        setDaemon(isDaemon);
        this.id = id;
    }
    public void run(){
        System.out.println(Thread.currentThread());
        for(int i=0;i<2;i++){
            System.out.println("Thread "+id+" : "+i);
        }
    }
}
