//package ui;
//
//import javax.swing.*;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//
//public class RemoveListener implements ActionListener {
//    private JList list;
//    private JButton removeButton;
//    private DefaultListModel listModel;
//
//    public RemoveListener(JList list, JButton removeButton, DefaultListModel listModel) {
//        this.list = list;
//        this.removeButton = removeButton;
//        this.listModel = listModel;
//    }
//    @Override
//    public void actionPerformed(ActionEvent e) {
//        int index = list.getSelectedIndex();
//        listModel.remove(index);
//
//        int size = listModel.getSize();
//
//        if (size == 0) { //Nobody's left, disable firing.
//            removeButton.setEnabled(false);
//        } else { //Select an index.
//            if (index == listModel.getSize()) {
//                //removed item in last position
//                index--;
//            }
//
//            list.setSelectedIndex(index);
//            list.ensureIndexIsVisible(index);
//        }
//    }
//}
