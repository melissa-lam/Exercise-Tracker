package ui;

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

// Represents the main frame of the application
public class MainFrame extends JFrame {
    private static final String JSON_STORE = "./data/exercisetracker.json";
    ImageIcon workoutImage = new ImageIcon("./data/workout.png");
    ImageIcon loadImage = new ImageIcon("./data/load.jpg");
    ImageIcon saveImage = new ImageIcon("./data/save.png");
    ImageIcon goalsImage = new ImageIcon("./data/goals.png");
    JMenuBar menuBar;
    JMenu fileMenu;
    JMenu trackerMenu;
    JMenuItem mainItem;
    JMenuItem loadItem;
    JMenuItem saveItem;
    JMenuItem exercisesItem;
    JMenuItem goalsItem;
    JsonWriter jsonWriter;
    JsonReader jsonReader;
    JPanel masterPanel;
    JPanel mainPanel;
    ExercisePanel exercisePanel;
    GoalsPanel goalsPanel;
    CardLayout cl = new CardLayout();
    JPanel panel1;
    JPanel panel2;

    // EFFECTS: constructs the main frame of the application
    public MainFrame() {
        init();

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
        this.setSize(580,400);
        this.setResizable(true);
        this.setTitle("Exercise Tracker");

        this.getContentPane().setBackground(new Color(239,222,205));
        this.setVisible(true);

    }

    // EFFECTS: initializes necessary fields
    public void init() {
        menuBar = new JMenuBar();
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        cl = new CardLayout();
        masterPanel = new JPanel();
        masterPanel.setLayout(cl);
        mainPanel = new JPanel();
        exercisePanel = new ExercisePanel();
        goalsPanel = new GoalsPanel();
    }

    // MODIFIES: mainPanel
    // EFFECTS: returns the main panel which describes the application
    public JPanel makeMainPanel() {
        mainPanel.setBackground(new Color(204,229,255));
        JLabel label = new JLabel();
        label.setText("Start tracking your workout routine!");
        label.setFont(new Font("Arial", Font.BOLD, 20));

        makeMainPanelOne();

        makeMainPanelTwo();

        mainPanel.add(label);
        mainPanel.add(panel1);
        mainPanel.add(panel2);

        return mainPanel;
    }

    // MODIFIES: panel1
    // EFFECTS: returns a panel describing the exercise tracker
    public void makeMainPanelOne() {
        panel1 = new JPanel();
        panel1.setLayout(new BoxLayout(panel1, BoxLayout.Y_AXIS));
        panel1.setBackground(new Color(204,204,255));
        panel1.setPreferredSize(new Dimension(400,90));
        JLabel header1 = new JLabel("Track your exercises:");
        header1.setIcon(workoutImage);
        JLabel text1 = new JLabel("Record your exercises by the type, date, and number of hours!");
        JLabel text2 = new JLabel("View all exercises or by type or date!");

        Dimension minSize = new Dimension(18,18);
        Dimension prefSize = new Dimension(18,18);
        Dimension maxSize = new Dimension(Short.MAX_VALUE, 18);

        panel1.add(new Box.Filler(minSize, prefSize, maxSize));
        panel1.add(header1);
        panel1.add(text1);
        panel1.add(text2);
    }

    // MODIFIES: panel2
    // EFFECTS: returns a panel describing the goals tracker
    public void makeMainPanelTwo() {
        panel2 = new JPanel();
        panel2.setLayout(new BoxLayout(panel2, BoxLayout.PAGE_AXIS));
        panel2.setBackground(new Color(204,204,255));
        panel2.setPreferredSize(new Dimension(400,100));
        JLabel header2 = new JLabel("Track your goals:");
        header2.setIcon(goalsImage);
        JLabel text2 = new JLabel("Set your fitness goal for today!");
        JLabel text21 = new JLabel("Keep track of completed goals!");

        Dimension minSize = new Dimension(18,18);
        Dimension prefSize = new Dimension(18,18);
        Dimension maxSize = new Dimension(Short.MAX_VALUE, 18);

        panel2.add(new Box.Filler(minSize, prefSize, maxSize));
        panel2.add(header2);
        panel2.add(text2);
        panel2.add(text21);
    }

    // MODIFIES: fileMenu
    // EFFECTS: adds the file menu to the menu bar
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

    // MODIFIES: mainItem
    // EFFECTS: makes the Main menu item that shows the main panel
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

    // MODIFIES: loadItem
    // EFFECTS: makes the Load menu item that calls a method to load the previous files saved
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

    // MODIFIES: exercisePanel, goalsPanel
    // EFFECTS: loads exercise tracker from file
    private void loadExerciseTracker() {
        try {
            exercisePanel.display(jsonReader.readExercises());
            goalsPanel.displayGoals(jsonReader.readGoals());
            goalsPanel.displayCompletedGoals(jsonReader.readCompletedGoals());
            System.out.println("Loaded from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }

    // MODIFIES: saveItem
    // EFFECTS: makes the Save menu item which calls a method to save to the file
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

    // MODIFIES: this
    // EFFECTS: saves exercise tracker to the file
    private void saveExerciseTracker() {
        try {
            jsonWriter.open();
            jsonWriter.write(exercisePanel.getExercises(), goalsPanel.getGoals(), goalsPanel.getCompletedGoals());
            jsonWriter.close();
            System.out.println("Saved " + " to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    // MODIFIES: trackerMenu, menuBar
    // EFFECTS: makes the tracker menu and adds to menuBar
    public void trackerMenu() {
        exercisesItem();
        goalsItem();
        trackerMenu = new JMenu("Tracker");
        trackerMenu.add(exercisesItem);
        trackerMenu.add(goalsItem);
        menuBar.add(trackerMenu);
    }

    // MODIFIES: this, exercisesItem
    // EFFECTS: makes the exerciseItem menu item which shows the exercise panel when clicked
    public void exercisesItem() {
        exercisesItem = new JMenuItem("Exercises");
        exercisesItem.setIcon(workoutImage);
        exercisesItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                exercisePanel.display(exercisePanel.getExercises());
                cl.show(masterPanel, "ePanel");
            }
        });
    }

    // MODIFIES: this, goalsItem
    // EFFECTS: makes the goalsItem menu item which shows the goals panel when clicked
    public void goalsItem() {
        goalsItem = new JMenuItem("Goals");
        goalsItem.setIcon(goalsImage);
        goalsItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                goalsPanel.displayGoals(goalsPanel.getGoals());
                goalsPanel.displayCompletedGoals(goalsPanel.getCompletedGoals());
                cl.show(masterPanel, "gPanel");
            }
        });
    }
}
