package hotel.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class UpdateRoom extends JFrame implements ActionListener {

    Choice ccustomer;
    JTextField tfroom;
    JComboBox<String> cbavailability, cbclean;
    JButton check, update, back;

    UpdateRoom() {
        setTitle("Update Room");
        setLayout(null);
        getContentPane().setBackground(Color.WHITE);
        setBounds(300, 200, 900, 500);

        // Title
        JLabel heading = new JLabel("UPDATE ROOM STATUS");
        heading.setFont(new Font("Tahoma", Font.BOLD, 22));
        heading.setBounds(250, 20, 400, 30);
        add(heading);

        // Customer ID
        JLabel lblid = new JLabel("Customer ID:");
        lblid.setBounds(30, 80, 120, 30);
        add(lblid);

        ccustomer = new Choice();
        try {
            DBConnection c = new DBConnection();
            ResultSet rs = c.s.executeQuery("SELECT id FROM customer");
            while (rs.next()) {
                ccustomer.add(rs.getString("id"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        ccustomer.setBounds(200, 80, 150, 30);
        add(ccustomer);

        // Room Number
        JLabel lblroom = new JLabel("Room Number:");
        lblroom.setBounds(30, 130, 120, 30);
        add(lblroom);

        tfroom = new JTextField();
        tfroom.setBounds(200, 130, 150, 30);
        add(tfroom);

        // Availability
        JLabel lblavail = new JLabel("Availability:");
        lblavail.setBounds(30, 180, 120, 30);
        add(lblavail);

        cbavailability = new JComboBox<>(new String[]{"Available", "Occupied"});
        cbavailability.setBounds(200, 180, 150, 30);
        add(cbavailability);

        // Cleaning Status
        JLabel lblclean = new JLabel("Cleaning Status:");
        lblclean.setBounds(30, 230, 120, 30);
        add(lblclean);

        cbclean = new JComboBox<>(new String[]{"Cleaned", "Dirty"});
        cbclean.setBounds(200, 230, 150, 30);
        add(cbclean);

        // Buttons
        check = new JButton("Check");
        check.setBounds(30, 300, 100, 30);
        check.setBackground(Color.BLACK);
        check.setForeground(Color.WHITE);
        check.addActionListener(this);
        add(check);

        update = new JButton("Update");
        update.setBounds(150, 300, 100, 30);
        update.setBackground(Color.BLACK);
        update.setForeground(Color.WHITE);
        update.addActionListener(this);
        add(update);

        back = new JButton("Back");
        back.setBounds(270, 300, 100, 30);
        back.setBackground(Color.BLACK);
        back.setForeground(Color.WHITE);
        back.addActionListener(this);
        add(back);

        // Image
        try {
            ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/10.jpg"));
            Image i2 = i1.getImage().getScaledInstance(500, 300, Image.SCALE_DEFAULT);
            JLabel image = new JLabel(new ImageIcon(i2));
            image.setBounds(400, 100, 450, 300);
            add(image);
        } catch (Exception e) {
            System.out.println("Image not found!");
        }

        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == check) {
            // Fetch room number of selected customer
            try {
                String id = ccustomer.getSelectedItem();
                DBConnection c = new DBConnection();
                String query = "SELECT room_number FROM customer WHERE id='" + id + "'";
                ResultSet rs = c.s.executeQuery(query);
                if (rs.next()) {
                    tfroom.setText(rs.getString("room_number"));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        } else if (ae.getSource() == update) {
            String room = tfroom.getText();
            String availability = (String) cbavailability.getSelectedItem();
            String clean = (String) cbclean.getSelectedItem();

            try {
                DBConnection c = new DBConnection();
                String query = "UPDATE room SET availability='" + availability + "', cleaning_status='" + clean + "' WHERE room_number='" + room + "'";
                c.s.executeUpdate(query);

                JOptionPane.showMessageDialog(null, "Room Updated Successfully!");
                setVisible(false);
            } catch (Exception e) {
                e.printStackTrace();
            }

        } else if (ae.getSource() == back) {
            setVisible(false);
        }
    }

    public static void main(String[] args) {
        new UpdateRoom();
    }
}
