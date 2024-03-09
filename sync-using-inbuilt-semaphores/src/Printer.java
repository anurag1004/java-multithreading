import java.util.concurrent.Semaphore;

public class Printer {
    Semaphore s1 = new Semaphore(0);
    Semaphore s2 = new Semaphore(0);
    public void printFirst() {
        System.out.println("First");
        s1.release();
    }

    public void printSecond() throws InterruptedException {
        s1.acquire();
        System.out.println("Second");
        s2.release();
        s1.release();

    }

    public void printThird() throws InterruptedException {
        s2.acquire();
        System.out.println("Third");
        s2.release();
    }
}
