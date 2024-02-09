package com.acelerazg.filemanager;

import com.acelerazg.app.Task;
import com.acelerazg.app.TaskHandler;

import java.io.*;
import java.util.ArrayList;

public class TaskFileReader {
    // Default path
    String path = "./log/task_log.csv";
    File log = new File(path);

    public TaskFileReader(String path) {
        this.path = path;
        // Automatically creates a log file if it does not exist
        createLog();
    }

    public TaskFileReader() {
        createLog();
    }

    public void createLog() {
        try {
            if (log.exists()) { } else {
                log.createNewFile();
                System.out.println("log criado");
            }
        } catch (IOException e) {
            System.out.println("An error occurred during log creation");
            e.printStackTrace();
        }
    }
    public ArrayList<Task> readLog() {
        ArrayList<Task> tasks = new ArrayList<Task>();
        TaskHandler handler = new TaskHandler();
        try (FileReader reader = new FileReader(log); BufferedReader bufferedReader = new BufferedReader(reader)) {
            String line = bufferedReader.readLine();
            while (line != null) {
                Task log_task = handler.logToTask(line,"\",\"");
                tasks.add(log_task);
                line = bufferedReader.readLine();
            }
            return tasks;

        } catch (Exception e) {
            System.out.println("Error reading log...");
        }
        return null;
    }
    public void saveLog(ArrayList<Task> tasks) {
        try (FileWriter writer = new FileWriter(log)){
            for (Task task : tasks) {
                writer.write(task.toCsv("\",\""));
            }
            System.out.println("Saved records");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}
