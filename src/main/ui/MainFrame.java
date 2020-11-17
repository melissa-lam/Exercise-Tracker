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
    ExerciseList exercises = new ExerciseList();
    Goals goals = new Goals();
    Goals completedGoals = new Goals();
    JPanel masterPanel;
    JPanel mainPanel;
    JPanel exercisePanel;
    JPanel goalsPanel;
    CardLayout cl = new CardLayout();

    public MainFrame() {
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        masterPanel = new JPanel();
        masterPanel.setLayout(cl);
        mainPanel = new JPanel();
        exercisePanel = new ExercisePanel();
        goalsPanel = new GoalsPanel();

        masterPanel.add(mainPanel, "mPanel");
        masterPanel.add(exercisePanel, "ePanel");
        masterPanel.add(goalsPanel, "gPanel");

        cl.show(masterPanel, "mPanel");

        makeMainPanel();
        fileMenu();
        trackerMenu();
        this.setJMenuBar(menuBar);
        this.add(masterPanel);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(500,500);
        this.setResizable(true);
        this.setTitle("Exercise Tracker");

        this.getContentPane().setBackground(new Color(239,222,205));
        this.setVisible(true);

    }

    public JPanel makeMainPanel() {
        mainPanel.setBackground(new Color(204,229,255));
        JLabel label = new JLabel();
        label.setText("Start tracking your workout routine!");
        label.setFont(new Font("Arial", Font.BOLD, 20));
        label.setIcon(workoutImage);
        JLabel label2 = new JLabel();
//        label2.setText();
        mainPanel.add(label);
        return mainPanel;
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
                cl.show(masterPanel, "mPanel");
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
                cl.show(masterPanel, "ePanel");
            }
        });
    }

    public void goalsItem() {
        goalsItem = new JMenuItem("Goals");
        goalsItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cl.show(masterPanel, "gPanel");
            }
        });
    }
}
