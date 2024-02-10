package com.acelerazg.app;

import com.acelerazg.printer.TaskPrinter;
import java.util.ArrayList;

import static com.acelerazg.app.Utils.getInput;

public class TaskHandler {
    public Task logToTask(String entry, String sep) {
        String[] parameters = entry.split(sep);
        return new Task(parameters[0], parameters[1], parameters[2], Integer.parseInt(parameters[3]), parameters[4], Integer.parseInt(parameters[5]));
    }

    public static ArrayList<Task> newTask(ArrayList<Task> tasks) {
        String start = "What's the new task's ";

        System.out.println(start + "name?:");
        System.out.print("> ");
        String name = getInput();
        for (Task task : tasks) {
            if (task.equals(new Task(name, "", "", 0, "", 0))) {
                System.out.println("This task already exists");
                return tasks;
            }
        }

        System.out.println(start + "description?:");
        System.out.print("> ");
        String desc = getInput();

        System.out.println(start + "tag?:");
        System.out.print("> ");
        String tag = getInput();

        System.out.println(start + "end date? (dd-mm-yyyy):");
        System.out.print("> ");
        String endDate = getInput();
        int priority = 0;
        while (!(priority <= 5 && priority >= 1)) {
            System.out.println(start + "priority? (1-5):");
            System.out.print("> ");
            String answer = getInput();
            try {
                priority = Integer.parseInt(answer);
            } catch (Exception ignored) {
            }
        }
        String strStatus;
        do {
            System.out.println(start + "status? (TODO | DOING | DONE):");
            System.out.print("> ");

            strStatus = getInput().toUpperCase();
        } while (!strStatus.equals("DONE") && !strStatus.equals("DOING") && !strStatus.equals("TODO"));
        int status = 0;
        switch (strStatus) {
            case "DOING":
                status = 1;
                break;
            case "DONE":
                status = 2;
                break;
        }
        Task new_task = new Task(name, desc, endDate, priority, tag, status);
        tasks.add(new_task);
        return tasks;
    }

    public static ArrayList<Task> deleteTask(ArrayList<Task> tasks) {
        System.out.println("What's the name of the task you want to delete?");
        String delete_name = getInput();
        Task dummy = new Task(delete_name, "", "", 0, "", 1);
        for (Task task : tasks) {
            if (task.equals(dummy)) {
                tasks.remove(task);
                return tasks;
            }
        }
        System.out.println("This task does not exist");
        return tasks;
    }

    public static ArrayList<Task> updateTask(ArrayList<Task> tasks) {
        System.out.println("What's the name of the task you want to update?");
        System.out.print("> ");
        String update_name = getInput();
        Task updateTask = new Task(update_name, "", "", 0, "", 1);
        Task new_task = updateTask.clone();
        boolean save = false;
        boolean discard = false;
        for (Task task : tasks) {
            if (task.equals(updateTask)) {
                new_task = task.clone();
                do {
                    System.out.println("What do you want to edit? (Description | EndDate | Priority | Tag | Status | Discard | Save)");
                    System.out.print("> ");
                    String field = getInput().toLowerCase();
                    switch (field) {
                        case "discard":
                            System.out.println("Discarding changes...");
                            System.out.print("> ");
                            discard = true;
                            break;
                        case "save":
                            System.out.println("Saving changes...");
                            System.out.print("> ");
                            save = true;
                            break;
                        case "description":
                            System.out.println("Old Description:\n" + new_task.getDesc() + "\n");
                            System.out.println("Type new Description:");
                            System.out.print("> ");
                            new_task.setDesc(getInput());
                            break;
                        case "enddate":
                            System.out.println("Old End Date:\n" + new_task.getEndDate() + "\n");
                            System.out.println("Type new End Date:");
                            System.out.print("> ");
                            new_task.setEndDate(getInput());
                            break;
                        case "priority":
                            System.out.println("Old Priority:\n" + new_task.getPriority() + "\n");
                            System.out.println("Type new Priority:");
                            System.out.print("> ");
                            switch (getInput()) {
                                case "1":
                                    new_task.setPriority(1);
                                    break;
                                case "2":
                                    new_task.setPriority(2);
                                    break;
                                case "3":
                                    new_task.setPriority(3);
                                    break;
                                case "4":
                                    new_task.setPriority(4);
                                    break;
                                case "5":
                                    new_task.setPriority(5);
                                    break;
                                default:
                                    System.out.println("Invalid Priority");
                            }
                            break;
                        case "tag":
                            System.out.println("Old Tag:\n" + new_task.getTag() + "\n");
                            System.out.println("Type new tag:");
                            System.out.print("> ");
                            new_task.setTag(getInput());
                            break;
                        case "status":
                            System.out.println("Old Status:\n" + new_task.getStrStatus() + "\n");
                            System.out.println("Type new status:");
                            System.out.print("> ");
                            switch (getInput().toLowerCase()) {
                                case "todo":
                                    new_task.setStatus(0);
                                    break;
                                case "doing":
                                    new_task.setStatus(1);
                                    break;
                                case "done":
                                    new_task.setStatus(2);
                                    break;
                                default:
                                    System.out.println("Invalid status!");
                            }
                        default:
                            System.out.println("Invalid field to edit!");
                    }
                } while (!save && !discard);
            }
            if (save || discard) break;
        }
        if (discard) {
            return tasks;
        }
        if (save) {
            tasks.remove(updateTask);
            tasks.add(new_task);
            return tasks;
        }
        System.out.println("This task does not exist");
        return tasks;
    }
    public static void viewTask(ArrayList<Task> tasks) {
        System.out.println("How you want to sort the todo list? BY:(Status | Priority | Tag)");
        System.out.print("> ");
        switch (getInput().toLowerCase()) {
            case "status":
                TaskPrinter.allByStatus(tasks);
                break;
            case "priority":
                TaskPrinter.allByPriority(tasks);
                break;
            case "tag": TaskPrinter.allByTag(tasks);
                break;
            default:
                System.out.println("Invalid sort");
        }
    }
}