package com.hotel.ui;

import java.util.Scanner;
import com.hotel.dao.UserDAO;

public class LoginUI {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        UserDAO userDAO = new UserDAO();

        System.out.println("=================================");
        System.out.println("   HOTEL RESERVATION SYSTEM");
        System.out.println("=================================");

        System.out.print("Enter Username: ");
        String username = scanner.nextLine();

        System.out.print("Enter Password: ");
        String password = scanner.nextLine();

        boolean loginSuccess = userDAO.authenticate(username, password);

        if (loginSuccess) {
            System.out.println("Login Successful! Welcome " + username);
        } else {
            System.out.println("Invalid Username or Password!");
        }

        scanner.close();
    }
}