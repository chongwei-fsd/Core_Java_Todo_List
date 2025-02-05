package mini_proj;

import java.time.LocalDateTime;
import java.util.Scanner;

public class EventController {

    PromptCheck promptCheck = new PromptCheck();
    Menu menu = new Menu();

    public void createEvent(TaskManager taskManager, Scanner scanner) {
        System.out.println("***************** add event **********************");
        String eventTitle = promptCheck.getStringInput(scanner, "enter event title");
        String eventDesc = promptCheck.getStringInput(scanner, "enter event description");
        LocalDateTime startTime = promptCheck.getDateTimeInput(scanner, "enter event start time yyyy-MM-dd HH:mm");
        LocalDateTime endTime = promptCheck.getDateTimeInput(scanner, "enter event end time yyyy-MM-dd HH:mm");

        Event event = new Event(eventTitle, eventDesc, false, startTime, endTime);
        taskManager.addTask(event);
        System.out.println(eventTitle+" added successfully\n");
    }

    public void displayEvent(TaskManager taskManager) {
        System.out.println("***************** events **********************");
        boolean flag = false;
        for (Task t : taskManager.getTasks()) {
            if (t instanceof Event) {
                flag = true;
                System.out.println("title: " + t.getTitle());
                System.out.println("description: " + t.getDescription());
                System.out.println("complete: " + (t.isComplete()?"Complete":"Not complete"));
                System.out.println("start time: " + ((Event) t).getStartTime());
                System.out.println("end time: " + ((Event) t).getEndTime()+"\n");
            }
        }
        if (!flag) {
            System.out.println("no event added\n");
        }
    }

    public void editEvent(TaskManager taskManager, Scanner scanner) {
        do {
            String eventTitle = promptCheck.getStringInput(scanner, "enter event title:");
            Event event = findEventTitle(taskManager, eventTitle);
            if (event != null) {
                System.out.println("event title found - " + eventTitle+"\n");
                while (true) {
                    menu.editEventMenu(event.getTitle());
                    int choice;
                    try {
                        choice = Integer.parseInt(scanner.nextLine());
                    } catch (NumberFormatException e) {
                        System.out.println("invalid. only digit");
                        continue;
                    }
                    switch (choice) {
                        case 1 -> editEventTitle(taskManager, scanner, eventTitle, event);
                        case 2 -> editEventDesc(taskManager, scanner, eventTitle, event);
                        case 3 -> editEventStartTime(taskManager, scanner, eventTitle, event);
                        case 4 -> editEventEndTime(taskManager, scanner, eventTitle, event);
                        case 5 -> editEventCompletion(taskManager, scanner, eventTitle, event);
                        case 6 -> {
                            return;
                        }
                        default -> System.out.println("invalid. only 1-6");
                    }
                }
            }
            if (event == null) System.out.println(eventTitle + " not found\n");

        } while (true);
    }


    public void editEventEndTime(TaskManager taskManager, Scanner scanner, String eventTitle, Event event) {
        System.out.println("********* edit event end time *********");
        LocalDateTime newEndTime = promptCheck.getDateTimeInput(scanner, "enter new event end time yyyy-MM-dd HH:mm");
        event.setEndTime(newEndTime);
        System.out.println(eventTitle+" updated\n");
    }

    public void editEventStartTime(TaskManager taskManager, Scanner scanner, String eventTitle, Event event) {
        System.out.println("********* edit event start time *********");
        LocalDateTime newStartTime = promptCheck.getDateTimeInput(scanner, "enter new event start time yyyy-MM-dd HH:mm");
        event.setStartTime(newStartTime);
        System.out.println(eventTitle+" updated\n");
    }

    public void editEventDesc(TaskManager taskManager, Scanner scanner, String eventTitle, Event event) {
        System.out.println("********* edit event description *********");
        String newEventDesc = promptCheck.getStringInput(scanner, "enter new event description");
        event.setDescription(newEventDesc);
        System.out.println(eventTitle+" updated\n");
    }

    public void editEventCompletion(TaskManager taskManager, Scanner scanner, String eventTitle, Event event) {
        System.out.println("********* edit event completion *********");
        boolean eventComplete = promptCheck.setComplete(scanner, "is event completed? (Y/N)");
        event.setComplete(eventComplete);
        System.out.println(event.getTitle()+" set to "+(eventComplete?"complete\n":"not complete\n"));
    }

    public void editEventTitle(TaskManager taskManager, Scanner scanner, String eventTitle, Event event) {
        System.out.println("********* edit event title *********");
        String newEventTitle = promptCheck.getStringInput(scanner, "enter new event title");
        event.setTitle(newEventTitle);
        System.out.println(newEventTitle+" updated\n");
    }

    public void deleteEvent(TaskManager taskManager, Scanner scanner) {
        System.out.println("********* delete event *********");
        while (true){
            String eventTitle=promptCheck.getStringInput(scanner,"enter event title you wish to delete");
            Event event=findEventTitle(taskManager,eventTitle);
            if(event==null){
                System.out.println(eventTitle+" not found\n");
            }
            if(event!=null){
                taskManager.deleteTask(event);
                System.out.println(eventTitle+" has been deleted\n");
                return;
            }
        }
    }

    public Event findEventTitle(TaskManager taskManager, String eventTitle) {
        for (Task t : taskManager.getTasks()) {
            if (t instanceof Event && t.getTitle().equals(eventTitle)) {
                return (Event) t;
            }
        }
        return null;
    }

    public void searchEvent(TaskManager taskManager, Scanner scanner) {
        boolean flag=false;
        String searchText=promptCheck.getStringInput(scanner,"search for event (title/description)");
        System.out.println("***************** events **********************");
        for(Task t:taskManager.getTasks()){
            if(t instanceof Event){
                if(t.getTitle().contains(searchText) || t.getDescription().contains(searchText)){
                    flag=true;
                    System.out.println("title: "+t.getTitle());
                    System.out.println("description: "+t.getDescription());
                    System.out.println("start time: "+((Event) t).getStartTime());
                    System.out.println("end time: "+((Event) t).getEndTime());
                    System.out.println("completion: "+(t.isComplete()?"Completed\n":"Not complete\n"));
                }
            }
        }
        if(!flag){
            System.out.println(searchText+" not found\n");
        }
    }


}


