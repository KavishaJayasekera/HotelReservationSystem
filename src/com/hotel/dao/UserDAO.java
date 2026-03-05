// package com.hotel.dao;

// import java.sql.*;

// import com.hotel.model.User;

// public class UserDAO {

//     // Authenticate user
//     public User authenticate(String username, String password) {
//         String sql = "SELECT * FROM users WHERE username = ? AND password = ?";

//         try (Connection conn = DBConnection.getConnection();
//                 PreparedStatement stmt = conn.prepareStatement(sql)) {

//             stmt.setString(1, username);
//             stmt.setString(2, password);

//             ResultSet rs = stmt.executeQuery();

//             if (rs.next()) {
//                 User user = new User();
//                 user.setUserId(rs.getInt("user_id"));
//                 user.setUserId(rs.getString("username"));
//                 user.setPassword(rs.getString("password"));
//                 user.setRole(rs.getString("role"));
//                 return user;
//             }
//         } catch (SQLException e) {
//             e.printStackTrace();
//         }
//         return null;
//     }

//     // Create new user
//     public boolean createUser(User user) {
//         String sql = "INSERT INTO users (username, password, role) VALUES (?, ?, ?)";

//         try (Connection conn = DBConnection.getConnection();
//                 PreparedStatement stmt = conn.prepareStatement(sql)) {

//             stmt.setString(1, user.getUsername());
//             stmt.setString(2, user.getPassword());
//             stmt.setString(3, user.getRole());

//             return stmt.executeUpdate() > 0;
//         } catch (SQLException e) {
//             e.printStackTrace();
//             return false;
//         }
//     }
// }

package com.hotel.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO {

    public boolean authenticate(String username, String password) {
        String sql = "SELECT * FROM users WHERE username = ? AND password = ?";
        try (Connection conn = DBConnection.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, username);
            ps.setString(2, password);

            ResultSet rs = ps.executeQuery();
            return rs.next(); // returns true if user exists

        } catch (SQLException e) {
            System.out.println("Error during authentication!");
            e.printStackTrace();
        }
        return false;
    }
}