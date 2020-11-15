package ui;

import model.Exercise;
import model.ExerciseList;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static java.awt.GridBagConstraints.*;

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
    private DefaultListModel listModel = new DefaultListModel();
    private Exercise exercise;
    private ExerciseList exercises = new ExerciseList();
    private GridBagConstraints gbc = new GridBagConstraints();

    public ExercisePanel() {
        this.setLayout(new GridBagLayout());

//        gbc.gridx = 0;
//        gbc.gridy = 0;
//        gbc.gridheight = 2;
//        gbc.gridwidth = 10;
//        gbc.fill = HORIZONTAL;
//        topPanel();

        gbc.gridx = 0;
        gbc.gridy = 2;
//        gbc.weightx = 0.5;
//        gbc.anchor = LINE_START;
        leftPanel();
//        createTypePane();
//
//        gbc.gridx = 0;
//        gbc.gridy = 1;
//        gbc.weightx = 0.5;
////        gbc.anchor = LINE_START;
//        createDatePane();
//
//        gbc.gridx = 0;
//        gbc.gridy = 2;
//        gbc.weightx = 0.5;
////        gbc.anchor = LINE_START;
//        createHoursPane();
//
//        gbc.gridx = 0;
//        gbc.gridy = 3;
//        gbc.weightx = 0.5;
////        gbc.anchor = LINE_START;
//        createAddExerciseButton();

//        gbc.gridx = 1;
//        add(Box.createHorizontalStrut(5));
//        add(new JSeparator(SwingConstants.VERTICAL));
//        add(Box.createHorizontalStrut(5));

        gbc.gridx = 4;
        gbc.gridy = 2;
//        gbc.gridheight = 3;
//        gbc.weightx = 0.5;
//        gbc.anchor = LINE_END;
        rightPanel();
//        createList();

//        gbc.gridx = 2;
//        gbc.gridy = 3;
//        gbc.weightx = 0.5;
////        gbc.anchor = LINE_END;
//        createRemoveExerciseButton();
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
        JLabel label = new JLabel("Track your exercises here!");
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
//        this.add(panel, gbc);
//        gbc.gridx++;
//        this.add(typeText, gbc);
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
//        this.add(panel, gbc);
//        gbc.gridx++;
//        this.add(dateText, gbc);

    }

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
//        this.add(panel, gbc);
//        gbc.gridx++;
//        this.add(hoursText, gbc);

    }

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
            }
        });
        addExerciseButton.setEnabled(true);
        panel.add(addExerciseButton);
        return panel;
//        this.add(addExerciseButton, gbc);
    }

    public void leftPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
        panel.add(createTypePane());
        panel.add(createDatePane());
        panel.add(createHoursPane());
        panel.add(createAddExerciseButton());
        this.add(panel, gbc);
    }

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
//        this.add(scrollPane, gbc);

    }

    public JPanel createRemoveExerciseButton() {
        JPanel panel = new JPanel();
        panel.setBackground(new Color(255, 204, 204));
        removeExerciseButton = new JButton("Remove Exercise");
        removeExerciseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int index = list.getSelectedIndex();
                listModel.remove(index);
                int size = listModel.getSize();

                if (size == 0) { //Nobody's left, disable firing.
                    removeExerciseButton.setEnabled(false);
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
        removeExerciseButton.setEnabled(true);
        panel.add(removeExerciseButton);
        return panel;
//        this.add(removeExerciseButton, gbc);
    }

    public void rightPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
        panel.add(createList());
        panel.add(createRemoveExerciseButton());
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
                removeExerciseButton.setEnabled(false);

            } else {
                //Selection, enable the remove button.
                removeExerciseButton.setEnabled(true);
            }
        }
    }
}
