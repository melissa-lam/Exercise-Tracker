//package ui;
//
//import javax.swing.*;
//import javax.swing.event.DocumentEvent;
//import javax.swing.event.DocumentListener;
//import java.awt.*;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//
//public class AddListener implements ActionListener, DocumentListener {
//    private JButton button;
//    private JTextField exerciseName;
//    private JList list;
//    private DefaultListModel listModel;
//    private boolean alreadyEnabled = false;
//
//    public AddListener(JButton button, JTextField exerciseName, JList list, DefaultListModel listModel) {
//        this.button = button;
//        this.exerciseName = exerciseName;
//        this.list = list;
//        this.listModel = listModel;
//    }
//
//    @Override
//    public void actionPerformed(ActionEvent e) {
//        String name = exerciseName.getText();
//
//        if (name.equals("") || alreadyInList(name)) {
//            Toolkit.getDefaultToolkit().beep();
//            exerciseName.requestFocusInWindow();
//            exerciseName.selectAll();
//            return;
//        }
//
//        int index = list.getSelectedIndex(); //get selected index
//        if (index == -1) { //no selection, so insert at beginning
//            index = 0;
//        } else {           //add after the selected item
//            index++;
//        }
//
//        listModel.addElement(exerciseName.getText());
//
//        exerciseName.requestFocusInWindow();
//        exerciseName.setText("");
//
//        list.setSelectedIndex(index);
//        list.ensureIndexIsVisible(index);
//    }
//
//    protected boolean alreadyInList(String name) {
//        return listModel.contains(name);
//    }
//
//    @Override
//    public void insertUpdate(DocumentEvent e) {
//        if (!alreadyEnabled) {
//            button.setEnabled(true);
//        }
//    }
//
//    @Override
//    public void removeUpdate(DocumentEvent e) {
//        handleEmptyTextField(e);
//    }
//
//    @Override
//    public void changedUpdate(DocumentEvent e) {
//        if (!handleEmptyTextField(e)) {
//            if (!alreadyEnabled) {
//                button.setEnabled(true);
//            }
//        }
//    }
//
//    private boolean handleEmptyTextField(DocumentEvent e) {
//        if (e.getDocument().getLength() <= 0) {
//            button.setEnabled(false);
//            alreadyEnabled = false;
//            return true;
//        }
//        return false;
//    }
//}
