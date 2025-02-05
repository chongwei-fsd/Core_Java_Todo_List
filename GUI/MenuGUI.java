package mini_proj.GUI;

import mini_proj.TaskManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuGUI extends JFrame {
    private TaskManager taskManager=new TaskManager();

    private JButton eventBtn, deadlineBtn, floatingBtn;

    public MenuGUI(){
        //set up frame
        setTitle("Menu");
        setSize(300,300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // panel and grid layout
        JPanel panel=new JPanel();
        panel.setLayout(new GridLayout(3,1));

        //buttons
        eventBtn=new JButton("Event");
        panel.add(eventBtn);

        deadlineBtn=new JButton("Deadline task");
        panel.add(deadlineBtn);

        floatingBtn=new JButton("Floating task");
        panel.add(floatingBtn);

        add(panel,BorderLayout.NORTH);

        //event button action
        eventBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); //close current window
                eventGUI();
            }
        });

        //deadline button action
        deadlineBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); //close current window
                deadlineTaskGUI();
            }
        });

        //floating task button action
        floatingBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); //close current window
                floatingTaskGUI();
            }
        });

    }

    public void eventGUI() {
        SwingUtilities.invokeLater(() -> {
            EventGUI gui = new EventGUI(taskManager);
            gui.setVisible(true);
        });
    }

    public void deadlineTaskGUI(){
        SwingUtilities.invokeLater(()->{
            dispose(); //close current window
            DeadlineTaskGUI deadlineTaskGUI=new DeadlineTaskGUI(taskManager);
            deadlineTaskGUI.setVisible(true);
        });
    }

    public void floatingTaskGUI(){
        SwingUtilities.invokeLater(()->{
            FloatingTaskGUI floatingTaskGUI=new FloatingTaskGUI(taskManager);
            floatingTaskGUI.setVisible(true);
        });
    }
}
