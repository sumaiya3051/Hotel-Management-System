package hotel.management.system;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;

public class EmployeeInfo extends JFrame {
    JTable table;

    public EmployeeInfo() {
        setTitle("Employee Information");
        setBounds(300, 150, 900, 500);
        setLayout(new BorderLayout());

        table = new JTable();
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        fetchEmployeeData();

        setVisible(true);
    }

    private void fetchEmployeeData() {
        try {
            DBConnection c = new DBConnection();
            String query = "SELECT name, age, gender, job, salary, email, phone FROM employee";
            ResultSet rs = c.s.executeQuery(query);

            DefaultTableModel model = new DefaultTableModel(new String[]{
                    "Name", "Age", "Gender", "Job", "Salary", "Email", "Phone"}, 0);

            while (rs.next()) {
                model.addRow(new Object[]{
                        rs.getString("name"),
                        rs.getString("age"),
                        rs.getString("gender"),
                        rs.getString("job"),
                        rs.getString("salary"),
                        rs.getString("email"),
                        rs.getString("phone")
                });
            }
            table.setModel(model);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error fetching employee data: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new EmployeeInfo());
    }
}
