package com.acelerazg.printer;

import com.acelerazg.app.Task;
import com.acelerazg.comparators.*;
import java.util.ArrayList;
import java.util.Collections;

public class TaskPrinter {
    public static void allByPriority(ArrayList<Task> tasks) {
        tasks.sort(new PriorityComparator());
        for (Task t : tasks) {
            System.out.println(t);
        }
    }
    public static void allByStatus(ArrayList<Task> tasks) {
        tasks.sort(new StatusComparator());
        for (Task t : tasks) {
            System.out.println(t);
        }
    }
    public static void allByTag(ArrayList<Task> tasks) {
        tasks.sort(new TagComparator());
        for (Task t : tasks) {
            System.out.println(t);
        }
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
}
