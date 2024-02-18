import orders.Order;
import orders.orderpipeline.OrderProcessor;
import repository.OrderRepository;
import storage.OrderStorage;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

class PipelineWorker implements Runnable{
    Order order;
    OrderStorage storage;
    public PipelineWorker(Order order, OrderStorage storage){
        this.order = order;
        this.storage = storage;
    }
    @Override
    public void run() {
        Order transform = OrderProcessor.transformOrderBy(order);
        try {
            Thread.sleep(2000);
        }catch (InterruptedException e){}
        storage.write(OrderProcessor.orderToStringConverter(transform));
    }
}
public class UsingJustExecutorService {
    public static void main(String[] args) throws InterruptedException {
        OrderRepository orderRepository = new OrderRepository();
        OrderStorage storage = new OrderStorage();
        // order pipeline
        // fetchOrders -> transformOrderBy -> orderToStringConverter -> writer
        ExecutorService executor = Executors.newFixedThreadPool(10);
        orderRepository.fetchOrders().forEach(order -> {
            executor.submit(new PipelineWorker(order, storage));
        });
        executor.shutdown();
        boolean isTerminated = executor.awaitTermination(20, TimeUnit.SECONDS);
        if(isTerminated){
            System.out.println(storage.read());
        }
    }
}
