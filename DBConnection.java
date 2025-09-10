package hotel.management.system;

import java.sql.*;

public class DBConnection {
    
    public Connection c;
    public Statement s;

    public DBConnection() {
        try {
            // 1. Load MySQL Driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // 2. Connect to Database (XAMPP MySQL)
            // database name = hotelmanagementsystem
            // username = root
            // password = (blank by default in XAMPP)
            c = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/hotelmanagementsystem",
                    "root",
                    ""   // if you set a password in XAMPP, put it here
            );

            // 3. Create Statement
            s = c.createStatement();

            System.out.println("✅ Database Connected Successfully!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}



