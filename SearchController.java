package mini_proj;

import java.util.List;
import java.util.Scanner;

public class SearchController {
    PromptCheck promptCheck = new PromptCheck();

    public void displayTasks(List<Task> tasks) {
        if (tasks.isEmpty()) System.out.println("no tasks found\n");
        for (Task t : tasks) {
            if (t instanceof Event) {
                System.out.println("********* event *********");
                System.out.println(t.getTitle());
                System.out.println(t.getDescription());
                System.out.println(t.isComplete());
                System.out.println(((Event) t).getStartTime());
                System.out.println(((Event) t).getEndTime()+"\n");
            }
            if (t instanceof DeadlineTask) {
                System.out.println("********* deadline task *********");
                System.out.println(t.getTitle());
                System.out.println(t.getDescription());
                System.out.println(t.isComplete());
                System.out.println(((DeadlineTask) t).getDeadline()+"\n");
            }
            if (t instanceof FloatingTask) {
                System.out.println("********* floating task *********");
                System.out.println(t.getTitle());
                System.out.println(t.getDescription());
                System.out.println(t.isComplete()+"\n");
            }
        }
        System.out.println();
    }

    public void searchByTitle(TaskManager taskManager, Scanner scanner) {
        String title = promptCheck.getStringInput(scanner, "enter title to search");
        List<Task> tasks = taskManager.searchByTitle(title);
        displayTasks(tasks);
    }

    public void searchByDesc(TaskManager taskManager, Scanner scanner) {
        String desc=promptCheck.getStringInput(scanner,"enter description to search");
        List<Task>tasks=taskManager.searchByDesc(desc);
        displayTasks(tasks);
    }

    public void searchByCompletion(TaskManager taskManager, Scanner scanner) {
        boolean complete=promptCheck.getTrueFalse(scanner,"search for event/task completion (Y/N)");
        List<Task>tasks=taskManager.searchByComplete(complete);
        displayTasks(tasks);
    }



}
