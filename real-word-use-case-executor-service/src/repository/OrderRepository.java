package repository;

import orders.Order;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class OrderRepository {
    List<Order> orders;
    public OrderRepository(){
        orders = new ArrayList<>();
        // populate arbitrary orders
        for(int i=0;i<20;i++){
            StringBuilder sb = new StringBuilder();
            for(int j=0;j<7;j++){
                int randAscii = new Random().nextInt(91-65+1)+65; // generate ascii value from A-Z
                sb.append((char)(randAscii));
            }
            // generate randomstring
            orders.add(new Order(i, sb.toString()));
        }
    }
    public List<Order> fetchOrders(){
        return this.orders;
    }
}
