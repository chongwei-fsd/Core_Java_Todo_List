package mini_proj;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TaskManager {
    private List<Task> tasks;
    private static final String FILE_NAME = "tasks.ser";

    public TaskManager() {
//        this.tasks = new ArrayList<>();
        this.tasks = loadFromFile();
    }

    public void addTask(Task task) {
        tasks.add(task);
        saveToFile();
    }

    public void deleteTask(Task task) {
        tasks.remove(task);
        saveToFile();
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
        if (isComplete) {
            return tasks.stream()
                    .filter(t -> t.isComplete()==true)
                    .collect(Collectors.toList());
        }
        return tasks.stream()
                .filter(t -> t.isComplete()==false)
                .collect(Collectors.toList());
    }

    public List<Task> loadFromFile() {
        File file = new File(FILE_NAME);
        if (!file.exists()) return new ArrayList<>(); // Return empty list if file doesn't exist

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            return (List<Task>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error loading tasks: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    public void saveToFile() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            oos.writeObject(tasks);
//            System.out.println("Updated tasks.ser");
        } catch (IOException e) {
            System.out.println("Error saving tasks: " + e.getMessage());
        }
    }


}
