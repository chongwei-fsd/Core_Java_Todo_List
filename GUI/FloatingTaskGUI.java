package mini_proj.GUI;

import mini_proj.Main;
import mini_proj.TaskManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FloatingTaskGUI extends JFrame {
    private TaskManager taskManager;
    private FloatingTaskControllerGUI floatingTaskControllerGUI;
    private Main main;

    private JTextArea displayArea;
    private JTextField titleField, descField;
    private JButton addButton, displayButton, editButton, deleteButton,backButton,searchButton;


    public FloatingTaskGUI(TaskManager taskManager){
        this.taskManager=taskManager;
        this.floatingTaskControllerGUI=new FloatingTaskControllerGUI();
        this.main=new Main();

        //set up frame
        setTitle("Floating task");
        setSize(400,400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // panel and grid layout
        JPanel panel=new JPanel();
        panel.setLayout(new GridLayout(5,2));

        //labels and input field
        panel.add(new JLabel("Floating task title")); //label
        titleField=new JTextField();
        panel.add(titleField); //input field

        panel.add(new JLabel("Floating task description:"));
        descField = new JTextField();
        panel.add(descField);

        //buttons
        addButton=new JButton("Add floating task");
        panel.add(addButton);

        displayButton = new JButton("Display floating tasks");
        panel.add(displayButton);

        editButton = new JButton("Edit floating task");
        panel.add(editButton);

        deleteButton = new JButton("Delete floating task");
        panel.add(deleteButton);

        backButton = new JButton("Back to main menu");
        panel.add(backButton);

        searchButton = new JButton("Search floating task");
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

                floatingTaskControllerGUI.createFloatingTaskGUI(taskManager,title,description,displayArea);
                titleField.setText("");
                descField.setText("");
            }
        });

        //display button action
        displayButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                floatingTaskControllerGUI.displayFloatingTaskGUI(taskManager,displayArea);
            }
        });

        //back button action
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                main.menuGUI();
            }
        });

        //edit button action
        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String title=JOptionPane.showInputDialog("Enter floating task title to edit:");
                floatingTaskControllerGUI.editFloatingTaskGUI(taskManager,title,displayArea);
            }
        });

        //delete button action
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String title=JOptionPane.showInputDialog("Enter floating task title to delete:");
                floatingTaskControllerGUI.deleteFloatingTaskGUI(taskManager,title,displayArea);
            }
        });

        //search button action
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String searchText=JOptionPane.showInputDialog("Search floating task (title/description):");
                floatingTaskControllerGUI.searchFloatingTaskGUI(taskManager,searchText,displayArea);
            }
        });


    }



}
