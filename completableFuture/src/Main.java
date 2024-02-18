import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        // CompletableFuture
        // No explicit executor is required, if we dont pass any Executor, by default ForkJoinPool Executor is used
        //
        /*
         * runAsync (Runnable) -> use it when you dont want to return anything
         */
        try {
            CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
                System.out.println("This function don't return anything:" + Thread.currentThread().getName());
            });
            future.get();
        }catch (InterruptedException | ExecutionException e){}
        /*
         * supplyAsync (Supplier) -> use it when you  want to return something and pass/chain to other futures
         * Supplier has one abstract method -> T get(), we can use lambda expr
         */
        try{
            CompletableFuture<String> future2 = CompletableFuture.supplyAsync(()->{
                return "anurag: "+Thread.currentThread().getName();
            });
            System.out.println(future2.get());
        }catch (InterruptedException | ExecutionException e){}
        /*
        * Passing own executor
        * */
        ExecutorService executor = Executors.newFixedThreadPool(10);
        try{
            CompletableFuture<List<Integer>> future3 = CompletableFuture.supplyAsync(()->{
                return List.of(1,2,3,4,5);
            }, executor);
            System.out.println(future3.get());
        }catch (InterruptedException | ExecutionException e){}
        finally {
            executor.shutdown();

        }
        /*
        *  Future chaining
        * Attaching callbacks which automatically triggers as the future completes
        * These chaining are dependent task, they run sequentially by single thread.
        * Though if you use Async suffix to these functions, they can  be executed by other threads from the given thread pool
        * thenApply -> takes consumer and returns to next callback
        * thenAccept -> takes consumer but dont returns anything, usually used as the last callback
        * thenRun -> takes runnable and dont returns anything, usually used as the last callback
        * */
        try{
            CompletableFuture<Void> future4 = CompletableFuture.supplyAsync(()->List.of(1,2,4,5))
                    .thenApplyAsync((list)->list.stream().filter(e->e%2==0).collect(Collectors.toList()))
                    .thenAccept(System.out::println);
            future4.get();
        }catch (InterruptedException | ExecutionException e){}
        /*
        * Running multiple futures parallel using defaulr thread pool
         * */
        try{
            List<CompletableFuture<Integer>> completableFutureList = new ArrayList<>();
            List<Integer> randomList = List.of(1,2,3,4,5,6,7,8,9,10);
            randomList.forEach(i->{
                CompletableFuture<Integer> future = CompletableFuture
                        .supplyAsync(()->i*2)
                        .thenApplyAsync(j->j+3)
                        .thenApplyAsync((k)->{
                            try {
                                Thread.sleep(new Random().nextInt(3000));
                            } catch (InterruptedException e) {
                                throw new RuntimeException(e);
                            }
                            return k+1;
                        });
                completableFutureList.add(future);
            });
            CompletableFuture<Void> combinedFutures = CompletableFuture.allOf(completableFutureList.toArray(new CompletableFuture[randomList.size()]))
                                                        .thenAccept((value)->{
                                                            // allOf() returns a CompletableFuture<Void>.
                                                            // computableFuture.join is similar to get, only diff is that join dont throw checked exception
                                                            //   making it easier with lambda expression
                                                            completableFutureList.stream()
                                                                    .map(CompletableFuture::join)
                                                                    .forEach(System.out::println);
                                                        });
            combinedFutures.get();

        }catch (InterruptedException | ExecutionException e){

        }
    }
}
