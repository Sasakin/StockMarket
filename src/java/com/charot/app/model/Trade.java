package com.charot.app.model;

public class Trade {
    private Integer buyOrderId;
    private Integer sellOrderId;
    private Integer buyPrice;
    private Integer sellPrice;
    private Integer quantity;
    private Integer id;
    private String bookName;

    public Trade(Integer buyOrderId, Integer sellOrderId, Integer buyPrice, Integer sellPrice, Integer quantity, Integer id, String bookName) {
        this.buyOrderId = buyOrderId;
        this.sellOrderId = sellOrderId;
        this.buyPrice = buyPrice;
        this.sellPrice = sellPrice;
        this.quantity = quantity;
        this.id = id;
        this.bookName = bookName;
    }

    public Integer getId() {
        return id;
    }

    public String getBookName() {
        return bookName;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public Integer getSellPrice() {
        return sellPrice;
    }

    public Integer getBuyPrice() {
        return buyPrice;
    }

    public Integer getSellOrderId() {
        return sellOrderId;
    }

    public Integer getBuyOrderId() {
        return buyOrderId;
    }

    public String tradeInfo() {
        return id +" "+getBookName()+" " + getSellPrice()+ " @ "+getQuantity()+ " (orders " + getBuyOrderId() + " and "+getSellOrderId()+")";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Trade trade = (Trade) o;

        if (buyOrderId != null ? !buyOrderId.equals(trade.buyOrderId) : trade.buyOrderId != null) return false;
        if (sellOrderId != null ? !sellOrderId.equals(trade.sellOrderId) : trade.sellOrderId != null) return false;
        if (buyPrice != null ? !buyPrice.equals(trade.buyPrice) : trade.buyPrice != null) return false;
        if (sellPrice != null ? !sellPrice.equals(trade.sellPrice) : trade.sellPrice != null) return false;
        if (quantity != null ? !quantity.equals(trade.quantity) : trade.quantity != null) return false;
        if (id != null ? !id.equals(trade.id) : trade.id != null) return false;
        return bookName != null ? bookName.equals(trade.bookName) : trade.bookName == null;
    }

    @Override
    public int hashCode() {
        int result = buyOrderId != null ? buyOrderId.hashCode() : 0;
        result = 31 * result + (sellOrderId != null ? sellOrderId.hashCode() : 0);
        result = 31 * result + (buyPrice != null ? buyPrice.hashCode() : 0);
        result = 31 * result + (sellPrice != null ? sellPrice.hashCode() : 0);
        result = 31 * result + (quantity != null ? quantity.hashCode() : 0);
        result = 31 * result + (id != null ? id.hashCode() : 0);
        result = 31 * result + (bookName != null ? bookName.hashCode() : 0);
        return result;
    }
}
