package com.acelerazg.app;

import com.sun.xml.internal.ws.encoding.fastinfoset.FastInfosetStreamReaderRecyclable;

import javax.swing.plaf.synth.SynthTextAreaUI;
import java.util.ArrayList;

public class TaskHandler {
    public Task logToTask(String entry, String sep) {
        String[] parameters = entry.split(sep);
        Task new_task = new Task(parameters[0], parameters[1], parameters[2], Integer.parseInt(parameters[3]), parameters[4], Integer.parseInt(parameters[5]));
        return new_task;
    }

    public ArrayList<Task> newTask(ArrayList<Task> tasks) {
        String start = "What's the new task's ";

        System.out.println(start + "name?:\n> ");
        String name = Utils.getInput();
        for (Task task : tasks) {
            if (task.equals(new Task(name, "", "", 0, "", 0))) {
                System.out.println("This task already exists");
                return tasks;
            }
        }

        System.out.println(start + " description?:\n> ");
        String desc = Utils.getInput();

        System.out.println(start + " tag?");
        String tag = Utils.getInput();

        System.out.println(start + " end date? (dd-mm-yyyy):\n> ");
        String endDate = Utils.getInput();

        System.out.println(start + " priority? (1-5):\n> ");
        int priority = Integer.parseInt(Utils.getInput());

        System.out.println(start + " status? (TODO | DOING | DONE):\n> ");
        String strStatus = Utils.getInput().toUpperCase();

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

    public ArrayList<Task> deleteTask(ArrayList<Task> tasks) {
        System.out.println("What's the name of the task you want to delete?");
        String delete_name = Utils.getInput();
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
}