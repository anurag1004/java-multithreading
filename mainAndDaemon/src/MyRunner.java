public class MyRunner implements Runnable{
    public void run(){
        for(int i=0;i<2;i++){
            System.out.println("Thread "+Thread.currentThread()+" : "+i);
        }
    }
}
