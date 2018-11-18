package com.charot.app;

import com.charot.app.model.Order;

import java.util.Set;

public interface MatchingEngine {

    void addNewOrderToBook(Order o);

    boolean isBookExists(String bookName);

    Set<String> getBooksNames();

    String getHistory();
}
