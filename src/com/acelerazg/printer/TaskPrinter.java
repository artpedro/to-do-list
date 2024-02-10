package com.acelerazg.printer;

import com.acelerazg.app.Task;
import com.acelerazg.comparators.*;
import java.util.ArrayList;

public class TaskPrinter {
    public static void allByPriority(ArrayList<Task> tasks) {
        printBanner();
        tasks.sort(new PriorityComparator());
        for (Task t : tasks) {
            System.out.println(t);
            printBar();
        }
        countStatus(tasks);
    }
    public static void allByStatus(ArrayList<Task> tasks) {
        printBanner();
        tasks.sort(new StatusComparator());
        for (Task t : tasks) {
            System.out.println(t);
            printBar();
        }
        countStatus(tasks);
    }
    public static void allByTag(ArrayList<Task> tasks) {
        printBanner();
        tasks.sort(new TagComparator());
        for (Task t : tasks) {
            System.out.println(t);
            printBar();
        }
        countStatus(tasks);
    }
    public static void askCommand() {
        System.out.print("\nWaiting for command to execute: "+
                TextColors.ANSI_GREEN_BACKGROUND + TextColors.ANSI_BLACK + "[New]" + TextColors.ANSI_RESET + " " +
                TextColors.ANSI_RED_BACKGROUND + TextColors.ANSI_BLACK + "[Delete]" + TextColors.ANSI_RESET + " " +
                TextColors.ANSI_BLUE_BACKGROUND + TextColors.ANSI_BLACK + "[Update]" + TextColors.ANSI_RESET + " " +
                TextColors.ANSI_YELLOW_BACKGROUND + TextColors.ANSI_BLACK + "[View]" + TextColors.ANSI_RESET + " " +
                TextColors.ANSI_BLACK_BACKGROUND + TextColors.ANSI_WHITE + "[Close]" + TextColors.ANSI_RESET +
                "\n> ");
    }
    public static void closeApp() {
        System.out.println("Saving list and closing application...");
    }
    public static void countStatus(ArrayList<Task> tasks) {
        int todo = 0;
        int doing = 0;
        int done = 0;
        for (Task task : tasks) {
            switch (task.getStatus()) {
                case 0:
                    todo++;
                    break;
                case 1:
                    doing++;
                    break;
                case 2:
                    done++;
                    break;
            }
        }
        System.out.println("Currently:\n" +
                            "    " + TextColors.ANSI_GREEN_BACKGROUND + TextColors.ANSI_BLACK + "Done:  " + done + TextColors.ANSI_RESET + "\n" +
                            "    " + TextColors.ANSI_YELLOW_BACKGROUND + TextColors.ANSI_BLACK + "Doing: " + doing + TextColors.ANSI_RESET + "\n" +
                            "    " + TextColors.ANSI_RED_BACKGROUND + TextColors.ANSI_BLACK + "Todo:  " + todo + TextColors.ANSI_RESET);
        printBar();
    }
    public static void printBar() {
        System.out.println("___________________________________________________________________________________________");
    }
    public static void printBanner() {
        System.out.println(TextColors.ANSI_YELLOW +
        "    88888888888                   888               888      d8b          888   \n"+
        "        888                       888               888      Y8P          888   \n" +
        "        888                       888               888                   888   \n" +
        "        888   .d88b.          .d88888  .d88b.       888      888 .d8888b  888888\n" +
        "        888  d88\"\"88b        d88\" 888 d88\"\"88b      888      888 88K      888   \n" +
        "        888  888  888 888888 888  888 888  888      888      888 \"Y8888b. 888   \n" +
        "        888  Y88..88P        Y88b 888 Y88..88P      888      888      X88 Y88b. \n" +
        "        888   \"Y88P\"          \"Y88888  \"Y88P\"       88888888 888  88888P'  \"Y888"+
                TextColors.ANSI_RESET);
        printBar();
    }
}
