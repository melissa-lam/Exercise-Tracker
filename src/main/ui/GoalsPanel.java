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

public class GoalsPanel extends JPanel implements ListSelectionListener {
    private JLabel type;
    private JLabel date;
    private JLabel hours;
    private JTextField typeText;
    private JTextField dateText;
    private JTextField hoursText;
    private JButton addGoalButton;
    private JButton removeGoalButton;
    private JList list;
    private DefaultListModel listModel = new DefaultListModel();
    private Goal goal;
    private Goals goals = new Goals();
    private GridBagConstraints gbc = new GridBagConstraints();

    public GoalsPanel() {
        this.setLayout(new GridBagLayout());

        gbc.gridx = 0;
        gbc.gridy = 0;
        leftPanel();
//        createTypePane();
//
//        gbc.gridx = 0;
//        gbc.gridy = 1;
//        createDatePane();
//
//        gbc.gridx = 0;
//        gbc.gridy = 2;
//        createHoursPane();
//
//        gbc.gridx = 0;
//        gbc.gridy = 3;
//        createAddGoalButton();

        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.gridheight = 3;
        rightPanel();
//        createList();
//
//        gbc.gridx = 2;
//        gbc.gridy = 3;
//        createRemoveGoalButton();
    }

    public JPanel createTypePane() {
        JPanel panel = new JPanel(new FlowLayout());
        panel.setBackground(new Color(255, 204, 204));
        type = new JLabel("Type:");
        typeText = new JTextField();
        typeText.setPreferredSize(new Dimension(100,30));
        panel.add(type);
        panel.add(typeText);
        panel.setAlignmentX(Component.CENTER_ALIGNMENT);
//        this.add(panel, gbc);
//        gbc.gridx++;
//        this.add(typeText, gbc);
        return panel;
    }

    public JPanel createDatePane() {
        JPanel panel = new JPanel(new FlowLayout());
        panel.setBackground(new Color(255, 204, 204));
        date = new JLabel("Date:");
        dateText = new JTextField();
        dateText.setPreferredSize(new Dimension(100,30));
        panel.add(date);
        panel.add(dateText);
        panel.setAlignmentX(Component.CENTER_ALIGNMENT);
//        this.add(panel, gbc);
//        gbc.gridx++;
//        this.add(dateText, gbc);
        return panel;

    }

    public JPanel createHoursPane() {
        JPanel panel = new JPanel(new FlowLayout());
        panel.setBackground(new Color(255, 204, 204));
        hours = new JLabel("Hours:");
        hoursText = new JTextField();
        hoursText.setPreferredSize(new Dimension(100,30));
        panel.add(hours);
        panel.add(hoursText);
        panel.setAlignmentX(Component.CENTER_ALIGNMENT);
//        this.add(panel, gbc);
//        gbc.gridx++;
//        this.add(hoursText, gbc);
        return panel;
    }

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
            }
        });
        addGoalButton.setEnabled(true);
        panel.add(addGoalButton);
        panel.setAlignmentX(Component.CENTER_ALIGNMENT);
//        this.add(addGoalButton, gbc);
        return panel;
    }

    public void leftPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
        panel.add(createTypePane());
        panel.add(createDatePane());
        panel.add(createHoursPane());
        panel.add(createAddGoalButton());
        this.add(panel, gbc);
    }

    public JPanel createList() {
        JPanel panel = new JPanel(new FlowLayout());
        panel.setBackground(new Color(255, 204, 204));
        for (Goal g: goals.getGoals()) {
            listModel.addElement(g.getName());
        }
        list = new JList(listModel);
        JScrollPane scrollPane = new JScrollPane(list);
        scrollPane.setPreferredSize(new Dimension(250,110));
        panel.add(scrollPane);
        return panel;
    }

    public JPanel createRemoveGoalButton() {
        JPanel panel = new JPanel(new FlowLayout());
        panel.setBackground(new Color(255, 204, 204));
        removeGoalButton = new JButton("Remove Goal");
        removeGoalButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int index = list.getSelectedIndex();
                listModel.remove(index);
                int size = listModel.getSize();

                if (size == 0) { //Nobody's left, disable firing.
                    removeGoalButton.setEnabled(false);
                } else { //Select an index.
                    if (index == listModel.getSize()) {
                        //removed item in last position
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

    public void rightPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
        panel.add(createList());
        panel.add(createRemoveGoalButton());
        this.add(panel, gbc);
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        if (e.getValueIsAdjusting() == false) {

            if (list.getSelectedIndex() == -1) {
                //No selection, disable remove button.
                removeGoalButton.setEnabled(false);

            } else {
                //Selection, enable the remove button.
                removeGoalButton.setEnabled(true);
            }
        }
    }
}
