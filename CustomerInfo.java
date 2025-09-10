package hotel.management.system;

import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class CustomerInfo extends JFrame implements ActionListener {
    
    JTable table;
    JButton back;

    CustomerInfo() {
        setTitle("Customer Information");
        setLayout(null);
        getContentPane().setBackground(Color.WHITE);
        setBounds(300, 200, 900, 600);

        // Title
        JLabel l1 = new JLabel("CUSTOMER INFO");
        l1.setFont(new Font("Tahoma", Font.BOLD, 24));
        l1.setBounds(350, 20, 300, 30);
        add(l1);

        // Table
        table = new JTable();
        JScrollPane sp = new JScrollPane(table);
        sp.setBounds(20, 80, 850, 400);
        add(sp);

        // Load Data from Database
        try {
            DBConnection c = new DBConnection();
            String query = "SELECT * FROM customer";  // তোমার AddCustomer এ যে টেবিলে insert হচ্ছে
            ResultSet rs = c.s.executeQuery(query);

            // ResultSet থেকে JTable এ ডেটা সেট করা
            ResultSetMetaData rsmd = rs.getMetaData();
            int columnCount = rsmd.getColumnCount();

            // Column names
            String[] columns = new String[columnCount];
            for (int i = 1; i <= columnCount; i++) {
                columns[i - 1] = rsmd.getColumnName(i);
            }

            // Row data
            DefaultTableModel model = new DefaultTableModel();
            model.setColumnIdentifiers(columns);

            while (rs.next()) {
                Object[] row = new Object[columnCount];
                for (int i = 1; i <= columnCount; i++) {
                    row[i - 1] = rs.getObject(i);
                }
                model.addRow(row);
            }

            table.setModel(model);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Database Error: " + e.getMessage());
        }

        // Back Button
        back = new JButton("Back");
        back.setBounds(380, 500, 120, 30);
        back.setBackground(Color.BLACK);
        back.setForeground(Color.WHITE);
        back.addActionListener(this);
        add(back);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == back) {
            setVisible(false);
            new Reception().setVisible(true);
        }
    }

    public static void main(String[] args) {
        new CustomerInfo();
    }
}