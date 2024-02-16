public class Main {
    public static void main(String[] args) {
        Stack myStack = new Stack(10);
        Thread t1 = new Thread(()->{
            int cnt = 0;
            while(cnt < 12){
                myStack.push(100);
                cnt++;
            }
        });
        Thread t2 = new Thread(()->{
            int cnt = 0;
            while(cnt < 12){
                myStack.pop();
                cnt++;
            }
        });
        t1.start();
        t2.start();
    }
}
