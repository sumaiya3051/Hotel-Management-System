package hotel.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class PickupService extends JFrame implements ActionListener {

    JTextField tfName, tfAge, tfCompany, tfBrand, tfAvailability, tfLocation;
    JComboBox<String> cbTypeOfCar;
    JRadioButton rbMale, rbFemale;
    JButton submit, back;

    PickupService() {
        setTitle("Pickup Service");
        setLayout(null);
        getContentPane().setBackground(Color.WHITE);
        setBounds(350, 200, 900, 500);

        JLabel heading = new JLabel("PICKUP SERVICE");
        heading.setFont(new Font("Tahoma", Font.BOLD, 24));
        heading.setBounds(300, 10, 300, 30);
        add(heading);

        JLabel lblTypeOfCar = new JLabel("Type Of Car");
        lblTypeOfCar.setBounds(50, 70, 120, 25);
        add(lblTypeOfCar);

        String[] carTypes = {"Sedan", "SUV", "Minivan", "Luxury", "Other"};
        cbTypeOfCar = new JComboBox<>(carTypes);
        cbTypeOfCar.setBounds(180, 70, 150, 25);
        add(cbTypeOfCar);

        JLabel lblName = new JLabel("Name");
        lblName.setBounds(50, 110, 120, 25);
        add(lblName);
        tfName = new JTextField();
        tfName.setBounds(180, 110, 150, 25);
        add(tfName);

        JLabel lblAge = new JLabel("Age");
        lblAge.setBounds(50, 150, 120, 25);
        add(lblAge);
        tfAge = new JTextField();
        tfAge.setBounds(180, 150, 150, 25);
        add(tfAge);

        JLabel lblGender = new JLabel("Gender");
        lblGender.setBounds(50, 190, 120, 25);
        add(lblGender);
        rbMale = new JRadioButton("Male");
        rbMale.setBounds(180, 190, 70, 25);
        rbMale.setBackground(Color.WHITE);
        add(rbMale);
        rbFemale = new JRadioButton("Female");
        rbFemale.setBounds(260, 190, 90, 25);
        rbFemale.setBackground(Color.WHITE);
        add(rbFemale);
        ButtonGroup bg = new ButtonGroup();
        bg.add(rbMale);
        bg.add(rbFemale);

        JLabel lblCompany = new JLabel("Company");
        lblCompany.setBounds(50, 230, 120, 25);
        add(lblCompany);
        tfCompany = new JTextField();
        tfCompany.setBounds(180, 230, 150, 25);
        add(tfCompany);

        JLabel lblBrand = new JLabel("Brand");
        lblBrand.setBounds(50, 270, 120, 25);
        add(lblBrand);
        tfBrand = new JTextField();
        tfBrand.setBounds(180, 270, 150, 25);
        add(tfBrand);

        JLabel lblAvailability = new JLabel("Availability");
        lblAvailability.setBounds(50, 310, 120, 25);
        add(lblAvailability);
        tfAvailability = new JTextField();
        tfAvailability.setBounds(180, 310, 150, 25);
        add(tfAvailability);

        JLabel lblLocation = new JLabel("Location");
        lblLocation.setBounds(50, 350, 120, 25);
        add(lblLocation);
        tfLocation = new JTextField();
        tfLocation.setBounds(180, 350, 150, 25);
        add(tfLocation);

        submit = new JButton("Submit");
        submit.setBounds(50, 400, 120, 30);
        submit.setBackground(Color.BLACK);
        submit.setForeground(Color.WHITE);
        submit.addActionListener(this);
        add(submit);

        back = new JButton("Back");
        back.setBounds(210, 400, 120, 30);
        back.setBackground(Color.BLACK);
        back.setForeground(Color.WHITE);
        back.addActionListener(this);
        add(back);

        try {
            ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/40.jpg"));
            Image i2 = i1.getImage().getScaledInstance(400, 350, Image.SCALE_DEFAULT);
            JLabel image = new JLabel(new ImageIcon(i2));
            image.setBounds(400, 80, 400, 350);
            add(image);
        } catch (Exception e) {
            System.out.println("Image not found.");
        }

        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == submit) {
            String type = (String) cbTypeOfCar.getSelectedItem();
            String name = tfName.getText();
            String age = tfAge.getText();
            String gender = rbMale.isSelected() ? "Male" : (rbFemale.isSelected() ? "Female" : "");
            String company = tfCompany.getText();
            String brand = tfBrand.getText();
            String availability = tfAvailability.getText();
            String location = tfLocation.getText();

            if (name.isEmpty() || age.isEmpty() || gender.isEmpty() || company.isEmpty() ||
                    brand.isEmpty() || availability.isEmpty() || location.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Please fill all fields!");
                return;
            }

            try {
                int ageNum = Integer.parseInt(age);

                DBConnection c = new DBConnection();
                String query = "INSERT INTO pickup_service (type_of_car, name, age, gender, company, brand, availability, location) "
                        + "VALUES ('" + type + "', '" + name + "', " + ageNum + ", '" + gender + "', '" + company + "', '" + brand + "', '" + availability + "', '" + location + "')";
                c.s.executeUpdate(query);
                JOptionPane.showMessageDialog(null, "Pickup Service Details Saved Successfully!");
            } catch (NumberFormatException nfe) {
                JOptionPane.showMessageDialog(null, "Age must be numeric!");
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Database Error: " + e.getMessage());
            }

        } else if (ae.getSource() == back) {
            setVisible(false);
        }
    }

    public static void main(String[] args) {
        new PickupService();
    }
}
