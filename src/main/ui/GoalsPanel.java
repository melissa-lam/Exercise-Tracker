package ui;

import model.Goal;
import model.Goals;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static java.awt.GridBagConstraints.HORIZONTAL;

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
    private DefaultListModel listModel = new DefaultListModel();
    private Goal goal;
    private Goal completedGoal;
    private Goals goals = new Goals();
    private Goals completedGoals = new Goals();
    private GridBagConstraints gbc = new GridBagConstraints();

    public GoalsPanel() {
        this.setLayout(new GridBagLayout());
        this.setBackground(new Color(204,229,255));

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridheight = 2;
        gbc.gridwidth = 10;
        gbc.fill = HORIZONTAL;
        topPanel();

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

    }

    public void topPanel() {
        JPanel panel = new JPanel();
        panel.setBackground(new Color(204,255,229));
        JLabel label = new JLabel("Make your goals here!");
        panel.add(label);
        this.add(panel, gbc);
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
                removeGoalButton.setEnabled(true);
            }
        });
        addGoalButton.setEnabled(true);
        panel.add(addGoalButton);
        panel.setAlignmentX(Component.CENTER_ALIGNMENT);
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

    public JPanel createGoalsList() {
        JPanel panel = new JPanel(new FlowLayout());
        panel.setBackground(new Color(255, 204, 204));
        for (Goal g: goals.getGoals()) {
            listModel.addElement(g.getName());
        }
        list = new JList(listModel);
        JScrollPane scrollPane = new JScrollPane(list);
        scrollPane.setPreferredSize(new Dimension(200,110));
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

    public void middlePanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
        panel.add(createGoalsList());
        panel.add(createRemoveGoalButton());
        this.add(panel, gbc);
    }

    public JPanel createCompletedGoalsList() {
        JPanel panel = new JPanel(new FlowLayout());
        panel.setBackground(new Color(255, 204, 204));
        for (Goal g: completedGoals.getGoals()) {
            listModel.addElement(g.getName());
        }
        list = new JList(listModel);
        JScrollPane scrollPane = new JScrollPane(list);
        scrollPane.setPreferredSize(new Dimension(200,110));
        panel.add(scrollPane);
        return panel;
    }

    public JPanel createAddCompletedGoalButton() {
        JPanel panel = new JPanel(new FlowLayout());
        panel.setBackground(new Color(255, 204, 204));
        completeGoalButton = new JButton("Complete Goal");
        completeGoalButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int hours = Integer.parseInt(hoursText.getText());
                completedGoal = new Goal(typeText.getText(), dateText.getText(), hours);
                completedGoals.addGoals(completedGoal);
                listModel.addElement(completedGoal.getName());
//                removeGoalButton.setEnabled(true);
            }
        });
        completeGoalButton.setEnabled(true);
        panel.add(completeGoalButton);
        panel.setAlignmentX(Component.CENTER_ALIGNMENT);
        return panel;
    }

    public void rightPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
        panel.add(createCompletedGoalsList());
        panel.add(createAddCompletedGoalButton());
        this.add(panel, gbc);
    }


    public void bottomPanel() {
        JPanel panel = new JPanel();
        panel.setBackground(new Color(204,255,229));
        JLabel label = new JLabel("Keep up the great work!");
        panel.add(label);
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
