package mini_proj.GUI;

import mini_proj.FloatingTask;
import mini_proj.Task;
import mini_proj.TaskManager;

import javax.swing.*;
import java.util.List;

public class FloatingTaskControllerGUI {
    public void createFloatingTaskGUI(TaskManager taskManager, String title, String description, JTextArea displayArea) {
        if (title.isEmpty() || description.isEmpty())
            JOptionPane.showMessageDialog(null, "input cannot be empty");
        else {
            FloatingTask floatingTask = new FloatingTask(title, description, false);
            taskManager.addTask(floatingTask);
            JOptionPane.showMessageDialog(null, title + " added successfully");
            displayFloatingTaskGUI(taskManager,displayArea);
        }
    }

    public void displayFloatingTaskGUI(TaskManager taskManager, JTextArea displayArea) {
        StringBuilder string = new StringBuilder();
        List<Task> tasks=taskManager.getTasks();

        for (Task t : tasks) {
            if (t instanceof FloatingTask) {
                string.append("Title: ").append(t.getTitle()).append("\n")
                        .append("Description: ").append(t.getDescription()).append("\n")
                        .append("Completion: ").append(t.isComplete()?"Completed":"Not complete").append("\n\n");

            }
        }
        if(string.length()==0)string.append("no floating task found");
        displayArea.setText(string.toString());
    }

    public void editFloatingTaskGUI(TaskManager taskManager,String title,JTextArea displayArea) {
        if(title.isEmpty())JOptionPane.showMessageDialog(null,"input cannot be empty");
        else{
            FloatingTask floatingTask=findFloatingTaskTitle(taskManager,title);
            if(floatingTask==null)JOptionPane.showMessageDialog(null,title+" not found");
            else{
                String newTitle=JOptionPane.showInputDialog("Enter new title:",floatingTask.getTitle());
                String newDesc=JOptionPane.showInputDialog("Enter new description:",floatingTask.getDescription());

                // Confirm dialog for completion status (Yes = true, No = false)
                int completionStatus = JOptionPane.showConfirmDialog(
                        null,
                        "Is the task complete?",
                        "Task Completion",
                        JOptionPane.YES_NO_OPTION
                );
                boolean isComplete = completionStatus == JOptionPane.YES_OPTION;

                floatingTask.setTitle(newTitle);
                floatingTask.setDescription(newDesc);
                floatingTask.setComplete(isComplete);

                taskManager.saveToFile();

                JOptionPane.showMessageDialog(null,newTitle+" updated successfully");
            }
            displayFloatingTaskGUI(taskManager,displayArea);
        }
    }

    public void deleteFloatingTaskGUI(TaskManager taskManager, String title, JTextArea displayArea) {
        if(title.isEmpty())JOptionPane.showMessageDialog(null,"input cannot be empty");
        else{
            FloatingTask floatingTask=findFloatingTaskTitle(taskManager,title);
            if(floatingTask==null)JOptionPane.showMessageDialog(null,title+" not found");
            else{
                taskManager.deleteTask(floatingTask);
                JOptionPane.showMessageDialog(null,title+" deleted successfully");
            }
        }
        displayFloatingTaskGUI(taskManager,displayArea);
    }

    public void searchFloatingTaskGUI(TaskManager taskManager, String searchText, JTextArea displayArea) {
        StringBuilder string=new StringBuilder();
        if(searchText==null){
            return;
        }
        else if(searchText.isEmpty()){
            JOptionPane.showMessageDialog(null,"input cannot be empty");
        }
        else{
            for(Task t:taskManager.getTasks()){
                if(t instanceof FloatingTask){
                    FloatingTask floatingTask=(FloatingTask) t;
                    if(floatingTask.getTitle().contains(searchText) || floatingTask.getDescription().contains(searchText)){
                        string.append("Title: "+floatingTask.getTitle()+"\nDescription: "+floatingTask.getDescription()+"\nCompletion: "+(floatingTask.isComplete()?"Completed\n\n":"Not complete\n\n"));
                    }
                }
            }
        }
        if(string.length()==0) string.append("no floating task found");
        displayArea.setText(string.toString());
    }

    public FloatingTask findFloatingTaskTitle(TaskManager taskManager, String taskTitle) {
        for (Task t : taskManager.getTasks()) {
            if (t instanceof FloatingTask && t.getTitle().equals(taskTitle)) {
                return (FloatingTask) t;
            }
        }
        return null;
    }
}
