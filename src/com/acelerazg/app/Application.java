package com.acelerazg.app;
// import com.acelerazg.printer.TaskPrinter;
// import com.acelerazg.filemanager.TaskFileReader;
import com.acelerazg.printer.TaskPrinter;

import static com.acelerazg.app.Utils.getInput;

public class Application {
    public static void main(String[] args) {
        System.out.println("TO-DO LIST");
        System.out.println();
        // TaskFileReader fileReader = new TaskFileReader(path);
        // if (fileReader.hasLog()) { // check if file is readable
        //      ArrayList<Task> allTasks = fileReader.getLog(); // create one instance of Task for each line in log
        //      TaskPrinter.allByPriority(allTasks); //
        // } else {System.out.println("No records found!);

        System.out.println();
        while (true) {
            // TaskPrinter.askCommand();
            String command = getInput().toLowerCase();
            if (command.equals("close")) break;
            switch (command) {
                case "new": TaskHandler.newTask(allTasks);
                    break;
                case "delete": TaskHandler.deleteTask(allTasks);
                    break;
                case "update": TaskHandler.updateTask(allTasks);
                    break;
                case "view": TaskHandler.viewTask(allTask);
                    break;
                default: System.out.println("Invalid Command");
            }
        }
        // TaskPrinter.closeApp();
    }
}
