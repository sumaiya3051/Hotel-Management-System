package hotel.management.system;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;

public class Department extends JFrame implements ActionListener {

    JTable table;
    JButton back;

    Department() {
        setTitle("Department Information");
        setLayout(null);
        getContentPane().setBackground(Color.WHITE);
        setBounds(400, 200, 600, 400);

        // Table columns
        String[] columns = {"Department", "Budget"};

        // Table data
        String[][] data = {
            {"Front Office", "500000"},
            {"Housekeeping", "40000"},
            {"Food and Beverage", "23000"},
            {"Kitchen or Food Production", "540000"},
            {"Security", "320000"}
        };

        // JTable setup
        table = new JTable();
        DefaultTableModel model = new DefaultTableModel(data, columns);
        table.setModel(model);
        table.setFont(new Font("Tahoma", Font.PLAIN, 14));
        table.setRowHeight(25);

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(50, 30, 500, 250);
        add(scrollPane);

        // Back button
        back = new JButton("Back");
        back.setBounds(230, 300, 120, 30);
        back.setBackground(Color.BLACK);
        back.setForeground(Color.WHITE);
        back.addActionListener(this);
        add(back);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == back) {
            setVisible(false);
            new Reception(); // go back to Reception menu
        }
    }

    public static void main(String[] args) {
        new Department();
    }
}