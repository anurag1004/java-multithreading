import orders.orderpipeline.OrderProcessor;
import repository.OrderRepository;
import storage.OrderStorage;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class UsingComputableFuture {
    public static void main(String[] args) {
        OrderRepository orderRepository = new OrderRepository();
        OrderStorage storage = new OrderStorage();
        List<CompletableFuture<Void>> completableFutures = new ArrayList<>();
        orderRepository
        .fetchOrders()
        .forEach(order -> {
            completableFutures.add(
                    CompletableFuture.supplyAsync(()-> OrderProcessor.transformOrderBy(order))
                    .thenApply(o2-> OrderProcessor.orderToStringConverter(o2))
                    .thenAccept(o3->storage.write(o3))
            );
        });
        completableFutures.forEach(CompletableFuture::join);
        System.out.println(storage.read());
    }
}
