package ite.kubak.graphic;

import ite.kubak.communication.Controller;
import ite.kubak.communication.Listener;
import ite.kubak.model.Hospitality.Element;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class Frame {

    private JPanel panel1;
    private JButton OKButton;
    private JTextField textField1;
    private JList<String> list;
    private JScrollPane scroll;
    private JList<Integer> list1;
    private JList<String> list2;
    private JPanel chart;
    private JScrollPane scroll1;
    private JScrollPane scroll2;
    private DefaultListModel<String> listModel;
    private DefaultListModel<Integer> listModel1;
    private DefaultListModel<String> listModel2;
    private Listener listener;

    public Frame() {
        OKButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(listener!=null){
                    String query = textField1.getText();
                    List<String> names = listener.get_benefits(query);
                    updateList(names);
                }
            }
        });

        listModel = new DefaultListModel<>();
        list.setModel(listModel);
        listModel1 = new DefaultListModel<>();
        list1.setModel(listModel1);
        listModel2 = new DefaultListModel<>();
        list2.setModel(listModel2);


        list.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                String name = list.getSelectedValue();
                if(!e.getValueIsAdjusting() && listener!=null && name!=null){
                    List<Integer> years = listener.get_index(name);
                    updateList1(years);
                }
            }
        });
        list1.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                Integer year = list1.getSelectedValue();
                if(!e.getValueIsAdjusting() && listener!=null && year!=null){
                    List<String> tables = listener.get_tables(year);
                    updateList2(tables);
                }
            }
        });
        list2.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                Integer year = list1.getSelectedValue();
                String table = list2.getSelectedValue();
                if(!e.getValueIsAdjusting() && listener!=null && year!=null && table!=null){
                    List<Element> elements = listener.get_chart(year,table);
                    System.out.println("chuj");
                    updateChart(elements,table,year);
                }
            }
        });
    }

    public void setListener(Listener listener){
        this.listener = listener;
    }

    public void updateList(List<String> names){
        list2.clearSelection();
        list1.clearSelection();
        list.clearSelection();
        listModel2.clear();
        listModel1.clear();
        listModel.clear();
        chart.removeAll();
        chart.repaint();
        for(String name : names) listModel.addElement(name);
    }

    public void updateList1(List<Integer> years){
        list2.clearSelection();
        list1.clearSelection();
        listModel2.clear();
        listModel1.clear();
        chart.removeAll();
        chart.repaint();
        for(Integer year : years) listModel1.addElement(year);
    }

    public void updateList2(List<String> tables){
        list2.clearSelection();
        listModel2.clear();
        chart.removeAll();
        chart.repaint();
        for(String table : tables) listModel2.addElement(table);
    }

    public void updateChart(List<Element> elements, String table, int year){
        Chart chart_panel = new Chart(elements,table,year);
        chart.removeAll();
        chart.setLayout(new BorderLayout());
        chart.add(chart_panel);
        chart.revalidate();
        chart.repaint();
    }

    public static void run(){
        JFrame frame = new JFrame("Frame");
        Frame myFrame = new Frame();
        frame.setContentPane(myFrame.panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        Listener controller = new Controller();
        myFrame.setListener(controller);
    }
}
