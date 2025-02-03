package mini_proj;

public class Menu {

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
