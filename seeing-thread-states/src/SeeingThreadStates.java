public class SeeingThreadStates {
    public static void main(String[] args)  {
        Thread t1 = new Thread(()->{
            try{
                Thread.sleep(1);
            }catch (InterruptedException e){
                Thread.currentThread().interrupt();
            }
        },"t1");
        t1.start();
        while(true){
            Thread.State state = t1.getState();
            System.out.println(state.toString());
            if(state.equals(Thread.State.TERMINATED)){
                break;
            }
        }
    }
}
