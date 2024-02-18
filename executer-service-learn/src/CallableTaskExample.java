import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

class Msg{
    private String msg;

    public Msg(String msg) {
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }
}
class CallableTask implements Callable<Msg>{
    private Msg msg;
    private String inputStr;
    public CallableTask(String inputStr){
        this.inputStr = inputStr;
    }
    public Msg call(){
        try {
            Thread.sleep(1000);
        }catch (InterruptedException exception){
            Thread.currentThread().interrupt();
        }
        return new Msg(this.inputStr);
    }
}
public class CallableTaskExample {
    public static void main(String[] args) {
        ExecutorService executor = Executors.newCachedThreadPool(); // automatically creates and reuse threds, if a thread remains idle for a long time it may be terminated and removed from pool
        List<Callable<Msg>> callableList = List.of(new CallableTask("callable1"), new CallableTask("callable2"), new CallableTask("callable3"));
        try {
            List<Future<Msg>> futures = executor.invokeAll(callableList);
            for(Future<Msg> future: futures){
                Msg result = future.get();
                System.out.println("Received: "+result.getMsg());
            }
        }catch (InterruptedException exception){
            exception.printStackTrace();
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        } finally {
            executor.shutdown();
        }

    }
}
