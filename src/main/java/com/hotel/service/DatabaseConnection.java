package main.java.com.hotel.service;

import java.sql.Statement;

public class DatabaseConnection {

    private static final String DB_URL = "jdbc:mysql://localhost:3306/hotel_reservation";
    private static final String USER = "root'@'localhost";
    private static final String PASSWORD = "root123";

    public static Statement getInstance() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getInstance'");
    }
}
