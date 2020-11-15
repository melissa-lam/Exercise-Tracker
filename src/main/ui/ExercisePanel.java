package ui;

import model.Exercise;
import model.ExerciseList;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
        createAddExerciseButton();

        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.gridheight = 3;
        createList();

        gbc.gridx = 2;
        gbc.gridy = 3;
        createRemoveExerciseButton();
//
//        JFrame frame = new JFrame();
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.add(this);
//        frame.setVisible(true);
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

    public void createAddExerciseButton() {
        addExerciseButton = new JButton("Add Exercise");
        addExerciseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int hours = Integer.parseInt(hoursText.getText());
                exercise = new Exercise(typeText.getText(), dateText.getText(), hours);
                exercises.addExercise(exercise);
                listModel.addElement(exercise.getName());
            }
        });
        addExerciseButton.setEnabled(true);
        this.add(addExerciseButton, gbc);
    }

    public void createList() {
        for (Exercise e: exercises.getExercises()) {
            listModel.addElement(e.getName());
        }
        list = new JList(listModel);
        JScrollPane scrollPane = new JScrollPane(list);
        scrollPane.setPreferredSize(new Dimension(250,80));

        this.add(scrollPane, gbc);

    }

    public void createRemoveExerciseButton() {
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
        this.add(removeExerciseButton, gbc);
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
