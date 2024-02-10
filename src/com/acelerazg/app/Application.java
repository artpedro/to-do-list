package com.acelerazg.app;
import com.acelerazg.printer.TaskPrinter;
import com.acelerazg.filemanager.TaskFileReader;
import java.util.ArrayList;

import static com.acelerazg.app.Utils.getInput;

public class Application {
    public static void main(String[] args) {
        System.out.println("TO-DO LIST");
        System.out.println();
        TaskFileReader fileReader = new TaskFileReader();
        ArrayList<Task> allTasks = fileReader.readLog(); // create one instance of Task for each line in log
        TaskPrinter.allByPriority(allTasks);
        System.out.println();
        while (true) {
            TaskPrinter.askCommand();
            String command = getInput().toLowerCase();
            if (command.equals("close")) break;
          switch (command) {
                case "new":
                    TaskHandler.newTask(allTasks);
                    fileReader.saveLog(allTasks);
                    break;
                case "delete":
                    TaskHandler.deleteTask(allTasks);
                    fileReader.saveLog(allTasks);
                    break;
                case "update":
                    TaskHandler.updateTask(allTasks);
                    fileReader.saveLog(allTasks);
                    break;
                case "view":
                    TaskHandler.viewTask(allTasks);
                    break;
                default: System.out.println("Invalid Command");
            }
        }
        TaskPrinter.closeApp();
    }
}
