package hotel.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class UpdateCheck extends JFrame implements ActionListener {

    JTextField tfRoom, tfName, tfCheckin, tfPaid, tfPending;
    Choice cCustomer;
    JButton btnCheck, btnUpdate, btnBack;

    UpdateCheck() {
        setTitle("Update Check");
        setBounds(300, 200, 800, 400);
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);

        // Title
        JLabel lblTitle = new JLabel("Update Customer Check-in");
        lblTitle.setFont(new Font("Tahoma", Font.BOLD, 22));
        lblTitle.setBounds(250, 20, 400, 30);
        add(lblTitle);

        // Customer ID
        JLabel lblId = new JLabel("Customer ID:");
        lblId.setBounds(30, 80, 150, 25);
        add(lblId);

        cCustomer = new Choice();
        cCustomer.setBounds(180, 80, 150, 25);
        add(cCustomer);

        try {
            DBConnection c = new DBConnection();
            ResultSet rs = c.s.executeQuery("SELECT id FROM customer");
            while (rs.next()) {
                cCustomer.add(rs.getString("id"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Room Number
        JLabel lblRoom = new JLabel("Room Number:");
        lblRoom.setBounds(30, 120, 150, 25);
        add(lblRoom);

        tfRoom = new JTextField();
        tfRoom.setBounds(180, 120, 150, 25);
        add(tfRoom);

        // Name
        JLabel lblName = new JLabel("Name:");
        lblName.setBounds(30, 160, 150, 25);
        add(lblName);

        tfName = new JTextField();
        tfName.setBounds(180, 160, 150, 25);
        add(tfName);

        // Check-in Time
        JLabel lblCheckin = new JLabel("Check-in Time:");
        lblCheckin.setBounds(30, 200, 150, 25);
        add(lblCheckin);

        tfCheckin = new JTextField();
        tfCheckin.setBounds(180, 200, 150, 25);
        add(tfCheckin);

        // Amount Paid
        JLabel lblPaid = new JLabel("Amount Paid:");
        lblPaid.setBounds(30, 240, 150, 25);
        add(lblPaid);

        tfPaid = new JTextField();
        tfPaid.setBounds(180, 240, 150, 25);
        add(tfPaid);

        // Pending Amount
        JLabel lblPending = new JLabel("Pending Amount:");
        lblPending.setBounds(30, 280, 150, 25);
        add(lblPending);

        tfPending = new JTextField();
        tfPending.setBounds(180, 280, 150, 25);
        add(tfPending);

        // Buttons
        btnCheck = new JButton("Check");
        btnCheck.setBounds(30, 330, 100, 30);
        btnCheck.setBackground(Color.BLACK);
        btnCheck.setForeground(Color.WHITE);
        btnCheck.addActionListener(this);
        add(btnCheck);

        btnUpdate = new JButton("Update");
        btnUpdate.setBounds(150, 330, 100, 30);
        btnUpdate.setBackground(Color.BLACK);
        btnUpdate.setForeground(Color.WHITE);
        btnUpdate.addActionListener(this);
        add(btnUpdate);

        btnBack = new JButton("Back");
        btnBack.setBounds(270, 330, 100, 30);
        btnBack.setBackground(Color.BLACK);
        btnBack.setForeground(Color.WHITE);
        btnBack.addActionListener(this);
        add(btnBack);

        // Right side image
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/16.png"));
        Image i2 = i1.getImage().getScaledInstance(300, 300, Image.SCALE_SMOOTH);
        JLabel image = new JLabel(new ImageIcon(i2));
        image.setBounds(400, 80, 350, 250);
        add(image);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == btnCheck) {
            String id = cCustomer.getSelectedItem();
            try {
                Conn c = new Conn();
                ResultSet rs = c.s.executeQuery("SELECT * FROM customer WHERE id = '" + id + "'");
                if (rs.next()) {
                    tfRoom.setText(rs.getString("room_number"));
                    tfName.setText(rs.getString("name"));
                    tfCheckin.setText(rs.getString("checkin_time"));
                    tfPaid.setText(rs.getString("amount_paid"));
                    tfPending.setText(rs.getString("pending_amount"));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (ae.getSource() == btnUpdate) {
            String id = cCustomer.getSelectedItem();
            String room = tfRoom.getText();
            String name = tfName.getText();
            String checkin = tfCheckin.getText();
            String paid = tfPaid.getText();
            String pending = tfPending.getText();

            try {
                Conn c = new Conn();
                String query = "UPDATE customer SET room_number = '" + room + "', name = '" + name + "', checkin_time = '" + checkin + "', amount_paid = '" + paid + "', pending_amount = '" + pending + "' WHERE id = '" + id + "'";
                c.s.executeUpdate(query);
                JOptionPane.showMessageDialog(null, "Customer Updated Successfully!");
                setVisible(false);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (ae.getSource() == btnBack) {
            setVisible(false);
        }
    }

    public static void main(String[] args) {
        new UpdateCheck();
    }
}
