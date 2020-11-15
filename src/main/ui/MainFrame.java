package ui;

import model.ExerciseList;
import model.Goals;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import javax.swing.JFrame;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;

public class MainFrame extends JFrame {
    private static final String JSON_STORE = "./data/exercisetracker.json";
    ImageIcon workoutImage = new ImageIcon("./data/workout.png");
    ImageIcon loadImage = new ImageIcon("./data/load.jpg");
    ImageIcon saveImage = new ImageIcon("./data/save.png");
    JLabel label;
    JMenuBar menuBar = new JMenuBar();
    JMenu fileMenu;
    JMenu trackerMenu;
    JMenuItem mainItem;
    JMenuItem loadItem;
    JMenuItem saveItem;
    JMenuItem exercisesItem;
    JMenuItem goalsItem;
    JsonWriter jsonWriter;
    JsonReader jsonReader;
    ExerciseList exercises;
    Goals goals;
    Goals completedGoals;

    public MainFrame() {
        //label.setOpaque(true);
        //makeTitleLabel();
//        this.setBounds(0,0,50,50);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(500,500);
        this.setResizable(true);
        this.setTitle("Exercise Tracker");
        this.setJMenuBar(menuBar);
        this.add(makeMainPanel());
        fileMenu();
        trackerMenu();
        this.getContentPane().setBackground(new Color(239,222,205));
//        this.pack();
        this.setVisible(true);

    }

    public JPanel makeMainPanel() {
        JPanel panel = new JPanel();
        label = new JLabel();
        label.setText("Start tracking your workout routine!");
        label.setFont(new Font("Arial", Font.BOLD, 30));
        label.setIcon(workoutImage);
        label.setForeground(new Color(0,0,0));
        panel.add(label);
        return panel;
    }

    public void fileMenu() {
        mainItem();
        loadItem();
        saveItem();
        fileMenu = new JMenu("File");
        fileMenu.add(mainItem);
        fileMenu.add(loadItem);
        fileMenu.add(saveItem);
        menuBar.add(fileMenu);
    }

    public void mainItem() {
        mainItem = new JMenuItem("Main");
        mainItem.setIcon(workoutImage);
        mainItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("test");
            }
        });
    }

    public void loadItem() {
        loadItem = new JMenuItem("Load");
        loadItem.setIcon(loadImage);
        loadItem. addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadExerciseTracker();
            }
        });
    }

    private void loadExerciseTracker() {
        try {
            exercises = jsonReader.readExercises();
            goals = jsonReader.readGoals();
            completedGoals = jsonReader.readCompletedGoals();
            System.out.println("Loaded from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }

    public void saveItem() {
        saveItem = new JMenuItem("Save");
        saveItem.setIcon(saveImage);
        saveItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveExerciseTracker();
            }
        });
    }

    private void saveExerciseTracker() {
        try {
            jsonWriter.open();
            jsonWriter.write(exercises, goals, completedGoals);
            jsonWriter.close();
            System.out.println("Saved " + " to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    public void trackerMenu() {
        exercisesItem();
        goalsItem();
        trackerMenu = new JMenu("Tracker");
        trackerMenu.add(exercisesItem);
        trackerMenu.add(goalsItem);
        menuBar.add(trackerMenu);
    }

    public void exercisesItem() {
        exercisesItem = new JMenuItem("Exercises");
        exercisesItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                add(new ExercisePanel());
                revalidate();
                repaint();
            }
        });
    }

    public void goalsItem() {
        goalsItem = new JMenuItem("Goals");
        goalsItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                add(new GoalsPanel());
                revalidate();
                repaint();
            }
        });
    }

//    public void exerciseMenu() {
//        exerciseMenu = new JMenu("Exercises");
//        exerciseMenu.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                if (e.getSource() == exerciseMenu) {
//                    new ExercisePanel2();
//                }
//            }
//        });
//        menuBar.add(exerciseMenu);
//    }
//
//    public void goalsMenu() {
//        goalsMenu = new JMenu("Goals");
//        goalsMenu.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                if (e.getSource() == goalsMenu) {
//                    System.out.println("goals menu");
//                }
//            }
//        });
//        menuBar.add(goalsMenu);
//    }



}
