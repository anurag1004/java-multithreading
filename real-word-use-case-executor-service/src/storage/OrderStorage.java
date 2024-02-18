package storage;

import orders.Order;

import java.util.ArrayList;
import java.util.List;

public class OrderStorage {
    private final List<String> orderStrings;
    public OrderStorage(){
        orderStrings = new ArrayList<>();
    }
    public void write(String order){
        synchronized (orderStrings) {
            orderStrings.add(order);
        }
    }
    public List<String> read(){
        return this.orderStrings;
    }
}
