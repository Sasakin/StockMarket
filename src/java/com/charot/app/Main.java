package com.charot.app;

import com.charot.app.controller.Controller;
import com.charot.app.log.Logger;

public class Main {

    public static void main(String[] args) {
        try {
            writeStartOut();
            Controller controller = new Controller();
            controller.startApp();
            writeEndOut();
        } catch (Exception e) {
            Logger.toLog(e);
        }
    }

    private static void writeStartOut() {
        Logger.toLog("***Stock market started****");
        Logger.toLog("Use command \'info\' for details");
    }

    private static void writeEndOut() {
        Logger.toLog("***Stock market finished****");
    }

}
