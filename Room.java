package hotel.management.system;

import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;

public class Room extends JFrame implements ActionListener {
    JButton back;

    Room() {
        setLayout(null);
        setBounds(350, 200, 800, 570);
        getContentPane().setBackground(Color.WHITE);

        // Title
        JLabel heading = new JLabel("ROOMS");
        heading.setFont(new Font("Tahoma", Font.BOLD, 24));
        heading.setBounds(300, 10, 200, 30);
        add(heading);

        // Table Columns and Data
        String[] columns = {"Room Number", "Availability", "Status", "Price", "Bed Type"};
        String[][] data = {
                {"101", "Occupied", "Cleaned", "2100", "Double Bed"},
                {"102", "Occupied", "Cleaned", "3600", "Single Bed"},
                {"103", "Available", "Cleaned", "5600", "Single Bed"},
                {"104", "Available", "Cleaned", "2200", "Double Bed"}
        };

        JTable table = new JTable(data, columns);
        table.setBackground(Color.WHITE);
        table.setForeground(Color.BLACK);

        JScrollPane sp = new JScrollPane(table);
        sp.setBounds(10, 50, 760, 200);
        add(sp);

        // Room Image
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/18.jpg")); // replace with your image path
        Image i2 = i1.getImage().getScaledInstance(350, 250, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(220, 270, 350, 250);
        add(image);

        // Back Button
        back = new JButton("Back");
        back.setBounds(350, 530, 100, 30);
        back.setBackground(Color.BLACK);
        back.setForeground(Color.WHITE);
        back.addActionListener(this);
        add(back);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == back) {
            setVisible(false);
        }
    }

    public static void main(String[] args) {
        new Room();
    }
}