package orders.orderpipeline;

import orders.Order;

public class OrderProcessor {
    public static Order transformOrderBy(Order order){
        order.setorderBy(order.getorderBy().toLowerCase());
        return order;
    }
    public static String orderToStringConverter(Order order){
        return String.format("|OrderId: %d orderBy: %s|", order.getId(), order.getorderBy());
    }
}
