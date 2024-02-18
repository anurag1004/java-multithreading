package orders;

public class Order {
    private int id;
    private String orderBy;

    public Order(int id, String orderBy) {
        this.id = id;
        this.orderBy = orderBy;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getorderBy() {
        return orderBy;
    }

    public void setorderBy(String orderBy) {
        this.orderBy = orderBy;
    }
}
