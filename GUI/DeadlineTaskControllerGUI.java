package mini_proj.GUI;

import mini_proj.DeadlineTask;
import mini_proj.Task;
import mini_proj.TaskManager;

import javax.swing.*;
import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class DeadlineTaskControllerGUI {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    public void createDeadlineTaskGUI(TaskManager taskManager, String title, String desc, String deadline, JTextArea displayArea) {
        LocalDateTime deadlineTime=null;
        if(title.isEmpty()||desc.isEmpty()||deadline.isEmpty()) JOptionPane.showMessageDialog(null, "input cannot be empty");
        else{
            try{
                deadlineTime=LocalDateTime.parse(deadline,formatter);
                DeadlineTask deadlineTask=new DeadlineTask(title,desc,false,deadlineTime);
                taskManager.addTask(deadlineTask);
                JOptionPane.showMessageDialog(null, title + " added successfully.");
            }catch(DateTimeException e){
                JOptionPane.showMessageDialog(null,"invalid date/time format");
            }
            displayDeadLineTaskGUI(taskManager,displayArea);
        }
    }

    public void displayDeadLineTaskGUI(TaskManager taskManager,JTextArea displayArea){
        StringBuilder string=new StringBuilder();
        List<Task> tasks=taskManager.getTasks();

        for(Task t:tasks){
            if(t instanceof DeadlineTask){
                DeadlineTask deadlineTask=(DeadlineTask) t;
                string.append("Title: ").append(deadlineTask.getTitle()).append("\n")
                        .append("Description: ").append(deadlineTask.getDescription()).append("\n")
                        .append("Deadline: ").append(deadlineTask.getDeadline()).append("\n")
                        .append("Completion: ").append(deadlineTask.isComplete()?"Completed":"Not complete").append("\n\n");
            }
        }
        if(string.length()==0) string.append("no deadline task found");
        displayArea.setText(string.toString());
    }

    public void editDeadLineTaskGUI(TaskManager taskManager,String title,JTextArea displayArea){
        LocalDateTime newDeadlineTime=null;
        if(title.isEmpty())JOptionPane.showMessageDialog(null,"Input cannot be empty");
        else{
            DeadlineTask deadlineTask=findDeadlineTaskTitle(taskManager,title);
            if(deadlineTask==null)JOptionPane.showMessageDialog(null,title+" not found");
            else{
                String newTitle = JOptionPane.showInputDialog("Enter new title:", deadlineTask.getTitle());
                String newDesc = JOptionPane.showInputDialog("Enter new desc:", deadlineTask.getDescription());
                String newDeadline = JOptionPane.showInputDialog("Enter new deadline (yyyy-MM-dd HH:mm):", deadlineTask.getDeadline());

                // Confirm dialog for completion status (Yes = true, No = false)
                int completionStatus = JOptionPane.showConfirmDialog(
                        null,
                        "Is the task complete?",
                        "Task Completion",
                        JOptionPane.YES_NO_OPTION
                );
                boolean isComplete = completionStatus == JOptionPane.YES_OPTION;

                try{
                    newDeadlineTime=LocalDateTime.parse(newDeadline,formatter);
                    deadlineTask.setTitle(newTitle);
                    deadlineTask.setDescription(newDesc);
                    deadlineTask.setDeadline(newDeadlineTime);
                    deadlineTask.setComplete(isComplete);

                    taskManager.saveToFile();
                    JOptionPane.showMessageDialog(null, newTitle + " updated successfully.");
                }catch(DateTimeException e){
                    JOptionPane.showMessageDialog(null, "Invalid date format.");
                }
            }
        }
        displayDeadLineTaskGUI(taskManager, displayArea); // Refresh display
    }

    public void deleteDeadLineTaskGUI(TaskManager taskManager,String title,JTextArea displayArea){
        if(title.isEmpty())JOptionPane.showMessageDialog(null,"input cannot be empty");
        else{
            DeadlineTask deadlineTask=findDeadlineTaskTitle(taskManager,title);
            if(deadlineTask==null)JOptionPane.showMessageDialog(null,title+" not found");
            else{
                taskManager.deleteTask(deadlineTask);
                JOptionPane.showMessageDialog(null,title+" deleted successfully");
            }
            displayDeadLineTaskGUI(taskManager,displayArea); // Refresh display
        }
    }

    public void searchDeadlineTaskGUI(TaskManager taskManager, String searchText, JTextArea displayArea) {
        StringBuilder string=new StringBuilder();
        if(searchText==null){
            return;
        }
        else if(searchText.isEmpty()){
            JOptionPane.showMessageDialog(null,"input cannot be empty");
        }
        else{
            for(Task t:taskManager.getTasks()){
                if(t instanceof DeadlineTask){
                    DeadlineTask deadlineTask=(DeadlineTask) t;
                    if(deadlineTask.getTitle().contains(searchText) || deadlineTask.getDescription().contains(searchText)){
                        string.append("Title: "+deadlineTask.getTitle()+"\nDescription: "+deadlineTask.getDescription()+"\nDeadline: "+deadlineTask.getDeadline()+"\nCompletion: "+(deadlineTask.isComplete()?"Completed\n\n":"Not complete\n\n"));
                    }
                }
            }
        }
        if(string.length()==0) string.append("no deadline task found");
        displayArea.setText(string.toString());
    }

    public DeadlineTask findDeadlineTaskTitle(TaskManager taskManager, String taskTitle) {
        for (Task t : taskManager.getTasks()) {
            if (t instanceof DeadlineTask && t.getTitle().equals(taskTitle)) {
                return (DeadlineTask) t;
            }
        }
        return null;
    }




}
