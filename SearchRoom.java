package hotel.management.system;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class SearchRoom extends JFrame implements ActionListener {

    JTable table;
    JButton back, searchBtn;
    JComboBox<String> bedTypeChoice;
    JCheckBox availableCheck;

    public SearchRoom() {
        setLayout(null);
        setBounds(300, 200, 900, 600);
        getContentPane().setBackground(Color.WHITE);

        // Title
        JLabel heading = new JLabel("SEARCH ROOM");
        heading.setFont(new Font("Tahoma", Font.BOLD, 24));
        heading.setBounds(350, 20, 300, 30);
        add(heading);

        // Bed Type filter
        JLabel lblBed = new JLabel("Bed Type:");
        lblBed.setBounds(50, 80, 100, 30);
        add(lblBed);

        bedTypeChoice = new JComboBox<>(new String[]{"Single Bed", "Double Bed"});
        bedTypeChoice.setBounds(150, 80, 150, 30);
        add(bedTypeChoice);

        // Availability filter
        availableCheck = new JCheckBox("Only Available");
        availableCheck.setBounds(350, 80, 150, 30);
        add(availableCheck);

        // Search Button
        searchBtn = new JButton("Search");
        searchBtn.setBounds(550, 80, 100, 30);
        searchBtn.setBackground(Color.BLACK);
        searchBtn.setForeground(Color.WHITE);
        searchBtn.addActionListener(this);
        add(searchBtn);

        // Back Button
        back = new JButton("Back");
        back.setBounds(700, 80, 100, 30);
        back.setBackground(Color.BLACK);
        back.setForeground(Color.WHITE);
        back.addActionListener(this);
        add(back);

        // Table
        table = new JTable();
        JScrollPane sp = new JScrollPane(table);
        sp.setBounds(50, 150, 800, 350);
        add(sp);

        // Load all rooms initially
        loadRoomData(null, false);

        setVisible(true);
    }

    // Method to load room data from database
    private void loadRoomData(String bedType, boolean onlyAvailable) {
        try {
            DBConnection c = new DBConnection(); // Database connection

            String query = "SELECT * FROM room"; // Default: all rooms
            if (bedType != null && onlyAvailable) {
                query = "SELECT * FROM room WHERE bed_type='" + bedType + "' AND availability='Available'";
            } else if (bedType != null) {
                query = "SELECT * FROM room WHERE bed_type='" + bedType + "'";
            } else if (onlyAvailable) {
                query = "SELECT * FROM room WHERE availability='Available'";
            }

            ResultSet rs = c.s.executeQuery(query);

            DefaultTableModel model = new DefaultTableModel();
            model.addColumn("Room Number");
            model.addColumn("Availability");
            model.addColumn("Status");
            model.addColumn("Price");
            model.addColumn("Bed Type");

            while (rs.next()) {
                model.addRow(new Object[]{
                        rs.getString("room_number"),
                        rs.getString("availability"),
                        rs.getString("status"),
                        rs.getString("price"),
                        rs.getString("bed_type")
                });
            }

            table.setModel(model);

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Database Error: " + e.getMessage());
        }
    }

    // Action listener for buttons
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == searchBtn) {
            String bedType = (String) bedTypeChoice.getSelectedItem();
            boolean onlyAvailable = availableCheck.isSelected();
            loadRoomData(bedType, onlyAvailable);
        } else if (ae.getSource() == back) {
            setVisible(false);
        }
    }

    // Main method for testing independently
    public static void main(String[] args) {
        new SearchRoom();
    }
}
