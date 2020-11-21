package ui;

import model.Exercise;
import model.ExerciseList;
import model.Goal;
import model.Goals;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static java.awt.GridBagConstraints.HORIZONTAL;

// Represents an goal panel where user can add and remove goals and view past goals
public class GoalsPanel extends JPanel implements ListSelectionListener {
    private JLabel type;
    private JLabel date;
    private JLabel hours;
    private JTextField typeText;
    private JTextField dateText;
    private JTextField hoursText;
    private JButton addGoalButton;
    private JButton removeGoalButton;
    private JButton completeGoalButton;
    private JList list;
    private DefaultListModel listModel;
    private JList completedList;
    private DefaultListModel completedListModel;
    private Goal goal;
    private Goal completedGoal;
    private Goals goals;
    private Goals completedGoals;
    private GridBagConstraints gbc;
    private JTextField filter;
    private JComboBox<String> chooseFilter;
    private JButton viewByButton;

    //EFFECTS: constructs the goals panel
    public GoalsPanel() {
        init();

        initTopPanel();

        gbc = new GridBagConstraints();

        gbc.gridx = 0;
        gbc.gridy = 2;
        leftPanel();

        gbc.gridx = 4;
        gbc.gridy = 2;
        middlePanel();

        gbc.gridx = 8;
        gbc.gridy = 2;
        rightPanel();

        gbc.gridx = 0;
        gbc.gridy = 8;
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
        completedListModel = new DefaultListModel();
        goals = new Goals();
        completedGoals = new Goals();
        gbc = new GridBagConstraints();
        this.setLayout(new GridBagLayout());
        this.setBackground(new Color(204,229,255));
    }

    //EFFECTS: initializes top panel
    public void initTopPanel() {
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridheight = 2;
        gbc.gridwidth = 10;
        gbc.fill = HORIZONTAL;
        topPanel();
    }

    // EFFECTS: returns the list of goals
    public Goals getGoals() {
        return goals;
    }

    // EFFECTS: returns the list of completed goals
    public Goals getCompletedGoals() {
        return completedGoals;
    }

    // MODIFIES: this
    // EFFECTS: makes a panel with text "Track your exercises here!" and adds it to this panel
    public void topPanel() {
        JPanel panel = new JPanel();
        panel.setBackground(new Color(204,255,229));
        JLabel label = new JLabel("Make your goals here!");
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
        JPanel panel = new JPanel(new FlowLayout());
        panel.setBackground(new Color(255, 204, 204));
        hours = new JLabel("Hours:");
        hoursText = new JTextField();
        hoursText.setPreferredSize(new Dimension(100,30));
        panel.add(hours);
        panel.add(hoursText);
        panel.setAlignmentX(Component.CENTER_ALIGNMENT);
        return panel;
    }

    // MODIFIES: goal, goals, listModel
    // EFFECTS: returns a panel that has an add goal button that adds goals to the goals
    //          as well as the listModel
    public JPanel createAddGoalButton() {
        JPanel panel = new JPanel(new FlowLayout());
        panel.setBackground(new Color(255, 204, 204));
        addGoalButton = new JButton("Add Goal");
        addGoalButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int hours = Integer.parseInt(hoursText.getText());
                goal = new Goal(typeText.getText(), dateText.getText(), hours);
                goals.addGoals(goal);
                listModel.addElement(goal.getName());

                typeText.requestFocusInWindow();
                typeText.setText("");
                dateText.requestFocusInWindow();
                dateText.setText("");
                hoursText.requestFocusInWindow();
                hoursText.setText("");
                removeGoalButton.setEnabled(true);
            }
        });
        addGoalButton.setEnabled(true);
        panel.add(addGoalButton);
        return panel;
    }

    // MODIFIES: this
    // EFFECTS: creates the left panel which shows the text fields to enter a new goal and adds it to this
    public void leftPanel() {
        JPanel panel = new JPanel();
        JLabel label = new JLabel(" ");
        panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
        panel.setBackground(new Color(255, 204, 204));
        panel.add(label);
        panel.add(createTypePane());
        panel.add(createDatePane());
        panel.add(createHoursPane());
        panel.add(createAddGoalButton());
        this.add(panel, gbc);
    }

    // MODIFIES: list, goals
    // EFFECTS: returns a jpanel that shows the goals list in a scroll pane
    public JPanel createGoalsList() {
        JPanel panel = new JPanel(new FlowLayout());
        panel.setBackground(new Color(255, 204, 204));
        for (Goal g: goals.getGoals()) {
            listModel.addElement(g.getName());
        }
        list = new JList(listModel);
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.setSelectedIndex(0);
        JScrollPane scrollPane = new JScrollPane(list);
        scrollPane.setPreferredSize(new Dimension(200,110));
        panel.add(scrollPane);
        return panel;
    }

    // MODIFIES: listModel, goals, list
    // EFFECTS: returns a panel that has a remove goal button that removes goals from the goals
    //          as well as the listModel
    public JPanel createRemoveGoalButton() {
        JPanel panel = new JPanel(new FlowLayout());
        panel.setBackground(new Color(255, 204, 204));
        removeGoalButton = new JButton("Remove Goal");
        removeGoalButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int index = list.getSelectedIndex();
                listModel.remove(index);
                goals.removeIndex(index);

                int size = listModel.getSize();

                if (size == 0) {
                    removeGoalButton.setEnabled(false);
                } else {
                    if (index == listModel.getSize()) {
                        index--;
                    }
                    list.setSelectedIndex(index);
                    list.ensureIndexIsVisible(index);
                }
            }
        });
        panel.add(removeGoalButton);
        return panel;
    }

    // MODIFIES: this
    // EFFECTS: creates panel showing the goals list in a scroll pane and adds it to this
    public void middlePanel() {
        JPanel panel = new JPanel();
        JLabel label = new JLabel("Goals ToDo");
        label.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        label.setAlignmentY(JLabel.CENTER_ALIGNMENT);
        panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
        panel.setBackground(new Color(255, 204, 204));
        panel.add(label);
        panel.add(createGoalsList());
        panel.add(createRemoveGoalButton());
        this.add(panel, gbc);
    }

    // MODIFIES: list, completedGoals
    // EFFECTS: returns a jpanel that shows the completed goals list in a scroll pane
    public JPanel createCompletedGoalsList() {
        JPanel panel = new JPanel(new FlowLayout());
        panel.setBackground(new Color(255, 204, 204));
        for (Goal g: completedGoals.getGoals()) {
            completedListModel.addElement(g.getName());
        }
        completedList = new JList(completedListModel);
        completedList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        completedList.setSelectedIndex(0);
        JScrollPane scrollPane = new JScrollPane(completedList);
        scrollPane.setPreferredSize(new Dimension(200,110));
        panel.add(scrollPane);
        return panel;
    }

    // MODIFIES: completedGoal, completedGoals, listModel, completedListModel
    // EFFECTS: returns a panel that has an add completed goal button that adds goals to the completed goals
    //          as well as remove from the listModel
    public JPanel createAddCompletedGoalButton() {
        JPanel panel = new JPanel(new FlowLayout());
        panel.setBackground(new Color(255, 204, 204));
        completeGoalButton = new JButton("Complete Goal");
        completeGoalButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int index = list.getSelectedIndex();
                completedGoal = goals.getGoals().get(index);
                completedGoals.addGoals(completedGoal);
                goals.removeIndex(index);
                completedListModel.addElement(completedGoal.getName());
                listModel.remove(index);
            }
        });
        completeGoalButton.setEnabled(true);
        panel.add(completeGoalButton);
        panel.setAlignmentX(Component.CENTER_ALIGNMENT);
        return panel;
    }

    // MODIFIES: this
    // EFFECTS: creates a panel that shows the completed goals on a scrollpane and adds it to this
    public void rightPanel() {
        JPanel panel = new JPanel();
        JLabel label = new JLabel("Completed Goals");
        label.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        label.setAlignmentY(JLabel.CENTER_ALIGNMENT);
        panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
        panel.setBackground(new Color(255, 204, 204));
        panel.add(label);
        panel.add(createCompletedGoalsList());
        panel.add(createAddCompletedGoalButton());
        this.add(panel, gbc);
    }

    // MODIFIES: this
    // EFFECTS: creates a panel that shows text "Keep up the great work!" and adds to this
    public void bottomPanel() {
        JPanel panel = new JPanel();
        panel.setBackground(new Color(204,255,229));
        JLabel label = new JLabel("Keep up the great work!");
        panel.add(label);
        this.add(panel, gbc);
    }

    // MODIFIES: this
    // EFFECTS: make a panel that displays options to view by all completed goals or by type or date
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
        chooseFilter.addItem("Filter Completed Goals by:");
        chooseFilter.addItem("All");
        chooseFilter.addItem("Type");
        chooseFilter.addItem("Date");
    }

    // MODIFIES: this, listModel
    // EFFECTS: make a button that updates list to show either all completed goals or by date or type
    public void makeViewByButton() {
        viewByButton = new JButton("View by...");
        viewByButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                completedListModel.clear();
                if (chooseFilter.getSelectedItem().toString().equals("All")) {
                    for (Goal g: completedGoals.getGoals()) {
                        completedListModel.addElement(g.getName());
                    }
                }
                if (chooseFilter.getSelectedItem().toString().equals("Type")) {
                    for (Goal g: completedGoals.goalsByType(filter.getText()).getGoals()) {
                        completedListModel.addElement(g.getName());
                    }
                }
                if (chooseFilter.getSelectedItem().toString().equals("Date")) {
                    for (Goal g: completedGoals.goalsByDate(filter.getText()).getGoals()) {
                        completedListModel.addElement(g.getName());
                    }
                    resetFilterTextField();
                }
            }
        });
    }

    // MODIFIES: this
    // EFFECTS: resets the filter text field
    public void resetFilterTextField() {
        filter.requestFocusInWindow();
        filter.setText("");
    }

    // MODIFIES: listModel
    // EFFECTS: clears old listModel and inserts updated goals list
    public void displayGoals(Goals goals) {
        listModel.clear();
        for (Goal g: goals.getGoals()) {
            listModel.addElement(g.getName());
        }
        this.goals = goals;
    }

    // MODIFIES: completedListModel
    // EFFECTS: clears old completedListModel and inserts updated completed goals list
    public void displayCompletedGoals(Goals completedGoals) {
        completedListModel.clear();
        for (Goal g: completedGoals.getGoals()) {
            completedListModel.addElement(g.getName());
        }
        this.completedGoals = completedGoals;
    }

    // MODIFIES: removeGoalButton
    // EFFECTS: sets the remove goal button to enable/disable based on if a goal is selected
    @Override
    public void valueChanged(ListSelectionEvent e) {
        if (e.getValueIsAdjusting() == false) {

            if (list.getSelectedIndex() == -1) {
                removeGoalButton.setEnabled(false);

            } else {
                removeGoalButton.setEnabled(true);
            }
        }
    }
}
