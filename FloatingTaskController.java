package mini_proj;

import java.util.Scanner;

public class FloatingTaskController {
    PromptCheck promptCheck=new PromptCheck();
    Menu menu=new Menu();

    public void createTask(TaskManager taskManager, Scanner scanner) {
        System.out.println("***************** add floating tasks **********************");
        String taskTitle=promptCheck.getStringInput(scanner,"enter floating task title");
        String taskDesc=promptCheck.getStringInput(scanner,"enter floating task description");

        FloatingTask floatingTask=new FloatingTask(taskTitle,taskDesc,false);
        taskManager.addTask(floatingTask);
        System.out.println(taskTitle+" added successfully\n");
    }

    public void displayTask(TaskManager taskManager) {
        System.out.println("***************** floating task **********************");
        boolean flag = false;
        for (Task t : taskManager.getTasks()) {
            if (t instanceof FloatingTask) {
                flag = true;
                System.out.println("title: " + t.getTitle());
                System.out.println("description: " + t.getDescription());
//                System.out.println("complete: " + (t.isComplete() ? "completed" : "not complete\n"));
                System.out.println("complete: " + t.isComplete()+"\n");
            }
        }
        if (!flag) {
            System.out.println("no floating task added\n");
        }
        System.out.println();
    }

    public void editTask(TaskManager taskManager, Scanner scanner) {
        while(true){
            String taskTitle=promptCheck.getStringInput(scanner,"enter floating task title:");
            FloatingTask floatingTask=findFloatingTaskTitle(taskManager,taskTitle);
            if(floatingTask!=null){
                System.out.println("floating task found - "+floatingTask.getTitle());
                while(true){
                    menu.editFloatingTaskMenu(floatingTask.getTitle());
                    int choice;
                    try{
                        choice=Integer.parseInt(scanner.nextLine());
                    }catch(NumberFormatException e){
                        System.out.println("invalid. only digit");
                        continue;
                    }
                    switch (choice){
                        case 1 -> editFloatingTaskTitle(scanner,floatingTask);
                        case 2 -> editFloatingTaskDesc(scanner,floatingTask);
                        case 3 -> editFloatingTaskCompletion(scanner,floatingTask);
                        case 4 -> {return;}
                        default -> System.out.println("invalid. only 1-4");
                    }
                }
            }
            if(floatingTask==null) System.out.println(taskTitle+" not found");
        }
    }

    public void editFloatingTaskCompletion(Scanner scanner, FloatingTask floatingTask) {
        System.out.println("********* edit event completion *********");
        boolean taskComplete = promptCheck.setComplete(scanner, "is floating task completed? (Y/N)");
        floatingTask.setComplete(taskComplete);
        System.out.println(floatingTask.getTitle()+" set to "+(taskComplete?"complete":"not complete"));
    }


    public void editFloatingTaskDesc(Scanner scanner, FloatingTask floatingTask) {
        System.out.println("********* edit floating task description *********");
        String newFloatingTaskDesc = promptCheck.getStringInput(scanner, "enter new description for "+floatingTask.getTitle());
        floatingTask.setDescription(newFloatingTaskDesc);
    }

    public void editFloatingTaskTitle(Scanner scanner, FloatingTask floatingTask) {
        System.out.println("********* edit floating task title *********");
        String newFloatingTaskTitle = promptCheck.getStringInput(scanner, "enter new title for "+floatingTask.getTitle());
        floatingTask.setTitle(newFloatingTaskTitle);
    }

    public void deleteTask(TaskManager taskManager, Scanner scanner) {
        System.out.println("********* delete floating task *********");
        while(true){
            String taskTitle=promptCheck.getStringInput(scanner,"enter floating task title you wish to delete");
            FloatingTask floatingTask=findFloatingTaskTitle(taskManager,taskTitle);
            if(floatingTask==null) System.out.println(taskTitle+" not found");
            if(floatingTask!=null){
                taskManager.deleteTask(floatingTask);
                System.out.println(taskTitle+" has been deleted");
                return;
            }
        }
    }

    public FloatingTask findFloatingTaskTitle(TaskManager taskManager, String taskTitle) {
        for (Task t : taskManager.getTasks()) {
            if (t instanceof FloatingTask && t.getTitle().equals(taskTitle)) {
                return (FloatingTask) t;
            }
        }
        return null;
    }
}
