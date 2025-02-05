package mini_proj;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class DeadlineTaskController {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    PromptCheck promptCheck = new PromptCheck();
    Menu menu = new Menu();

    public void createDeadlineTask(TaskManager taskManager, Scanner scanner) {
        System.out.println("***************** add deadline task **********************");
        String taskTitle = promptCheck.getStringInput(scanner, "enter deadline task title");
        String taskDesc = promptCheck.getStringInput(scanner, "enter deadline task description");
        LocalDateTime deadline = promptCheck.getDateTimeInput(scanner, "enter deadline yyyy-MM-dd HH:mm");

        DeadlineTask deadlineTask = new DeadlineTask(taskTitle, taskDesc, false, deadline);
        taskManager.addTask(deadlineTask);
        System.out.println(taskTitle+" added successfully\n");
    }

    public void displayDeadlineTask(TaskManager taskManager) {
        System.out.println("***************** Deadline task **********************");
        boolean flag = false;
        for (Task t : taskManager.getTasks()) {
            if (t instanceof DeadlineTask) {
                flag = true;
                System.out.println("title: " + t.getTitle());
                System.out.println("description: " + t.getDescription());
                System.out.println("complete: " + (t.isComplete()?"Completed":"Not complete"));
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
                System.out.println("deadline task title found - " + taskTitle+"\n");
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
            if (deadlineTask == null) System.out.println(taskTitle + " not found\n");

        } while (true);
    }

    public void editTaskCompletion(Scanner scanner, String taskTitle, DeadlineTask deadlineTask) {
        System.out.println("********* edit deadline task completion *********");
        boolean taskComplete = promptCheck.setComplete(scanner, "is "+taskTitle+" completed? (Y/N)");
        deadlineTask.setComplete(taskComplete);
        System.out.println(deadlineTask.getTitle()+" set to "+(taskComplete?"complete\n":"not complete\n"));
    }

    public void editTaskDeadline(Scanner scanner, String taskTitle, DeadlineTask deadlineTask) {
        System.out.println("********* edit deadline task deadline *********");
        LocalDateTime newDeadline=promptCheck.getDateTimeInput(scanner,"enter "+taskTitle+"'s new deadline");
        deadlineTask.setDeadline(newDeadline);
        System.out.println(taskTitle+" updated\n");
    }

    public void editTaskDesc(Scanner scanner, String taskTitle,DeadlineTask deadlineTask) {
        System.out.println("********* edit deadline task description *********");
        String newDeadlineTaskDesc=promptCheck.getStringInput(scanner,"enter "+taskTitle+"'s new description");
        deadlineTask.setDescription(newDeadlineTaskDesc);
        System.out.println(taskTitle+" updated\n");
    }

    public void editTaskTitle(Scanner scanner, String taskTitle,DeadlineTask deadlineTask) {
        System.out.println("********* edit deadline task title *********");
        String newTaskTitle = promptCheck.getStringInput(scanner, "enter "+taskTitle+"'s new title");
        deadlineTask.setTitle(newTaskTitle);
        System.out.println(newTaskTitle+" updated\n");
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
            if(deadlineTask==null) System.out.println(taskTitle+" not found\n");
            if(deadlineTask!=null) {
                taskManager.deleteTask(deadlineTask);
                System.out.println(taskTitle+" has been deleted\n");
                return;
            }
        }
    }

    public void searchDeadlineTask(TaskManager taskManager, Scanner scanner) {
        boolean flag=false;
        String searchText=promptCheck.getStringInput(scanner,"search for deadline task (title/description)");
        System.out.println("***************** deadline task **********************");
        for(Task t:taskManager.getTasks()){
            if(t instanceof DeadlineTask){
                if(t instanceof DeadlineTask){
                    if(t.getTitle().contains(searchText) || t.getDescription().contains(searchText)){
                        flag=true;
                        System.out.println("title: "+t.getTitle());
                        System.out.println("description: "+t.getDescription());
                        System.out.println("deadline: "+((DeadlineTask) t).getDeadline());
                        System.out.println("completion: "+(t.isComplete()?"Completed\n":"Not complete\n"));
                    }
                }
            }
        }
        if(!flag){
            System.out.println(searchText+" not found\n");
        }
    }



}
