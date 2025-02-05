package mini_proj.GUI;

import mini_proj.Main;
import mini_proj.TaskManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DeadlineTaskGUI extends JFrame {
    private TaskManager taskManager;
    private DeadlineTaskControllerGUI deadlineTaskGUIController;
    private Main main;

    private JTextArea displayArea;
    private JTextField titleField, descField, deadlineField;
    private JButton addButton, displayButton, editButton, deleteButton,backButton,searchButton;

    public DeadlineTaskGUI(TaskManager taskManager){
        this.taskManager=taskManager;
        this.deadlineTaskGUIController=new DeadlineTaskControllerGUI();
        this.main=new Main();

        //set up frame
        setTitle("Deadline task");
        setSize(400,400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // panel and grid layout
        JPanel panel=new JPanel();
        panel.setLayout(new GridLayout(6,2));

        //labels and input field
        panel.add(new JLabel("Deadline task title")); //label
        titleField=new JTextField();
        panel.add(titleField); //input field

        panel.add(new JLabel("Deadline task description:"));
        descField = new JTextField();
        panel.add(descField);

        panel.add(new JLabel("Deadline time (yyyy-MM-dd HH:mm):"));
        deadlineField = new JTextField();
        panel.add(deadlineField);

        //buttons
        addButton=new JButton("Add deadline task");
        panel.add(addButton);

        displayButton = new JButton("Display deadline tasks");
        panel.add(displayButton);

        editButton = new JButton("Edit deadline task");
        panel.add(editButton);

        deleteButton = new JButton("Delete deadline task");
        panel.add(deleteButton);

        backButton = new JButton("Back to main menu");
        panel.add(backButton);

        searchButton = new JButton("Search deadline task");
        panel.add(searchButton);

        //display area
        displayArea=new JTextArea();
        displayArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(displayArea);

        add(panel,BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER); //display text

        //add button action
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String title=titleField.getText();
                String description=descField.getText();
                String deadline=deadlineField.getText();

                deadlineTaskGUIController.createDeadlineTaskGUI(taskManager,title,description,deadline,displayArea);
                titleField.setText("");
                descField.setText("");
                deadlineField.setText("");
            }
        });

        //display button action
        displayButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deadlineTaskGUIController.displayDeadLineTaskGUI(taskManager,displayArea);
            }
        });

        //edit button action
        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String title=JOptionPane.showInputDialog("Enter deadline task title to edit:");
                deadlineTaskGUIController.editDeadLineTaskGUI(taskManager,title,displayArea);
            }
        });

        //delete button action
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String title=JOptionPane.showInputDialog("Enter event title to delete:");
                deadlineTaskGUIController.deleteDeadLineTaskGUI(taskManager,title,displayArea);
            }
        });

        //back button action
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); //close current window
                main.menuGUI();
            }
        });

        //search button action
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String searchText=JOptionPane.showInputDialog("Search deadline task (title/description):");
                deadlineTaskGUIController.searchDeadlineTaskGUI(taskManager,searchText,displayArea);
            }
        });

    }

}
