package mini_proj;

import mini_proj.GUI.MenuGUI;

import javax.swing.*;
import java.util.Scanner;

public class Main {
    private Scanner scanner=new Scanner(System.in);

    private Menu menu=new Menu();

    private TaskManager taskManager=new TaskManager();
    private EventController eventController=new EventController();
    private DeadlineTaskController deadlineTaskController=new DeadlineTaskController();
    private FloatingTaskController floatingTaskController=new FloatingTaskController();

    public static void main(String[] args) {
//        Main main=new Main();
//        main.start();
        new Main().menu();
    }

    public void menu(){
        int input;
        while (true){
            menu.gui_cli_Menu();
            try{
                input=Integer.parseInt(scanner.nextLine());
            }catch (NumberFormatException e){
                System.out.println("invalid. only digit");
                continue;
            }
            switch (input){
                case 1 -> {
                    menuGUI();
                    return;
                }
                case 2 -> cliMenu();
                default -> System.out.println("invalid. only 1-2");
            }
        }
    }

    public void cliMenu(){
        int input;
        while(true) {
            menu.cliMenu();
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
                default -> System.out.println("invalid. only 1-3");
            }
        }
    }

    public void floatingTaskMenu() {
        int floatingTaskChoice;
        while(true){
            menu.floatingTaskMenu();
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
                case 5 -> floatingTaskController.searchTask(taskManager,scanner);
                case 6 -> {return;}
                default -> System.out.println("invalid. only 1-5");
            }
        }
    }


    public void eventMenu(){
        int eventChoice;
        while(true){
                menu.eventMenu();
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
                case 5 -> eventController.searchEvent(taskManager,scanner);
                case 6 -> {return;}
                default -> System.out.println("invalid. only 1-5");
            }
        }
    }

    public void deadlineTaskMenu(){
        int deadlineTaskChoice;
        while(true){
                menu.deadlineTaskMenu();
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
                case 5 -> deadlineTaskController.searchDeadlineTask(taskManager,scanner);
                case 6 -> {return;}
                default -> System.out.println("invalid. only 1-4");
            }
        }
    }

    public void menuGUI(){
        SwingUtilities.invokeLater(()->{
            MenuGUI menuGUI=new MenuGUI();
            menuGUI.setVisible(true);
        });

    }


}
