package com.charot.app.model;

import java.util.Date;

/**
 * Created by Student on 15.11.2018.
 */
public class Order {
    private static Integer orderCount = 1;
    private final Integer id;
    private final String orderBookName;
    private final OrderType orderType;
    private final Integer price;
    private Integer quantity;
    private final Date createDate;

    public Order(String orderBookName, OrderType orderType, Integer price, Integer quantity) {
        id = orderCount++;
        createDate = new Date(System.currentTimeMillis());

        this.orderBookName = orderBookName;
        this.orderType = orderType;
        this.price = price;
        this.quantity = quantity;
    }

    public Integer getPrice() {
        return price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public OrderType getOrderType() {
        return orderType;
    }

    public String getOrderBookName() {
        return orderBookName;
    }

    public Integer getId() {
        return id;
    }

    public Date getCreateDate() {
        return createDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Order order = (Order) o;

        if (id != null ? !id.equals(order.id) : order.id != null) return false;
        if (price != null ? !price.equals(order.price) : order.price != null) return false;
        if (quantity != null ? !quantity.equals(order.quantity) : order.quantity != null) return false;
        if (orderType != order.orderType) return false;
        if (orderBookName != null ? !orderBookName.equals(order.orderBookName) : order.orderBookName != null)
            return false;
        return createDate != null ? createDate.equals(order.createDate) : order.createDate == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (price != null ? price.hashCode() : 0);
        result = 31 * result + (quantity != null ? quantity.hashCode() : 0);
        result = 31 * result + (orderType != null ? orderType.hashCode() : 0);
        result = 31 * result + (orderBookName != null ? orderBookName.hashCode() : 0);
        result = 31 * result + (createDate != null ? createDate.hashCode() : 0);
        return result;
    }

    public String orderInfo() {
        String type = orderType == OrderType.B ? "Buy" : "Sell";
        return orderBookName+" "+type+" "+price+" @ "+quantity;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", price=" + price +
                ", quantity=" + quantity +
                ", orderType=" + orderType +
                ", orderBookName='" + orderBookName + '\'' +
                ", createDate=" + createDate +
                '}';
    }
}
