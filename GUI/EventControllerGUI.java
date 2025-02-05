package mini_proj.GUI;

import mini_proj.Event;
import mini_proj.Task;
import mini_proj.TaskManager;

import javax.swing.*;
import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class EventControllerGUI {

    public void createEventFromGUI(TaskManager taskManager, String title, String description, String startTime, String endTime, JTextArea displayArea) {
        if(title.isEmpty()||description.isEmpty()||startTime.isEmpty()||endTime.isEmpty())JOptionPane.showMessageDialog(null,"input cannot be empty");
        else{
            try {
                LocalDateTime start = LocalDateTime.parse(startTime, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
                LocalDateTime end = LocalDateTime.parse(endTime, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
                Event event = new Event(title, description, false, start, end);
                taskManager.addTask(event);
                JOptionPane.showMessageDialog(null, title + " added successfully.");
            } catch (DateTimeException e) {
                JOptionPane.showMessageDialog(null, "Invalid date/time format.");
            }
            displayEventFromGUI(taskManager, displayArea); // Refresh display
        }
    }

    public void displayEventFromGUI(TaskManager taskManager, JTextArea displayArea) {
        StringBuilder eventDetails = new StringBuilder();
        List<Task> tasks = taskManager.getTasks();

        for (Task task : tasks) {
            if (task instanceof Event) {
                Event event = (Event) task;
                eventDetails.append("Title: ").append(event.getTitle()).append("\n")
                        .append("Description: ").append(event.getDescription()).append("\n")
                        .append("Start Time: ").append(event.getStartTime()).append("\n")
                        .append("End Time: ").append(event.getEndTime()).append("\n")
                        .append("Status: ").append(event.isComplete()?"Completed":"Not complete").append("\n\n");
            }
        }

        if (eventDetails.length() == 0) {
            eventDetails.append("No events found.");
        }

        displayArea.setText(eventDetails.toString());
    }

    public void editEventFromGUI(TaskManager taskManager, String eventTitle, JTextArea displayArea) {
        if(eventTitle.isEmpty())JOptionPane.showMessageDialog(null,"input cannot be empty");
        else{
            Event event = findEventTitle(taskManager, eventTitle);
            if (event != null) {
                String newTitle = JOptionPane.showInputDialog("Enter new title:", event.getTitle());
                String newDesc = JOptionPane.showInputDialog("Enter new description:", event.getDescription());
                String newStartTime = JOptionPane.showInputDialog("Enter new start time (yyyy-MM-dd HH:mm):", event.getStartTime());
                String newEndTime = JOptionPane.showInputDialog("Enter new end time (yyyy-MM-dd HH:mm):", event.getEndTime());

                // Confirm dialog for completion status (Yes = true, No = false)
                int completionStatus = JOptionPane.showConfirmDialog(
                        null,
                        "Is the task complete?",
                        "Task Completion",
                        JOptionPane.YES_NO_OPTION
                );
                boolean isComplete = completionStatus == JOptionPane.YES_OPTION;

                try {
                    LocalDateTime start = LocalDateTime.parse(newStartTime, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
                    LocalDateTime end = LocalDateTime.parse(newEndTime, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));

                    event.setTitle(newTitle);
                    event.setDescription(newDesc);
                    event.setStartTime(start);
                    event.setEndTime(end);
                    event.setComplete(isComplete);

                    taskManager.saveToFile();
                    JOptionPane.showMessageDialog(null, eventTitle + " updated successfully.");
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "Invalid date format.");
                }
            } else {
                JOptionPane.showMessageDialog(null, eventTitle + " not found.");
            }
        }
        displayEventFromGUI(taskManager, displayArea); // Refresh display
    }

    public void deleteEventFromGUI(TaskManager taskManager, String eventTitle, JTextArea displayArea) {
        Event event = findEventTitle(taskManager, eventTitle);
        if (event != null) {
            taskManager.deleteTask(event);
            JOptionPane.showMessageDialog(null, eventTitle + " deleted successfully.");
        } else {
            JOptionPane.showMessageDialog(null, eventTitle + " not found.");
        }
        displayEventFromGUI(taskManager, displayArea); // Refresh display
    }

    public void searchEventGUI(TaskManager taskManager, String searchText, JTextArea displayArea) {
        StringBuilder string=new StringBuilder();
        if(searchText==null){
            return;
        }
        else if(searchText.isEmpty()){
            JOptionPane.showMessageDialog(null,"input cannot be empty");
        }
        else{
            for(Task t:taskManager.getTasks()){
                if(t instanceof Event){
                    Event event=(Event) t;
                    if(event.getTitle().contains(searchText) || event.getDescription().contains(searchText)){
                        string.append("Title: "+event.getTitle()+"\nDescription: "+event.getDescription()+"\nStart time: "+event.getStartTime()+"\nEnd time: "+event.getEndTime()+"\nCompletion: "+(event.isComplete()?"Completed\n\n":"Not complete\n\n"));
                    }
                }
            }
        }
        if(string.length()==0) string.append("no event task found");
        displayArea.setText(string.toString());
    }

    public Event findEventTitle(TaskManager taskManager, String eventTitle) {
        for (Task t : taskManager.getTasks()) {
            if (t instanceof Event && t.getTitle().equals(eventTitle)) {
                return (Event) t;
            }
        }
        return null;
    }


}
