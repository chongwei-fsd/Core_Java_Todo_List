package mini_proj.GUI;

import mini_proj.Main;
import mini_proj.TaskManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EventGUI extends JFrame {
    private TaskManager taskManager;
    private Main main;

    private EventControllerGUI eventControllerGUI;

    private JTextArea displayArea;
    private JTextField titleField, descField, startField, endField;
    private JButton addButton, displayButton, editButton, deleteButton, backButton, searchButton;

    public EventGUI(TaskManager taskManager) {
        this.taskManager = taskManager;
        this.eventControllerGUI = new EventControllerGUI();
        this.main=new Main();

        // Set up frame
        setTitle("Event");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Create input fields and buttons
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(7, 2));

        panel.add(new JLabel("Event Title:"));
        titleField = new JTextField();
        panel.add(titleField);

        panel.add(new JLabel("Event Description:"));
        descField = new JTextField();
        panel.add(descField);

        panel.add(new JLabel("Start Time (yyyy-MM-dd HH:mm):"));
        startField = new JTextField();
        panel.add(startField);

        panel.add(new JLabel("End Time (yyyy-MM-dd HH:mm):"));
        endField = new JTextField();
        panel.add(endField);

        addButton = new JButton("Add Event");
        panel.add(addButton);

        displayButton = new JButton("Display Events");
        panel.add(displayButton);

        editButton = new JButton("Edit Event");
        panel.add(editButton);

        deleteButton = new JButton("Delete Event");
        panel.add(deleteButton);

        backButton = new JButton("Back to main menu");
        panel.add(backButton);

        searchButton = new JButton("Search event");
        panel.add(searchButton);

        // Display area for showing events
        displayArea = new JTextArea();
        displayArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(displayArea);

        add(panel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);

        //add button action
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String title = titleField.getText();
                String description = descField.getText();
                String startTime = startField.getText();
                String endTime = endField.getText();

                eventControllerGUI.createEventFromGUI(taskManager,title,description,startTime,endTime,displayArea);
                titleField.setText("");
                descField.setText("");
                startField.setText("");
                endField.setText("");
            }
        });

        //display button action
        displayButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                eventControllerGUI.displayEventFromGUI(taskManager,displayArea);
            }
        });

        //edit button action
        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String title = JOptionPane.showInputDialog("Enter event title to edit:");
                eventControllerGUI.editEventFromGUI(taskManager,title,displayArea);
            }
        });

        //delete button action
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String title = JOptionPane.showInputDialog("Enter event title to delete:");
                eventControllerGUI.deleteEventFromGUI(taskManager,title,displayArea);
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
                String searchText=JOptionPane.showInputDialog("Search event (title/description):");
                eventControllerGUI.searchEventGUI(taskManager,searchText,displayArea);
            }
        });


    }

}
