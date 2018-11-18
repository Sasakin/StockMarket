package com.charot.app.utills;

import com.charot.app.log.Logger;
import com.charot.app.MatchingEngine;
import com.charot.app.model.OrderBook;
import com.charot.app.model.OrderType;
import com.charot.app.resource.CMD;
import com.charot.app.resource.Strings;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;

public class StockMarketUtills {

     public static HashMap<String, OrderBook> getOrderBooks() {
        HashMap<String, OrderBook> books = new HashMap<>();
        String str;
        try (Scanner scanner = new Scanner(new File("books.txt"))) {
            str = scanner.nextLine();
        } catch (IOException ex) {
            return null;
        }
        if (str == null)
            return null;
        String[] stringBooks = str.split(",");
        for (String book : stringBooks) {
            books.put(book, new OrderBook(book));
        }
        return books;
    }

    public static boolean checkAddCMD(String[] cmd, MatchingEngine matchingEngine) {
        if (cmd.length != CMD.ADD_ORDER_WORD_COUNT) {
            Logger.toLog(Strings.INCORRECT_ARGUMENTS_COUNT_ERROR_MSG);
            return false;
        }
        if (!matchingEngine.isBookExists(cmd[CMD.BOOK_NAME_INDEX_ADD_COMMAND])) {
            Logger.toLog(Strings.INCORRECT_BOOK_NAME_ERROR_MSG);
            return false;
        }
        if (!cmd[CMD.SIDE_INDEX_ADD_COMMAND].equals(OrderType.B.toString()) && !cmd[2].equals(OrderType.S.toString())) {
            Logger.toLog(Strings.INCORRECT_ORDER_SIDE_ERROR_MSG);
            return false;
        }
        try {
            int price = Integer.parseInt(cmd[CMD.PRICE_INDEX_ADD_COMMAND]);
            int quantity = Integer.parseInt(cmd[CMD.QUANTITY_INDEX_ADD_COMMAND]);
            if(price<=0||quantity<=0)
                throw new NumberFormatException();
        } catch (NumberFormatException e) {
            Logger.toLog(Strings.INCORRECT_DIGIT_INPUT_ERROR_MSG);
            return false;
        }
        return true;
    }

}
