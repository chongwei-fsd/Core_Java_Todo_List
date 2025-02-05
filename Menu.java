package mini_proj;

public class Menu {

    public void gui_cli_Menu(){
        System.out.println("1. GUI menu");
        System.out.println("2. CLI menu");
    }

    public void cliMenu(){
        System.out.println("1. event");
        System.out.println("2. deadline task");
        System.out.println("3. floating task");
    }

    public void eventMenu(){
        System.out.println("1. add event");
        System.out.println("2. display event");
        System.out.println("3. edit event");
        System.out.println("4. delete event");
        System.out.println("5. search event");
        System.out.println("6. back to main menu");
    }

    public void deadlineTaskMenu(){
        System.out.println("1. add deadline task");
        System.out.println("2. display deadline task");
        System.out.println("3. edit deadline task");
        System.out.println("4. delete deadline task");
        System.out.println("5. search deadline task");
        System.out.println("6. back to main menu");
    }

    public void floatingTaskMenu(){
        System.out.println("1. add floating task");
        System.out.println("2. display floating task");
        System.out.println("3. edit floating task");
        System.out.println("4. delete floating task");
        System.out.println("5. search floating task");
        System.out.println("6. back to main menu");
    }

    public void editEventMenu(String eventTitle) {
        System.out.println("1. edit "+eventTitle+"'s title");
        System.out.println("2. edit "+eventTitle+"'s description");
        System.out.println("3. edit "+eventTitle+"'s start time");
        System.out.println("4. edit "+eventTitle+"'s end time");
        System.out.println("5. edit "+eventTitle+"'s status (completion)");
        System.out.println("6. back to event menu");
    }

    public void editDeadlineTaskMenu(String deadlineTaskTitle) {
        System.out.println("1. edit "+deadlineTaskTitle+"'s title");
        System.out.println("2. edit "+deadlineTaskTitle+"'s description");
        System.out.println("3. edit "+deadlineTaskTitle+"'s deadline");
        System.out.println("4. edit "+deadlineTaskTitle+"'s status (completion)");
        System.out.println("5. back to deadline task menu");
    }

    public void editFloatingTaskMenu(String floatingTaskTitle) {
        System.out.println("1. edit "+floatingTaskTitle+"'s title");
        System.out.println("2. edit "+floatingTaskTitle+"'s description");
        System.out.println("3. edit "+floatingTaskTitle+"'s status (completion)");
        System.out.println("4. back to floating task menu");
    }
}
