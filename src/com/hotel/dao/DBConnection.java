// package com.hotel.dao;

// import java.sql.Connection;
// import java.sql.DriverManager;
// import java.sql.SQLException;

// public class DBConnection {

//     private static final String URL = "jdbc:mysql://localhost:3306/hotel_db"; // replace with your DB name
//     private static final String USER = "root"; // replace with your DB username
//     private static final String PASSWORD = "Root@123*"; // replace with your DB password

//     public static Connection getConnection() {
//         try {
//             // Load MySQL JDBC driver (optional for newer versions)
//             Class.forName("com.mysql.cj.jdbc.Driver");

//             // Return connection
//             return DriverManager.getConnection(URL, USER, PASSWORD);
//         } catch (ClassNotFoundException e) {
//             System.out.println("JDBC Driver not found!");
//             e.printStackTrace();
//         } catch (SQLException e) {
//             System.out.println("Failed to connect to database!");
//             e.printStackTrace();
//         }
//         return null; // if connection fails
//     }
// }

package com.hotel.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DBConnection {

    private static final String URL = "jdbc:mysql://localhost:3306/hotel_db?serverTimezone=UTC";
    private static final String USER = "root"; // your MySQL username
    private static final String PASSWORD = "Root@123*"; // your MySQL password

    public static Connection getConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/hotel_db",
                    "root",
                    "Root@123*");

            // Ensure tables exist
            createTablesIfNotExist(conn);

            return conn;
        } catch (ClassNotFoundException e) {
            System.out.println("JDBC Driver not found!");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("Failed to connect to database!");
            e.printStackTrace();
        }
        return null;
    }

    private static void createTablesIfNotExist(Connection conn) {
        try (Statement stmt = conn.createStatement()) {

            // Users table
            stmt.executeUpdate(
                    "CREATE TABLE IF NOT EXISTS users (" +
                            "id INT AUTO_INCREMENT PRIMARY KEY, " +
                            "username VARCHAR(50) NOT NULL UNIQUE, " +
                            "password VARCHAR(255) NOT NULL, " +
                            "role VARCHAR(20) NOT NULL DEFAULT 'user', " +
                            "created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP" +
                            ")");

            // Insert admin user if not exists
            stmt.executeUpdate(
                    "INSERT IGNORE INTO users (id, username, password, role) VALUES " +
                            "(1, 'admin', 'admin123', 'admin')");

            // Rooms table
            stmt.executeUpdate(
                    "CREATE TABLE IF NOT EXISTS rooms (" +
                            "id INT AUTO_INCREMENT PRIMARY KEY, " +
                            "room_number VARCHAR(10) NOT NULL UNIQUE, " +
                            "type VARCHAR(50) NOT NULL, " +
                            "price DECIMAL(10,2) NOT NULL, " +
                            "status VARCHAR(20) NOT NULL DEFAULT 'Available', " +
                            "created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP" +
                            ")");

            // Bookings table
            stmt.executeUpdate(
                    "CREATE TABLE IF NOT EXISTS bookings (" +
                            "id INT AUTO_INCREMENT PRIMARY KEY, " +
                            "user_id INT NOT NULL, " +
                            "room_id INT NOT NULL, " +
                            "check_in DATE NOT NULL, " +
                            "check_out DATE NOT NULL, " +
                            "status VARCHAR(20) NOT NULL DEFAULT 'Booked', " +
                            "created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP, " +
                            "FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE, " +
                            "FOREIGN KEY (room_id) REFERENCES rooms(id) ON DELETE CASCADE" +
                            ")");

            // Payments table
            stmt.executeUpdate(
                    "CREATE TABLE IF NOT EXISTS payments (" +
                            "id INT AUTO_INCREMENT PRIMARY KEY, " +
                            "booking_id INT NOT NULL, " +
                            "amount DECIMAL(10,2) NOT NULL, " +
                            "payment_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP, " +
                            "method VARCHAR(50) NOT NULL, " +
                            "FOREIGN KEY (booking_id) REFERENCES bookings(id) ON DELETE CASCADE" +
                            ")");

        } catch (SQLException e) {
            System.out.println("Failed to create tables!");
            e.printStackTrace();
        }
    }
}