package com.acelerazg.app;

public class TaskHandler {
    public Task logToTask(String entry, String sep) {
        String[] parameters = entry.split(sep);
        Task new_task = new Task(parameters[0],parameters[1],parameters[2],Integer.parseInt(parameters[3]),parameters[4],Integer.parseInt(parameters[5]));
        return new_task;
    }
}
