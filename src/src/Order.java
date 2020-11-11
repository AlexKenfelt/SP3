import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Order {
    static int counter = 1;
    int orderID = 0;
    int phone;
    LocalDateTime createTime;
    List<Pizza> orders;
    String status;


    public Order() {
        this.orderID = counter;
        this.createTime = LocalDateTime.now();
        this.orders = new ArrayList<>();
        this.status = "CREATED/PENDING";
        counter++;
    }


    public int getOrderID() {
        return orderID;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public List<Pizza> getOrders() {
        return orders;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void addPizza(Pizza pizza) {
        this.orders.add(pizza);
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public int getPhone() {
        return phone;
    }

    public int getPrice() {
        int price = 0;
        for(Pizza pizza: orders) {
            price += pizza.getPrice();
        }
        return price;
    }

    public String toString() {
        int price = this.getPrice();
        String show = "";
        String ordersStr = "@";
        String pizzaNumber = "";
        for(Pizza pizza: orders) {
            ordersStr += pizza.toString();
            pizzaNumber += pizza.getPizzaNumber() + ",";
        }
        ordersStr += "@";
        return "-------------------------------------" + "\n OrderID: " + orderID + "\n Pizza(s) Ordered: " + pizzaNumber + "\n Customer Phone: " + phone + "\n Order Total: " + price + ",-" + "\n Order Status: " + status + "\n-------------------------------------";
    }
}
