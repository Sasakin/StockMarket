package com.charot.app.log;

import com.charot.app.resource.Strings;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public final class Logger {
    private static final String LOG_FILE_NAME = "log.txt";

    private Logger() {
    }

    public static void toLog(String txt, boolean toConsole) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        String out = dateFormat.format(date) + " " + txt;
        if (toConsole) {
            System.out.println(txt);
        }
        try (FileWriter writer = new FileWriter(LOG_FILE_NAME, true)) {
            writer.write(out);
            writer.write(System.getProperty("line.separator"));
            writer.flush();
        } catch (IOException ex) {
            toLog(ex.getMessage());
        }
    }

    public static void toLog(String txt) {
        toLog(txt, true);
    }

    public static void toLog(Exception e) {
        toLog(Strings.APPLICATION_ERROR_MSG);

        try (PrintWriter writer = new PrintWriter(new FileWriter(LOG_FILE_NAME, true))) {
            e.printStackTrace(writer);
            writer.println(System.getProperty("line.separator"));
            writer.flush();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
