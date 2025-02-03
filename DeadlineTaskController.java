package mini_proj;

import java.time.LocalDateTime;
import java.util.Scanner;

public class DeadlineTaskController {

    PromptCheck promptCheck = new PromptCheck();
    Menu menu = new Menu();

    public void createDeadlineTask(TaskManager taskManager, Scanner scanner) {
        System.out.println("***************** add deadline task **********************");
        String taskTitle = promptCheck.getStringInput(scanner, "enter deadline task title");
        String taskDesc = promptCheck.getStringInput(scanner, "enter deadline task description");
        LocalDateTime deadline = promptCheck.getDateTimeInput(scanner, "enter deadline yyyy-MM-dd HH:mm");

        DeadlineTask deadlineTask = new DeadlineTask(taskTitle, taskDesc, false, deadline);
        taskManager.addTask(deadlineTask);
        System.out.println(taskTitle+" added successfully");
    }

    public void displayDeadlineTask(TaskManager taskManager) {
        System.out.println("***************** Deadline task **********************");
        boolean flag = false;
        for (Task t : taskManager.getTasks()) {
            if (t instanceof DeadlineTask) {
                flag = true;
                System.out.println("title: " + t.getTitle());
                System.out.println("description: " + t.getDescription());
//                System.out.println("complete: " + (t.isComplete() ? "completed" : "not complete"));
                System.out.println("complete: " + t.isComplete());
                System.out.println("deadline: " + ((DeadlineTask) t).getDeadline()+"\n");
            }
        }
        if (!flag) {
            System.out.println("no deadline task added\n");
        }
        System.out.println();
    }

    public void editDeadlineTask(TaskManager taskManager, Scanner scanner) {
        do {
            String taskTitle = promptCheck.getStringInput(scanner, "enter deadline task title:");
            DeadlineTask deadlineTask = findDeadlineTaskTitle(taskManager, taskTitle);
            if (deadlineTask != null) {
                System.out.println("deadline task title found - " + deadlineTask.getTitle());
                do {
                    menu.editDeadlineTaskMenu(deadlineTask.getTitle());
                    int choice;
                    try{
                        choice=Integer.parseInt(scanner.nextLine());
                    }catch(NumberFormatException e){
                        System.out.println("invalid. only digit");
                        continue;
                    }
                    switch (choice) {
                        case 1 -> editTaskTitle(scanner,taskTitle,deadlineTask);
                        case 2 -> editTaskDesc(scanner,taskTitle,deadlineTask);
                        case 3 -> editTaskDeadline(scanner,taskTitle,deadlineTask);
                        case 4 -> editTaskCompletion(scanner,taskTitle,deadlineTask);
                        case 5 -> {return;}
                        default -> System.out.println("invalid. only 1-5");
                    }
                } while (true);
            }
            if (deadlineTask == null) System.out.println(taskTitle + " not found");

        } while (true);
    }

    public void editTaskCompletion(Scanner scanner, String taskTitle, DeadlineTask deadlineTask) {
        System.out.println("********* edit deadline task completion *********");
        boolean taskComplete = promptCheck.setComplete(scanner, "is "+taskTitle+" completed? (Y/N)");
        deadlineTask.setComplete(taskComplete);
        System.out.println(deadlineTask.getTitle()+" set to "+(taskComplete?"complete":"not complete"));
    }

    public void editTaskDeadline(Scanner scanner, String taskTitle, DeadlineTask deadlineTask) {
        System.out.println("********* edit deadline task deadline *********");
        LocalDateTime newDeadline=promptCheck.getDateTimeInput(scanner,"enter "+taskTitle+"'s new deadline");
        deadlineTask.setDeadline(newDeadline);
    }

    public void editTaskDesc(Scanner scanner, String taskTitle,DeadlineTask deadlineTask) {
        System.out.println("********* edit deadline task description *********");
        String newDeadlineTaskDesc=promptCheck.getStringInput(scanner,"enter "+taskTitle+"'s new description");
        deadlineTask.setDescription(newDeadlineTaskDesc);
    }

    public void editTaskTitle(Scanner scanner, String taskTitle,DeadlineTask deadlineTask) {
        System.out.println("********* edit deadline task title *********");
        String newTaskTitle = promptCheck.getStringInput(scanner, "enter "+taskTitle+"'s new title");
        deadlineTask.setTitle(newTaskTitle);
    }

    public DeadlineTask findDeadlineTaskTitle(TaskManager taskManager, String taskTitle) {
        for (Task t : taskManager.getTasks()) {
            if (t instanceof DeadlineTask && t.getTitle().equals(taskTitle)) {
                return (DeadlineTask) t;
            }
        }
        return null;
    }

    public void deleteDeadlineTask(TaskManager taskManager, Scanner scanner) {
        System.out.println("********* delete deadline task *********");
        while(true){
            String taskTitle=promptCheck.getStringInput(scanner,"enter deadline task title you wish to delete");
            DeadlineTask deadlineTask=findDeadlineTaskTitle(taskManager,taskTitle);
            if(deadlineTask==null) System.out.println(taskTitle+" not found");
            if(deadlineTask!=null) {
                taskManager.deleteTask(deadlineTask);
                System.out.println(taskTitle+" has been deleted");
                return;
            }
        }
    }
}
