package com.charot.app;

import com.charot.app.log.Logger;
import com.charot.app.model.Order;
import com.charot.app.model.OrderBook;
import com.charot.app.model.OrderType;

import java.util.*;

public class MatchingEngineImpl implements MatchingEngine {

    private final Integer MIN_PRICE = 0;
    private final Integer MAX_PRICE = Integer.MAX_VALUE;

    private HashMap<String, OrderBook> orderBooks;
    private HashMap<Integer, String> orderIdBookTable = new HashMap<>();
    private TradeLedger tradeLedger = new TradeLedger();

    public MatchingEngineImpl(HashMap<String, OrderBook> orderBooks) {
        this.orderBooks = orderBooks;
    }

    private void matching() {
        for (OrderBook book : orderBooks.values())
            findMatch(book);
    }

    private boolean findMatch(OrderBook book) {
        int minBuyPrice = MAX_PRICE;
        Order minBuyOrder = null;
        int maxSellPrice = MIN_PRICE;
        Order maxSellOrder = null;
        for (Order order : book.getOpenOrders().values()) {
            if (order.getOrderType() == OrderType.B && order.getPrice() < minBuyPrice) {
                minBuyPrice = order.getPrice();
                minBuyOrder = order;
                continue;
            }
            if (order.getOrderType() == OrderType.S && order.getPrice() > maxSellPrice) {
                maxSellPrice = order.getPrice();
                maxSellOrder = order;
            }
        }
        if (maxSellPrice == MIN_PRICE || minBuyPrice == MAX_PRICE)
            return false;
        tradeLedger.addTrade(minBuyOrder, maxSellOrder);
        book.updateOrders(minBuyOrder, maxSellOrder);
        return true;
    }

    @Override
    public void addNewOrderToBook(Order o) {
        orderBooks.get(o.getOrderBookName()).addOrder(o);
        orderIdBookTable.put(o.getId(), o.getOrderBookName());
        Logger.toLog("Order with ID "+o.getId()+" added: "+o.orderInfo());
        matching();
    }


    @Override
    public boolean isBookExists(String bookName) {
        return orderBooks.containsKey(bookName);
    }

    @Override
    public Set<String> getBooksNames() {
        if(orderBooks==null)
            return new HashSet<String>();
        return orderBooks.keySet();
    }

    @Override
    public String getHistory() {
        return tradeLedger.getTradeHistory();
    }

}
