public class Main {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("main starts");
        BlockingQueue queue = new BlockingQueue(2);
        Producer producer = new Producer(queue, 1000);
        Consumer consumer = new Consumer(queue, 100);
        Thread pThread = new Thread(producer, "producer");
        Thread cThread = new Thread(consumer, "consumer");
        pThread.start();
//        Thread.sleep(6000);
        // after some time start consumer
        cThread.start();
//        Thread.sleep(6000);
//        pThread.interrupt(); // after some time, interrupt the pThread
//        System.out.println("main exits");
    }
}
