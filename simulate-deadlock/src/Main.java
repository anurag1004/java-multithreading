public class Main {
    public static void main(String[] args) {
        Object lock1 = new Object();
        Object lock2 = new Object();
        Thread t1 = new Thread(()->{
            synchronized (lock1){
                try {
                    Thread.sleep(1000);
                }catch (InterruptedException ignored){}
                synchronized (lock2){
                    System.out.println(Thread.currentThread().getName()+" acquired lock2");
                }
            }
        },"t1");
        Thread t2 = new Thread(()->{
            synchronized (lock2){
                try {
                    Thread.sleep(1000);
                }catch (InterruptedException ignored){}
                synchronized (lock1){
                    System.out.println(Thread.currentThread().getName()+" acquired lock1");
                }
            }
        },"t2");
        t1.start();
        t2.start();

    }
}
