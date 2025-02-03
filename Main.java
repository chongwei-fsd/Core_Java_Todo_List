package mini_proj;

import java.util.Scanner;

public class Main {
    private Scanner scanner=new Scanner(System.in);

    private TaskManager taskManager=new TaskManager();
    private EventController eventController=new EventController();
    private DeadlineTaskController deadlineTaskController=new DeadlineTaskController();
    private FloatingTaskController floatingTaskController=new FloatingTaskController();
    private SearchController searchController=new SearchController();

    public static void main(String[] args) {
//        Main main=new Main();
//        main.start();
        new Main().start();
    }

    public void start(){
        int input;
        while(true) {
            System.out.println("1. event");
            System.out.println("2. task with deadline");
            System.out.println("3. task without deadline (floating task)");
            System.out.println("4. search event/task");
            try{
                input=Integer.parseInt(scanner.nextLine());
            }catch(NumberFormatException e){
                System.out.println("invalid. only digit");
                continue;
            }
            switch (input){
                case 1 -> eventMenu();
                case 2 -> deadlineTaskMenu();
                case 3 -> floatingTaskMenu();
                case 4 -> searchMenu();
                default -> System.out.println("invalid. only 1-4");
            }
        }
    }

    public void searchMenu() {
        int choice;
        while(true){
            System.out.println("1. search by title");
            System.out.println("2. search by description");
            System.out.println("3. search by completion status");
            System.out.println("4. search events by date range");
            System.out.println("5. back to main menu");
            try{
                choice=Integer.parseInt(scanner.nextLine());
            }catch(NumberFormatException e){
                System.out.println("invalid. only digit");
                continue;
            }
            switch (choice){
                case 1 -> searchController.searchByTitle(taskManager,scanner);
                case 2 -> searchController.searchByDesc(taskManager,scanner);
                case 3 -> searchController.searchByCompletion(taskManager,scanner);
                case 4 -> {return;}
                default -> System.out.println("invalid. only 1-4");
            }
        }
    }

    public void floatingTaskMenu() {
        int floatingTaskChoice;
        while(true){
            System.out.println("1. add floating task");
            System.out.println("2. display floating task");
            System.out.println("3. edit floating task");
            System.out.println("4. delete floating task");
            System.out.println("5. back to main menu");
            try{
                floatingTaskChoice=Integer.parseInt(scanner.nextLine());
            }catch(NumberFormatException e){
                System.out.println("invalid. only digit");
                continue;
            }
            switch (floatingTaskChoice){
                case 1 -> floatingTaskController.createTask(taskManager,scanner);
                case 2 -> floatingTaskController.displayTask(taskManager);
                case 3 -> floatingTaskController.editTask(taskManager,scanner);
                case 4 -> floatingTaskController.deleteTask(taskManager,scanner);
                case 5 -> {return;}
                default -> System.out.println("invalid. only 1-5");
            }
        }
    }


    public void eventMenu(){
        int eventChoice;
        while(true){
            System.out.println("1. add event");
            System.out.println("2. display event");
            System.out.println("3. edit event");
            System.out.println("4. delete event");
            System.out.println("5. back to main menu");
            try{
                eventChoice =Integer.parseInt(scanner.nextLine());
            }catch (NumberFormatException e){
                System.out.println("invalid. only digit");
                continue;
            }
            switch (eventChoice){
                case 1 -> eventController.createEvent(taskManager,scanner);
                case 2 -> eventController.displayEvent(taskManager);
                case 3 -> eventController.editEvent(taskManager,scanner);
                case 4 -> eventController.deleteEvent(taskManager,scanner);
                case 5 -> {return;}
                default -> System.out.println("invalid. only 1-5");
            }
        }
    }

    public void deadlineTaskMenu(){
        int deadlineTaskChoice;
        while(true){
            System.out.println("1. add deadline task");
            System.out.println("2. display deadline task");
            System.out.println("3. edit deadline task");
            System.out.println("4. delete deadline task");
            System.out.println("5. back to main menu");
            try{
                deadlineTaskChoice =Integer.parseInt(scanner.nextLine());
            }catch (NumberFormatException e){
                System.out.println("invalid. only digit");
                continue;
            }
            switch (deadlineTaskChoice){
                case 1 -> deadlineTaskController.createDeadlineTask(taskManager,scanner);
                case 2 -> deadlineTaskController.displayDeadlineTask(taskManager);
                case 3 -> deadlineTaskController.editDeadlineTask(taskManager,scanner);
                case 4 -> deadlineTaskController.deleteDeadlineTask(taskManager,scanner);
                case 5 -> {return;}
                default -> System.out.println("invalid. only 1-4");
            }
        }
    }


}
