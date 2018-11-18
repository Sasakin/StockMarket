package com.charot.app.controller;


import com.charot.app.log.Logger;
import com.charot.app.MatchingEngine;
import com.charot.app.MatchingEngineImpl;
import com.charot.app.model.Order;
import com.charot.app.model.OrderType;
import com.charot.app.resource.CMD;
import com.charot.app.resource.Strings;
import com.charot.app.utills.StockMarketUtills;

import java.util.*;

import java.util.Scanner;

import static com.charot.app.resource.Strings.NO_AVAILABLE_BOOKS_ERROR_MSG;
import static com.charot.app.utills.StockMarketUtills.getOrderBooks;


public class Controller {

    private HashMap<String, Function> cmdFunctionMap = new HashMap();
    private MatchingEngine matchingEngine;

    public void startApp() {
        matchingEngine = new MatchingEngineImpl(getOrderBooks());
        readInputLine();
    }

    private void readInputLine() {
        Scanner scanner = new Scanner(System.in);
        String line = "";
        do {
            line = scanner.nextLine();
            Logger.toLog("User input \'" + line + "\'", false);
            executeCmd(line);
        } while (!line.equals(CMD.CMD_EXIT));
    }

    private void executeCmd(String cmd) {
        String[] args = cmd.split(" ");
        if (args.length == 0)
            return;

        Function function = cmdFunctionMap.get(args[0]);
        if(function == null) {
            unknownCmd();
            return;
        }
        function.execute(args);
    }

    {
        cmdFunctionMap.put(CMD.CMD_INFO, new Function() {
            @Override
            public void execute(String[] cmd) {
                printInfo();
            }
        });
        cmdFunctionMap.put(CMD.CMD_HISTORY, new Function() {
            @Override
            public void execute(String[] cmd) {
                printHistory();
            }
        });
        cmdFunctionMap.put(CMD.CMD_BOOKS, new Function() {
            @Override
            public void execute(String[] cmd) {
                printBooks();
            }
        });
        cmdFunctionMap.put(CMD.CMD_EXIT, new Function() {
            @Override
            public void execute(String[] cmd) {
            }
        });
        cmdFunctionMap.put(CMD.CMD_ADD_ORDER, new Function() {
            @Override
            public void execute(String[] cmd) {
                addOrder(cmd);
            }
        });
    }

    private void printInfo() {
        Logger.toLog("Use command \'history\' to view trade history.");
        Logger.toLog("Use command \'add\' to place your orders. Use following syntax:");
        Logger.toLog("add <Book Name> B/S <Price> <Quantity>");
        Logger.toLog("Use command \'books\' to view available books.");
        Logger.toLog("Use command \'exit\' to correctly close simulator.");
    }

    private void printHistory() {
        Logger.toLog("History info:");
        Logger.toLog(matchingEngine.getHistory());
    }

    private void printBooks() {
        Set<String> books = matchingEngine.getBooksNames();
        if(books.size()==0) {
            Logger.toLog(NO_AVAILABLE_BOOKS_ERROR_MSG);
            return;
        }
        Logger.toLog("Books: " + books);
    }

    private void addOrder(String[] cmd) {
        if (!StockMarketUtills.checkAddCMD(cmd, matchingEngine))
            return;
        if (cmd[CMD.SIDE_INDEX_ADD_COMMAND].equals(OrderType.B.name()))
            matchingEngine.addNewOrderToBook(new Order(cmd[CMD.BOOK_NAME_INDEX_ADD_COMMAND],
                    OrderType.B, Integer.parseInt(cmd[CMD.PRICE_INDEX_ADD_COMMAND]), Integer.parseInt(cmd[CMD.QUANTITY_INDEX_ADD_COMMAND])));
        else
            matchingEngine.addNewOrderToBook(new Order(cmd[CMD.BOOK_NAME_INDEX_ADD_COMMAND],
                    OrderType.S, Integer.parseInt(cmd[CMD.PRICE_INDEX_ADD_COMMAND]), Integer.parseInt(cmd[CMD.QUANTITY_INDEX_ADD_COMMAND])));
    }

    private void unknownCmd() {
        Logger.toLog(Strings.UNKNOWN_COMMAND_ERROR_MSG);
    }

    interface Function {
        void execute(String[] cmd);
    }
}

