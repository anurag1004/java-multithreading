public class Main {
    public static void main(String[] args) {
        Printer printer = new Printer();
        Thread t1 = new Thread(()->{
           printer.printFirst();
        });
        Thread t2 = new Thread(()->{
            try {
                printer.printSecond();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        Thread t3 = new Thread(()->{
            try {
                printer.printThird();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        t1.start(); t2.start(); t3.start();

    }
}
