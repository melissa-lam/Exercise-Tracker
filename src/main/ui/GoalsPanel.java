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
        createTypePane();

        gbc.gridx = 0;
        gbc.gridy = 1;
        createDatePane();

        gbc.gridx = 0;
        gbc.gridy = 2;
        createHoursPane();

        gbc.gridx = 0;
        gbc.gridy = 3;
        createAddGoalButton();

        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.gridheight = 3;
        createList();

        gbc.gridx = 2;
        gbc.gridy = 3;
        createRemoveGoalButton();
    }

    public void createTypePane() {
        JPanel panel = new JPanel();
        panel.setBackground(new Color(0,0,0));
        type = new JLabel("Type:");
        typeText = new JTextField();
        typeText.setPreferredSize(new Dimension(100,30));
        this.add(type, gbc);
        gbc.gridx++;
        this.add(typeText, gbc);
    }

    public void createDatePane() {
        JPanel panel = new JPanel();
        panel.setBackground(new Color(0,0,0));
        date = new JLabel("Date:");
        dateText = new JTextField();
        dateText.setPreferredSize(new Dimension(100,30));
        this.add(date, gbc);
        gbc.gridx++;
        this.add(dateText, gbc);

    }

    public void createHoursPane() {
        JPanel panel = new JPanel();
        panel.setBackground(new Color(0,0,0));
        hours = new JLabel("Hours:");
        hoursText = new JTextField();
        hoursText.setPreferredSize(new Dimension(100,30));
        this.add(hours, gbc);
        gbc.gridx++;
        this.add(hoursText, gbc);
    }

    public void createAddGoalButton() {
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
        this.add(addGoalButton, gbc);
    }

    public void createList() {
        for (Goal g: goals.getGoals()) {
            listModel.addElement(g.getName());
        }
        list = new JList(listModel);
        JScrollPane scrollPane = new JScrollPane(list);
        scrollPane.setPreferredSize(new Dimension(250,80));

        this.add(scrollPane, gbc);
    }

    public void createRemoveGoalButton() {
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
        this.add(removeGoalButton, gbc);
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
