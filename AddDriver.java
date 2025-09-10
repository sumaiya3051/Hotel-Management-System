package hotel.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class AddDriver extends JFrame implements ActionListener {

    JButton add, cancel;
    JTextField tfname, tfage, tfcompany, tfmodel, tflocation;
    JComboBox<String> gendercombo, availablecombo;

    AddDriver() {

        getContentPane().setBackground(Color.WHITE);
        setLayout(null);

        JLabel heading = new JLabel("Add Driver");
        heading.setFont(new Font("Tahoma", Font.BOLD, 18));
        heading.setBounds(150, 10, 200, 20);
        add(heading);

        // Name
        JLabel lblname = new JLabel("Name");
        lblname.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblname.setBounds(60, 70, 120, 30);
        add(lblname);

        tfname = new JTextField();
        tfname.setBounds(200, 70, 150, 30);
        add(tfname);

        // Age
        JLabel lblage = new JLabel("Age");
        lblage.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblage.setBounds(60, 120, 120, 30);
        add(lblage);

        tfage = new JTextField();
        tfage.setBounds(200, 120, 150, 30);
        add(tfage);

        // Gender
        JLabel lblgender = new JLabel("Gender");
        lblgender.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblgender.setBounds(60, 170, 120, 30);
        add(lblgender);

        String genders[] = {"Male", "Female"};
        gendercombo = new JComboBox<>(genders);
        gendercombo.setBounds(200, 170, 150, 30);
        gendercombo.setBackground(Color.WHITE);
        add(gendercombo);

        // Car Company
        JLabel lblcompany = new JLabel("Car Company");
        lblcompany.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblcompany.setBounds(60, 220, 120, 30);
        add(lblcompany);

        tfcompany = new JTextField();
        tfcompany.setBounds(200, 220, 150, 30);
        add(tfcompany);

        // Car Model
        JLabel lblmodel = new JLabel("Car Model");
        lblmodel.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblmodel.setBounds(60, 270, 120, 30);
        add(lblmodel);

        tfmodel = new JTextField();
        tfmodel.setBounds(200, 270, 150, 30);
        add(tfmodel);

        // Availability
        JLabel lblavailable = new JLabel("Available");
        lblavailable.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblavailable.setBounds(60, 320, 120, 30);
        add(lblavailable);

        String avail[] = {"Available", "Busy"};
        availablecombo = new JComboBox<>(avail);
        availablecombo.setBounds(200, 320, 150, 30);
        availablecombo.setBackground(Color.WHITE);
        add(availablecombo);

        // Location
        JLabel lbllocation = new JLabel("Location");
        lbllocation.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lbllocation.setBounds(60, 370, 120, 30);
        add(lbllocation);

        tflocation = new JTextField();
        tflocation.setBounds(200, 370, 150, 30);
        add(tflocation);

        // Buttons
        add = new JButton("Add Driver");
        add.setForeground(Color.WHITE);
        add.setBackground(Color.BLACK);
        add.setBounds(60, 420, 130, 30);
        add.addActionListener(this);
        add(add);

        cancel = new JButton("Cancel");
        cancel.setForeground(Color.WHITE);
        cancel.setBackground(Color.BLACK);
        cancel.setBounds(220, 420, 130, 30);
        cancel.addActionListener(this);
        add(cancel);

        // Image
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/11.jpg"));
        Image i2 = i1.getImage().getScaledInstance(500, 300, Image.SCALE_DEFAULT);
        JLabel image = new JLabel(new ImageIcon(i2));
        image.setBounds(400, 30, 500, 300);
        add(image);

        setBounds(330, 150, 980, 500);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == add) {
            String name = tfname.getText();
            String age = tfage.getText();
            String gender = (String) gendercombo.getSelectedItem();
            String company = tfcompany.getText();
            String model = tfmodel.getText();
            String availability = (String) availablecombo.getSelectedItem();
            String location = tflocation.getText();

            try {
                DBConnection c = new DBConnection();
                String str = "INSERT INTO driver (name, age, gender, company, model, availability, location) VALUES ('"
                        + name + "', '" + age + "', '" + gender + "', '" + company + "', '" + model + "', '" + availability + "', '" + location + "')";
                c.s.executeUpdate(str);

                JOptionPane.showMessageDialog(null, "New Driver Added Successfully!");
                setVisible(false);

            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
            }
        } else if (ae.getSource() == cancel) {
            setVisible(false);
        }
    }

    public static void main(String[] args) {
        new AddDriver();
    }
}
