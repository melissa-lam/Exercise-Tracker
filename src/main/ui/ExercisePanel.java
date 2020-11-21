package ui;

import model.Exercise;
import model.ExerciseList;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.Clip;
import javax.sound.sampled.AudioSystem;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import static java.awt.GridBagConstraints.*;

// Represents an exercise panel where user can add and remove exercises and view past exercises
public class ExercisePanel extends JPanel implements ListSelectionListener {
    private JLabel type;
    private JLabel date;
    private JLabel hours;
    private JTextField typeText;
    private JTextField dateText;
    private JTextField hoursText;
    private JButton addExerciseButton;
    private JButton removeExerciseButton;
    private JList list;
    private DefaultListModel listModel;
    private Exercise exercise;
    public ExerciseList exercises;
    private GridBagConstraints gbc;
    private JComboBox<String> chooseFilter;
    private JButton viewByButton;
    private JTextField filter;

    //EFFECTS: constructs the exercise panel
    public ExercisePanel() {
        init();

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridheight = 2;
        gbc.gridwidth = 10;
        gbc.fill = HORIZONTAL;
        topPanel();

        gbc = new GridBagConstraints();

        gbc.gridx = 0;
        gbc.gridy = 3;
        leftPanel();

        gbc.gridx = 4;
        gbc.gridy = 3;
        rightPanel();

        gbc.gridx = 0;
        gbc.gridy = 9;
        gbc.gridheight = 2;
        gbc.gridwidth = 10;
        gbc.fill = HORIZONTAL;
        bottomPanel();

        gbc.gridx = 0;
        gbc.gridy = 11;
        viewBy();
    }

    // EFFECTS: initializes necessary fields
    public void init() {
        listModel = new DefaultListModel();
        exercises = new ExerciseList();
        gbc = new GridBagConstraints();
        this.setLayout(new GridBagLayout());
        this.setBackground(new Color(204,229,255));
    }

    // EFFECTS: returns the list of exercises
    public ExerciseList getExercises() {
        return exercises;
    }

    // MODIFIES: this
    // EFFECTS: makes a panel with text "Track your exercises here!" and adds it to this panel
    public void topPanel() {
        JPanel panel = new JPanel();
        panel.setBackground(new Color(204,255,229));
        JLabel label = new JLabel("Track your exercises here!");
        panel.add(label);
        this.add(panel, gbc);
    }

    // EFFECTS: returns a panel that shows the type and the corresponding text field
    public JPanel createTypePane() {
        JPanel panel = new JPanel(new FlowLayout());
        panel.setBackground(new Color(255, 204, 204));
        type = new JLabel("Type:");
        typeText = new JTextField();
        typeText.setPreferredSize(new Dimension(100,30));
        panel.add(type);
        panel.add(typeText);
        panel.setAlignmentX(Component.CENTER_ALIGNMENT);
        return panel;
    }

    // EFFECTS: returns a panel that shows the date and the corresponding text field
    public JPanel createDatePane() {
        JPanel panel = new JPanel(new FlowLayout());
        panel.setBackground(new Color(255, 204, 204));
        date = new JLabel("Date:");
        dateText = new JTextField();
        dateText.setPreferredSize(new Dimension(100,30));
        panel.add(date);
        panel.add(dateText);
        panel.setAlignmentX(Component.CENTER_ALIGNMENT);
        return panel;
    }

    // EFFECTS: returns a panel that shows the hours and the corresponding text field
    public JPanel createHoursPane() {
        JPanel panel = new JPanel();
        panel.setBackground(new Color(255, 204, 204));
        hours = new JLabel("Hours:");
        hoursText = new JTextField();
        hoursText.setPreferredSize(new Dimension(100,30));
        panel.add(hours);
        panel.add(hoursText);
        panel.setAlignmentX(Component.CENTER_ALIGNMENT);
        return panel;
    }

    // MODIFIES: exercise, exercises, listModel
    // EFFECTS: returns a panel that has an add exercise button that adds exercises to the exercises
    //          as well as the listModel
    public JPanel createAddExerciseButton() {
        JPanel panel = new JPanel();
        panel.setBackground(new Color(255, 204, 204));
        addExerciseButton = new JButton("Add Exercise");
        addExerciseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int hours = Integer.parseInt(hoursText.getText());
                exercise = new Exercise(typeText.getText(), dateText.getText(), hours);
                exercises.addExercise(exercise);
                listModel.addElement(exercise.getName());

                typeText.requestFocusInWindow();
                typeText.setText("");
                dateText.requestFocusInWindow();
                dateText.setText("");
                hoursText.requestFocusInWindow();
                hoursText.setText("");
                removeExerciseButton.setEnabled(true);
                playButtonSound("./data/button-3.wav");
            }
        });
        addExerciseButton.setEnabled(true);
        panel.add(addExerciseButton);
        return panel;
    }

    // MODIFIES: this
    // EFFECTS: creates the left panel which shows the text fields to enter a new exercise and adds it to this
    public void leftPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
        panel.add(createTypePane());
        panel.add(createDatePane());
        panel.add(createHoursPane());
        panel.add(createAddExerciseButton());
        this.add(panel, gbc);
    }

    // MODIFIES: list, exercises
    // EFFECTS: returns a jpanel that shows the exercise list in a scroll pane
    public JPanel createList() {
        JPanel panel = new JPanel();
        panel.setBackground(new Color(255, 204, 204));
        for (Exercise e: exercises.getExercises()) {
            listModel.addElement(e.getName());
        }
        list = new JList(listModel);
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.setSelectedIndex(0);
        JScrollPane scrollPane = new JScrollPane(list);
        scrollPane.setPreferredSize(new Dimension(250,110));
        panel.add(scrollPane);
        return panel;
    }

    // MODIFIES: listModel, exercises, list
    // EFFECTS: returns a panel that has a remove exercise button that removes exercises from the exercises
    //          as well as the listModel
    public JPanel createRemoveExerciseButton() {
        JPanel panel = new JPanel();
        panel.setBackground(new Color(255, 204, 204));
        removeExerciseButton = new JButton("Remove Exercise");
        removeExerciseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int index = list.getSelectedIndex();
                listModel.remove(index);
                exercises.removeIndex(index);
                int size = listModel.getSize();

                if (size == 0) {
                    removeExerciseButton.setEnabled(false);
                } else {
                    if (index == listModel.getSize()) {
                        index--;
                    }
                    list.setSelectedIndex(index);
                    list.ensureIndexIsVisible(index);
                }
            }
        });
        panel.add(removeExerciseButton);
        return panel;
    }

    // MODIFIES: this
    // EFFECTS: creates the panel showing the list scroll pane and the remove exercise button and adds it to this
    public void rightPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
        panel.add(createList());
        panel.add(createRemoveExerciseButton());
        this.add(panel, gbc);
    }

    // MODIFIES: this
    // EFFECTS: make a panel that displays options to view by all exercises or by type or date
    public void viewBy() {
        JPanel panel = new JPanel();
        panel.setBackground(new Color(255, 204, 204));
        panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));

        makeComboBox();
        filter = new JTextField();

        panel.add(chooseFilter);
        panel.add(filter);
        makeViewByButton();

        panel.add(viewByButton);
        this.add(panel, gbc);
    }

    // MODIFIES: this
    // EFFECTS: make a combo box to choose between "All", "Type", or "Date"
    public void makeComboBox() {
        chooseFilter = new JComboBox<>();
        chooseFilter.addItem("Filter by:");
        chooseFilter.addItem("All");
        chooseFilter.addItem("Type");
        chooseFilter.addItem("Date");
    }

    // MODIFIES: this, listModel
    // EFFECTS: make a button that updates list to show either all exercises or by date or type
    public void makeViewByButton() {
        viewByButton = new JButton("View by...");
        viewByButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                listModel.clear();
                if (chooseFilter.getSelectedItem().toString().equals("All")) {
                    for (Exercise er: exercises.getExercises()) {
                        listModel.addElement(er.getName());
                    }
                }
                if (chooseFilter.getSelectedItem().toString().equals("Type")) {
                    for (Exercise er: exercises.exercisesFromType(filter.getText()).getExercises()) {
                        listModel.addElement(er.getName());
                    }
                }
                if (chooseFilter.getSelectedItem().toString().equals("Date")) {
                    for (Exercise er: exercises.exercisesFromDate(filter.getText()).getExercises()) {
                        listModel.addElement(er.getName());
                    }
                }
                resetFilterTextField();
            }
        });
    }

    // MODIFIES: this
    // EFFECTS: resets the filter text field
    public void resetFilterTextField() {
        filter.requestFocusInWindow();
        filter.setText("");
    }

    // MODIFIES: this
    // EFFECTS: creates panel showing text "Keep up the great work!" and adds it to this
    public void bottomPanel() {
        JPanel panel = new JPanel();
        panel.setBackground(new Color(204,255,229));
        JLabel label = new JLabel("Keep up the great work!");
        panel.add(label);
        this.add(panel, gbc);
    }

    // MODIFIES: listModel
    // EFFECTS: clears old listModel and inserts updated exercise list
    public void display(ExerciseList exercises) {
        listModel.clear();
        for (Exercise e: exercises.getExercises()) {
            listModel.addElement(e.getName());
        }
        this.exercises = exercises;
    }

    // MODIFIES: this
    // EFFECTS: takes an audio file and plays the audio
    public void playButtonSound(String sound) {
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(sound).getAbsoluteFile());
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
        } catch (Exception e) {
            System.out.println("Error with playing button sound");
        }
    }

    // MODIFIES: removeExerciseButton
    // EFFECTS: sets the remove exercise button to enable/disable based on if an exercise is selected
    @Override
    public void valueChanged(ListSelectionEvent e) {
        if (e.getValueIsAdjusting() == false) {
            if (list.getSelectedIndex() == -1) {
                removeExerciseButton.setEnabled(false);
            } else {
                removeExerciseButton.setEnabled(true);
            }
        }
    }

}
