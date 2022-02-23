package utcn.bll;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * Clasa Order, este clasa care stocheaza informatii despre comenzi. Aceasta are metodele de equals si hashCode suprascrise pentru a putea fi identificate unic intr-un HashMap. Mai are si o variabila statica, idGenerator care se autoincrementaza la crearea unei noi comenzi.
 */
public class Order implements Serializable {
    private int orderID;
    private int clientID;
    private LocalDateTime orderDate;
    private static int idGenerator;
    public Order(int clientID, LocalDateTime orderDate) {
        this.orderID = idGenerator++;
        this.clientID = clientID;
        this.orderDate = orderDate;
    }

    public Order() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Order)) return false;
        Order order = (Order) o;
        return orderID == order.orderID && clientID == order.clientID && Objects.equals(orderDate, order.orderDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderID, clientID, orderDate);
    }

    @Override
    public String toString() {
        return
                "orderID=" + orderID +
                ", clientID=" + clientID +
                ", orderDate=" + orderDate;
    }

    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public int getClientID() {
        return clientID;
    }

    public void setClientID(int clientID) {
        this.clientID = clientID;
    }

    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDateTime orderDate) {
        this.orderDate = orderDate;
    }

    public static int getIdGenerator() {
        return idGenerator;
    }

    public static void setIdGenerator(int idGenerator) {
        Order.idGenerator = idGenerator;
    }
}
