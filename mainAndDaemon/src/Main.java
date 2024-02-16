public class Main {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("Main thread");
//        MyThread myThread = new MyThread(1, true); // is a daemon thread, if main exists. this thread also dies
        MyThread myThread = new MyThread(1, false); // is a non-daemon(or user) thread, if main exists. this thread continues until it finishes it task
        MyThread myThread2 = new MyThread("mythread2", 2, false);
        myThread.start(); // not imidiately started, JVM decides
        myThread2.start();
        // Using Runnable
        Thread t3 = new Thread(new MyRunner(),"myRunner"); // name is optional
        // using lambda
        Thread t4 = new Thread(()->{
            for(int i=0;i<2;i++){
                System.out.println("Thread "+Thread.currentThread()+" : "+i);
            }
        },"lamdaRunner");
        t3.start();
        t4.start();
//        Thread.sleep(1000); // sleep the main thread
        System.out.println("Main exiting");

    }
}
