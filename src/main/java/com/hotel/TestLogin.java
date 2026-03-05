package main.java.com.hotel;

import com.hotel.dao.UserDAO;

public class TestLogin {
    public static void main(String[] args) {
        System.out.println("==================================");
        System.out.println("Testing Hotel Reservation System");
        System.out.println("==================================\n");

        UserDAO userDAO = new UserDAO();

        // Test admin login
        System.out.println("Attempting login with: admin / admin123");
        boolean success = userDAO.authenticate("admin", "admin123");
        System.out.println(success ? "Login successful!" : "Login failed - Invalid credentials!");
    }
}