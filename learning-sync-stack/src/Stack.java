import java.util.concurrent.atomic.AtomicInteger;

public class Stack {
    private int[] arr;
    private int cap;
    private int top = -1;
    private final Object lock;
    public Stack(int cap){
        this.cap = cap;
        this.arr = new int[cap];
        this.lock = new Object();
    }
    public int push(int item){
        synchronized (lock) {
            if (isFull()) {
                System.out.println("Stack is full!");
                return -1;
            }
            ++top;
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            arr[top] = item;
            return item;
        }
    }
    public int pop(){
        synchronized (lock) {
            if (isEmpty()) {
                System.out.println("Stack is empty!");
                return -1;
            }
            int topItem = arr[top];
            --top;
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return topItem;
        }
    }
    public boolean isEmpty(){
        return top==-1;
    }
    public boolean isFull(){
        return top==cap-1;
    }
}
