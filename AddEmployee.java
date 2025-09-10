package hotel.management.system;


import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class AddEmployee extends JFrame implements ActionListener {
    
    JTextField tfname, tfemail, tfphone, tfage, tfsalary;
    JRadioButton rbmale, rbfemale;
    JButton submit;
    JComboBox<String> cbjob;
    
    AddEmployee() {
        setLayout(null);
        
        JLabel lblname = new JLabel("NAME");
        lblname.setBounds(60, 30, 120, 30);
        lblname.setFont(new Font("Tahoma", Font.PLAIN, 17));
        add(lblname);
        
        tfname = new JTextField();
        tfname.setBounds(200, 30, 150, 30);
        add(tfname);
        
        
        
        JLabel lblage = new JLabel("AGE");
        lblage.setBounds(60, 80, 120, 30);
        lblage.setFont(new Font("Tahoma", Font.PLAIN, 17));
        add(lblage);
        
        tfage = new JTextField();
        tfage.setBounds(200, 80, 150, 30);
        add(tfage);
        
        
        
        
        JLabel lblgender = new JLabel("GENDER");
        lblgender.setBounds(60, 130, 120, 30);
        lblgender.setFont(new Font("Tahoma", Font.PLAIN, 17));
        add(lblgender);
        
        rbmale = new JRadioButton("Male");
        rbmale.setBounds(200, 130, 70, 30);
        rbmale.setFont(new Font("Tahoma", Font.PLAIN, 14));
        rbmale.setBackground(Color.WHITE);
        add(rbmale);
        
        
        rbfemale = new JRadioButton("Female");
        rbfemale.setBounds(280, 130, 90, 30);
        rbfemale.setFont(new Font("Tahoma", Font.PLAIN, 14));
        rbfemale.setBackground(Color.WHITE);
        add(rbfemale);
        
        ButtonGroup bg = new ButtonGroup();
        bg.add(rbmale);
        bg.add(rbfemale);
        
        
        
        
        JLabel lbljob = new JLabel("JOB");
        lbljob.setBounds(60, 180, 120, 30);
        lbljob.setFont(new Font("Tahoma", Font.PLAIN, 17));
        add(lbljob);
        
        String str[] = {
            "Front Desk Clerks", 
            "Porters", 
            "Housekeeping", 
            "Kitchen Staff", 
            "Room Service", 
            "Chefs", 
            "Waiter/Waitress", 
            "Accountant"
        };
        
        cbjob = new JComboBox<>(str);
        cbjob.setBounds(200, 180, 150, 30);
        cbjob.setBackground(Color.WHITE);
        add(cbjob);
        
        
        
        JLabel lblsalary = new JLabel("SALARY");
        lblsalary.setBounds(60, 230, 120, 30);
        lblsalary.setFont(new Font("Tahoma", Font.PLAIN, 17));
        add(lblsalary);
        
        tfsalary = new JTextField();
        tfsalary.setBounds(200, 230, 150, 30);
        add(tfsalary);
        
        
        
        
        JLabel lblemail = new JLabel("EMAIL");
        lblemail.setBounds(60, 280, 120, 30);
        lblemail.setFont(new Font("Tahoma", Font.PLAIN, 17));
        add(lblemail);
        
        tfemail = new JTextField();
        tfemail.setBounds(200, 280, 150, 30);
        add(tfemail);
        
        
        
        JLabel lblphone = new JLabel("PHONE");
        lblphone.setBounds(60, 330, 120, 30);
        lblphone.setFont(new Font("Tahoma", Font.PLAIN, 17));
        add(lblphone);
        
        tfphone = new JTextField();
        tfphone.setBounds(200, 330, 150, 30);
        add(tfphone);
        
        
        
        submit = new JButton("Submit");
        submit.setBounds(200, 390, 120, 30);
        submit.setBackground(Color.BLACK);
        submit.setForeground(Color.WHITE);
        submit.addActionListener(this);
        add(submit);
        
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/21.jpg"));
        Image i2 = i1.getImage().getScaledInstance(400, 400, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(400, 60, 400, 400);
        add(image);
        
        getContentPane().setBackground(Color.WHITE);
        setBounds(350, 200, 850, 600);
        setVisible(true);
    }
    
    
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == submit) {
            String name = tfname.getText();
            String age = tfage.getText();
            String salary = tfsalary.getText();
            String email = tfemail.getText();
            String phone = tfphone.getText();
            String gender = rbmale.isSelected() ? "Male" : (rbfemale.isSelected() ? "Female" : "");
            String job = (String) cbjob.getSelectedItem();

            
            if (name.isEmpty() || age.isEmpty() || salary.isEmpty() || email.isEmpty() || phone.isEmpty() || gender.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Please fill all fields!");
                return;
            }
            
            try {
               
                int ageNum = Integer.parseInt(age);
                double salaryNum = Double.parseDouble(salary);

               
                try {
                    DBConnection c = new DBConnection();
                    String query = "INSERT INTO employee (name, age, gender, job, salary, email, phone) "
                                 + "VALUES ('"+name+"', '"+ageNum+"', '"+gender+"', '"+job+"', '"+salaryNum+"', '"+email+"', '"+phone+"')";
                    
                    c.s.executeUpdate(query);
                    JOptionPane.showMessageDialog(null, "Employee Added Successfully!");
                    setVisible(false);
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "Database Error: " + e.getMessage());
                }
                
            } catch (NumberFormatException nfe) {
                JOptionPane.showMessageDialog(null, "Age and Salary must be numeric!");
            }
        }
    }
    
    public static void main(String[] args) {
        new AddEmployee();
    }
}
