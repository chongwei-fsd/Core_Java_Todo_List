package mini_proj;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TaskManager {
    private List<Task> tasks;

    public TaskManager() {
        this.tasks = new ArrayList<>();
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public void deleteTask(Task task) {
        tasks.remove(task);
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public List<Task> searchByTitle(String title) {
        return tasks.stream()
                .filter(t -> t.getTitle().toLowerCase().contains(title.toLowerCase()))
                .collect(Collectors.toList());
    }

    public List<Task> searchByDesc(String desc) {
        return tasks.stream()
                .filter(t -> t.getDescription().toLowerCase().contains(desc.toLowerCase()))
                .collect(Collectors.toList());
    }

    public List<Task> searchByComplete(boolean isComplete) {
        if(isComplete){
            return tasks.stream()
                    .filter(t -> t.isComplete() == "complete")
                    .collect(Collectors.toList());
        }
        return tasks.stream()
                .filter(t -> t.isComplete()=="not complete")
                .collect(Collectors.toList());
    }


}
