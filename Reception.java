package hotel.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Reception extends JFrame implements ActionListener {

    JButton newCustomerForm, rooms, department, allEmployees, customerInfo,
            managerInfo, checkout, updateStatus, updateRoomStatus,
            pickupService, searchRoom, checkStatus, logout;

    Reception() {
        // Frame setup
        setTitle("Hotel Reception");
        setLayout(null);
        getContentPane().setBackground(Color.WHITE);
        setBounds(350, 200, 900, 600);

        // Title
        JLabel title = new JLabel("HOTEL RECEPTION");
        title.setFont(new Font("Tahoma", Font.BOLD, 36));
        title.setForeground(new Color(34, 139, 34));
        title.setBounds(280, 30, 600, 50);
        add(title);

        // Buttons
        newCustomerForm = createButton("New Customer Form", 30, 50);
        rooms = createButton("Rooms", 30, 90);
        department = createButton("Department", 30, 130);
        allEmployees = createButton("All Employees", 30, 170);
        customerInfo = createButton("Customer Info", 30, 210);
        managerInfo = createButton("Manager Info", 30, 250);
        checkout = createButton("Checkout", 30, 290);
        updateStatus = createButton("Update Status", 30, 330);
        updateRoomStatus = createButton("Update Room Status", 30, 370);
        pickupService = createButton("Pickup Service", 30, 410);
        searchRoom = createButton("Search Room", 30, 450);
        checkStatus = createButton("Check Status", 30, 490);
        logout = createButton("Logout", 30, 530);

        // Add buttons to frame
        addButtons(newCustomerForm, rooms, department, allEmployees, customerInfo,
                managerInfo, checkout, updateStatus, updateRoomStatus,
                pickupService, searchRoom, checkStatus, logout);

        // Background image
        try {
            ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/39.jpg"));
            Image i2 = i1.getImage().getScaledInstance(600, 400, Image.SCALE_DEFAULT);
            ImageIcon i3 = new ImageIcon(i2);
            JLabel image = new JLabel(i3);
            image.setBounds(280, 100, 600, 400);
            add(image);
        } catch (Exception e) {
            System.out.println("Background image not found.");
        }

        setVisible(true);
    }

    // Helper method to create buttons
    private JButton createButton(String text, int x, int y) {
        JButton btn = new JButton(text);
        btn.setBounds(x, y, 200, 30);
        btn.setBackground(Color.BLACK);
        btn.setForeground(Color.WHITE);
        btn.addActionListener(this);
        return btn;
    }

    // Helper method to add multiple buttons at once
    private void addButtons(JButton... buttons) {
        for (JButton btn : buttons) {
            add(btn);
        }
    }

    // Action listener
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == newCustomerForm) {
            new AddCustomer().setVisible(true);
        } else if (ae.getSource() == rooms) {
            new Room().setVisible(true);
        } else if (ae.getSource() == department) {
            new Department().setVisible(true);
        } else if (ae.getSource() == allEmployees) {
            new EmployeeInfo().setVisible(true);
        } else if (ae.getSource() == customerInfo) {
            new CustomerInfo().setVisible(true);
        } else if (ae.getSource() == managerInfo) {
            new ManagerInfo().setVisible(true);
        } else if (ae.getSource() == checkout) {
            new Checkout().setVisible(true);
        } else if (ae.getSource() == updateStatus) {
            new UpdateCheck().setVisible(true);
        } else if (ae.getSource() == updateRoomStatus) {
            new UpdateRoom().setVisible(true);
        } else if (ae.getSource() == pickupService) {
            new PickupService().setVisible(true);
        } else if (ae.getSource() == searchRoom) {
            new SearchRoom().setVisible(true);
        } else if (ae.getSource() == checkStatus) {
            new UpdateCheck().setVisible(true);
        } else if (ae.getSource() == logout) {
            setVisible(false);
        } else {
            JOptionPane.showMessageDialog(null, "Feature under development");
        }
    }

    // Main method
    public static void main(String[] args) {
        new Reception();
    }
}

// Stub classes (replace with full database logic)

class AddCustomer extends JFrame { AddCustomer() { setTitle("Add Customer"); setBounds(400,200,500,400); setVisible(true); } }
class Room extends JFrame { Room() { setTitle("Rooms"); setBounds(400,200,700,400); setVisible(true); } }
class Department extends JFrame { Department() { setTitle("Department"); setBounds(400,200,700,400); setVisible(true); } }
class EmployeeInfo extends JFrame { EmployeeInfo() { setTitle("Employee Info"); setBounds(400,200,700,400); setVisible(true); } }
class CustomerInfo extends JFrame { CustomerInfo() { setTitle("Customer Info"); setBounds(400,200,700,400); setVisible(true); } }
class ManagerInfo extends JFrame { ManagerInfo() { setTitle("Manager Info"); setBounds(400,200,700,400); setVisible(true); } }
class Checkout extends JFrame { Checkout() { setTitle("Checkout"); setBounds(400,200,700,400); setVisible(true); } }
class UpdateCheck extends JFrame { UpdateCheck() { setTitle("Update Status"); setBounds(400,200,700,400); setVisible(true); } }
class UpdateRoom extends JFrame { UpdateRoom() { setTitle("Update Room Status"); setBounds(400,200,700,400); setVisible(true); } }
class PickupService extends JFrame { PickupService() { setTitle("Pickup Service"); setBounds(400,200,700,400); setVisible(true); } }
class SearchRoom extends JFrame { SearchRoom() { setTitle("Search Room"); setBounds(400,200,700,400); setVisible(true); } }
