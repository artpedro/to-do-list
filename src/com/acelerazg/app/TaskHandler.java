package com.acelerazg.app;

import com.acelerazg.printer.TaskPrinter;
import com.sun.xml.internal.ws.encoding.fastinfoset.FastInfosetStreamReaderRecyclable;

import javax.rmi.CORBA.Util;
import javax.swing.plaf.synth.SynthTextAreaUI;
import java.util.ArrayList;

import static com.acelerazg.app.Utils.getInput;

public class TaskHandler {
    public Task logToTask(String entry, String sep) {
        String[] parameters = entry.split(sep);
        Task new_task = new Task(parameters[0], parameters[1], parameters[2], Integer.parseInt(parameters[3]), parameters[4], Integer.parseInt(parameters[5]));
        return new_task;
    }

    public static ArrayList<Task> newTask(ArrayList<Task> tasks) {
        String start = "What's the new task's ";

        System.out.println(start + "name?:\n> ");
        String name = getInput();
        for (Task task : tasks) {
            if (task.equals(new Task(name, "", "", 0, "", 0))) {
                System.out.println("This task already exists");
                return tasks;
            }
        }

        System.out.println(start + " description?:\n> ");
        String desc = getInput();

        System.out.println(start + " tag?:\n> ");
        String tag = getInput();

        System.out.println(start + " end date? (dd-mm-yyyy):\n> ");
        String endDate = getInput();

        System.out.println(start + " priority? (1-5):\n> ");
        int priority = Integer.parseInt(getInput());

        System.out.println(start + " status? (TODO | DOING | DONE):\n> ");
        String strStatus = getInput().toUpperCase();

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
        System.out.println("What's the name of the task you want to delete?\n> ");
        String delete_name = getInput();
        Task dummy = new Task(delete_name, "", "", 0, "", 1);
        for (Task task : tasks) {
            if (task == dummy) {
                tasks.remove(task);
                return tasks;
            }
        }
        System.out.println("This task does not exist");
        return tasks;
    }

    public static ArrayList<Task> updateTask(ArrayList<Task> tasks) {
        System.out.println("What's the name of the task you want to update?\n> ");
        String update_name = getInput();
        Task updateTask = new Task(update_name, "", "", 0, "", 1);
        Task old_task = updateTask.clone();
        Task new_task = updateTask.clone();
        Boolean save = false;
        Boolean discard = false;
        for (Task task : tasks) {
            if (task == updateTask) {
                old_task = task.clone();
                new_task = task.clone();
                while (true) {
                    System.out.println("What do you want to edit? (Description | EndDate | Priority | Tag | Status | Discard | Save)\n> ");
                    String field = getInput().toLowerCase();
                    switch (field) {
                        case "discard":
                            System.out.println("Discarding changes...");
                            discard = true;
                            break;
                        case "save":
                            System.out.println("Saving changes...");
                            save = true;
                            break;
                        case "description":
                            System.out.println("Old Description:\n" + new_task.getDesc()+"\n");
                            System.out.println("Type new Description:\n> ");
                            new_task.setDesc(getInput());
                            break;
                        case "enddate":
                            System.out.println("Old End Date:\n" + new_task.getEndDate() + "\n");
                            System.out.println("Type new End Date:\n> ");
                            new_task.setEndDate(getInput());
                            break;
                        case "priority":
                            System.out.println("Old Priority:\n" + Integer.toString(new_task.getPriority()) + "\n");
                            System.out.println("Type new Priority:\n> ");
                            String strPriority = getInput();
                            switch (strPriority) {
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
                            System.out.println("Old Tag:\n" + new_task.getTag()+ "\n");
                            System.out.println("Type new tag:\n> ");
                            new_task.setTag(getInput());
                            break;
                        case "status":
                            System.out.println("Old Status:\n" + new_task.getStrStatus() + "\n");
                            System.out.println("Type new status:\n> ");
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
                    if (save || discard) break;
                }
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
}