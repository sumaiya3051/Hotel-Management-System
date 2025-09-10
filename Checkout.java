package hotel.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class Checkout extends JFrame implements ActionListener {

    JTextField tfCustomerId, tfRoomNumber, tfCheckinTime, tfCheckoutTime;
    JButton btnCheckout, btnBack;

    Checkout() {
        setTitle("Checkout");
        setBounds(350, 200, 900, 500);
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);

        // Labels
        JLabel l1 = new JLabel("Customer ID");
        l1.setBounds(30, 50, 150, 30);
        add(l1);

        JLabel l2 = new JLabel("Room Number");
        l2.setBounds(30, 100, 150, 30);
        add(l2);

        JLabel l3 = new JLabel("Checkin Time");
        l3.setBounds(30, 150, 150, 30);
        add(l3);

        JLabel l4 = new JLabel("Checkout Time");
        l4.setBounds(30, 200, 150, 30);
        add(l4);

        // TextFields
        tfCustomerId = new JTextField();
        tfCustomerId.setBounds(200, 50, 150, 30);
        add(tfCustomerId);

        tfRoomNumber = new JTextField();
        tfRoomNumber.setBounds(200, 100, 150, 30);
        add(tfRoomNumber);

        tfCheckinTime = new JTextField();
        tfCheckinTime.setBounds(200, 150, 150, 30);
        add(tfCheckinTime);

        tfCheckoutTime = new JTextField();
        tfCheckoutTime.setBounds(200, 200, 150, 30);
        add(tfCheckoutTime);

        // Buttons
        btnCheckout = new JButton("Checkout");
        btnCheckout.setBounds(30, 270, 150, 30);
        btnCheckout.setBackground(Color.BLACK);
        btnCheckout.setForeground(Color.WHITE);
        btnCheckout.addActionListener(this);
        add(btnCheckout);

        btnBack = new JButton("Back");
        btnBack.setBounds(200, 270, 150, 30);
        btnBack.setBackground(Color.BLACK);
        btnBack.setForeground(Color.WHITE);
        btnBack.addActionListener(this);
        add(btnBack);

        // Right side image
        try {
            ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/20.jpeg"));
            Image i2 = i1.getImage().getScaledInstance(400, 300, Image.SCALE_DEFAULT);
            ImageIcon i3 = new ImageIcon(i2);
            JLabel image = new JLabel(i3);
            image.setBounds(400, 50, 400, 300);
            add(image);
        } catch (Exception e) {
            System.out.println("Image not found.");
        }

        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == btnCheckout) {
            String customerId = tfCustomerId.getText();
            String roomNumber = tfRoomNumber.getText();
            String checkinTime = tfCheckinTime.getText();
            String checkoutTime = tfCheckoutTime.getText();

            if (customerId.isEmpty() || roomNumber.isEmpty() || checkinTime.isEmpty() || checkoutTime.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Please fill all fields!");
                return;
            }

            try {
                DBConnection c = new DBConnection();

                // Example: Update checkout time in database (adjust according to your table structure)
                String query = "UPDATE customer SET checkout_time = '"+checkoutTime+"' WHERE id = '"+customerId+"'";
                c.s.executeUpdate(query);

                JOptionPane.showMessageDialog(null, "Checkout Successful!");
                setVisible(false);
            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Database Error: " + e.getMessage());
            }
        } else if (ae.getSource() == btnBack) {
            setVisible(false);
        }
    }

    public static void main(String[] args) {
        new Checkout();
    }
}
