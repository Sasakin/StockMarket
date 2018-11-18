package com.charot.app;

import com.charot.app.log.Logger;
import com.charot.app.model.Order;
import com.charot.app.model.Trade;

import java.util.ArrayList;
import java.util.List;

public class TradeLedger {
    private List<Trade> trades = new ArrayList<>();
    private Integer lastTradeId = 0;

    public TradeLedger(){}

    public Integer addTrade(Order buyOrder, Order sellOrder) {
        Integer tradeQuantity;
            if(buyOrder.getQuantity() >= sellOrder.getQuantity())
                tradeQuantity = sellOrder.getQuantity();
            else
                tradeQuantity = buyOrder.getQuantity();
        Trade trade = new Trade(buyOrder.getId(), sellOrder.getId(), buyOrder.getPrice(),
                sellOrder.getPrice(), tradeQuantity,
                lastTradeId, sellOrder.getOrderBookName());
        trades.add(trade);
        Logger.toLog("New execution with ID: " + trade.tradeInfo());
        return lastTradeId++;
    }

    public String getTradeHistory() {
        StringBuffer sb = new StringBuffer();
        for (Trade t: trades) {
            sb.append("Trade with ID:" + t.tradeInfo()+"\n");
        }
        return sb.toString();
    }
}
