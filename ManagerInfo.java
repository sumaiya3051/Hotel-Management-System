package hotel.management.system;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class ManagerInfo extends JFrame implements ActionListener {

    JTable table;
    JButton b1;

    ManagerInfo() {
        setTitle("Manager Information");
        setBounds(350, 200, 900, 600);
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);

        // Labels
        JLabel l1 = new JLabel("Name");
        l1.setBounds(40, 10, 100, 20);
        add(l1);

        JLabel l2 = new JLabel("Age");
        l2.setBounds(160, 10, 100, 20);
        add(l2);

        JLabel l3 = new JLabel("Gender");
        l3.setBounds(280, 10, 100, 20);
        add(l3);

        JLabel l4 = new JLabel("Job");
        l4.setBounds(400, 10, 100, 20);
        add(l4);

        JLabel l5 = new JLabel("Salary");
        l5.setBounds(520, 10, 100, 20);
        add(l5);

        JLabel l6 = new JLabel("Email");
        l6.setBounds(640, 10, 100, 20);
        add(l6);

        JLabel l7 = new JLabel("Phone");
        l7.setBounds(760, 10, 100, 20);
        add(l7);

        // Table
        table = new JTable();
        JScrollPane sp = new JScrollPane(table);
        sp.setBounds(20, 40, 850, 400);
        add(sp);

        // Back Button
        b1 = new JButton("Back");
        b1.setBounds(380, 500, 120, 30);
        b1.setBackground(Color.BLACK);
        b1.setForeground(Color.WHITE);
        b1.addActionListener(this);
        add(b1);

        // Load data from database
        loadManagerData();

        setVisible(true);
    }

    private void loadManagerData() {
        try {
            DBConnection c = new DBConnection();
            String query = "SELECT * FROM manager";
            ResultSet rs = c.s.executeQuery(query);

            // Table model
            DefaultTableModel model = new DefaultTableModel();
            model.addColumn("Name");
            model.addColumn("Age");
            model.addColumn("Gender");
            model.addColumn("Job");
            model.addColumn("Salary");
            model.addColumn("Email");
            model.addColumn("Phone");

            while (rs.next()) {
                model.addRow(new Object[]{
                        rs.getString("name"),
                        rs.getInt("age"),
                        rs.getString("gender"),
                        rs.getString("job"),
                        rs.getDouble("salary"),
                        rs.getString("email"),
                        rs.getString("phone")
                });
            }
            table.setModel(model);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == b1) {
            setVisible(false);
            new Reception();
        }
    }

    public static void main(String[] args) {
        new ManagerInfo();
    }
}
