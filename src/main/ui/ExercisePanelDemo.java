//package ui;
//
//import model.Exercise;
//
//import javax.swing.*;
//import javax.swing.event.ListSelectionEvent;
//import javax.swing.event.ListSelectionListener;
//import java.awt.*;
//import java.awt.event.ActionEvent;
//
//public class ExercisePanelDemo extends JFrame implements ListSelectionListener {
//    protected JList list;
//    public DefaultListModel listModel;
//
//    private static final String addExerciseString = "Add an Exercise";
//    private static final String removeExerciseString = "Remove an Exercise";
//    private JButton addButton;
//    public JButton removeButton;
//    public JTextField exerciseName;
//    public JScrollPane listScrollPane;
//    public JPanel buttonPane;
//    public Exercise exercise;
//
//    public ExercisePanelDemo() {
//
//        listModel = new DefaultListModel();
//        listModel.addElement(exercise);
//
//        list = new JList(listModel);
//
//        listScrollPane = new JScrollPane(list);
//        initList();
//        addButton = new JButton(addExerciseString);
//        removeButton = new JButton(removeExerciseString);
//        exerciseName = new JTextField(10);
//
//        makeButtons();
//
//        buttonPane = new JPanel();
//        makePanel();
//
////        JFrame frame = new JFrame();
//        this.add(buttonPane);
//        this.setVisible(true);
//
//        add(listScrollPane, BorderLayout.CENTER);
//        add(buttonPane, BorderLayout.PAGE_END);
//
//    }
//
//    public void initList() {
//        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
//        list.setLayoutOrientation(JList.VERTICAL);
//        list.setSelectedIndex(0);
//        list.addListSelectionListener(this);
//        list.setVisibleRowCount(5);
//        listScrollPane.setPreferredSize(new Dimension(250,80));
//    }
//
//    public void makeButtons() {
//
//        AddListener addListener = new AddListener(addButton, exerciseName, list, listModel);
//        addButton.setActionCommand(addExerciseString);
//        addButton.addActionListener(addListener);
//        addButton.setEnabled(false);
//        addButton.setBackground(Color.lightGray);
//
//
//        RemoveListener removeListener = new RemoveListener(list, removeButton, listModel);
//        removeButton.setActionCommand(removeExerciseString);
//        removeButton.addActionListener(removeListener);
//
//
//        exerciseName.addActionListener(addListener);
//        exerciseName.getDocument().addDocumentListener(addListener);
//    }
//
//    public void makePanel() {
//        buttonPane.setLayout(new BoxLayout(buttonPane,
//                BoxLayout.LINE_AXIS));
//        buttonPane.add(addButton);
//        buttonPane.add(Box.createHorizontalStrut(5));
//        buttonPane.add(new JSeparator(SwingConstants.VERTICAL));
//        buttonPane.add(Box.createHorizontalStrut(5));
//        buttonPane.add(exerciseName);
//        buttonPane.add(removeButton);
//        buttonPane.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
//    }
//
//    @Override
//    public void valueChanged(ListSelectionEvent e) {
//        if (e.getValueIsAdjusting() == false) {
//
//            if (list.getSelectedIndex() == -1) {
//                //No selection, disable remove button.
//                removeButton.setEnabled(false);
//
//            } else {
//                //Selection, enable the remove button.
//                removeButton.setEnabled(true);
//            }
//        }
//    }
//
//}
