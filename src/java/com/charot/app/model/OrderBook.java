package com.charot.app.model;


import com.charot.app.log.Logger;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class OrderBook {
    private String name;

    private HashMap<Integer, Order> openOrders = new HashMap<>();
    private HashMap<Integer, Order> closedOrders = new HashMap<>();


    public OrderBook(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public HashMap<Integer, Order>  getOpenOrders() {
        return openOrders;
    }

    public HashMap<Integer, Order>  getClosedOrders() {
        return closedOrders;
    }

    public void addOrder(Order order) {
        openOrders.put(order.getId(), order);
    }

    public void updateOrders(Order buyOrder, Order sellOrder){
        if (isQuantityGreater(sellOrder, buyOrder)) return;
        if (isQuantityGreater(buyOrder, sellOrder)) return;
        closeOrder(sellOrder);
        closeOrder(buyOrder);
    }

    private boolean isQuantityGreater(Order firstOrder, Order secondOrder) {
        if(secondOrder.getQuantity() > firstOrder.getQuantity()){
            secondOrder.setQuantity(secondOrder.getQuantity() - firstOrder.getQuantity());
            Logger.toLog("Order with ID: " + secondOrder.getId() + " updated at " + name + ". New quantity: " + secondOrder.getQuantity());
            closeOrder(firstOrder);
            return true;
        }
        return false;
    }

    private void closeOrder(Order order) {
        openOrders.remove(order.getId());
        order.setQuantity(0);
        closedOrders.put(order.getId(),order);
        Logger.toLog("Order with ID: " + order.getId() + " closed at " + name);
    }

}
