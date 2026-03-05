package main.java.com.hotel.util;

import java.sql.*;
import java.io.File;

public class DatabaseConnection {
    private static DatabaseConnection instance;
    private Connection connection;
    private static final String DB_PATH = "database/hotel_reservation.db";
    private static final String DB_URL = "jdbc:sqlite:" + DB_PATH;

    private DatabaseConnection() {
        try {
            Class.forName("org.sqlite.JDBC");

            // Create database directory if it doesn't exist
            File dbFile = new File(DB_PATH);
            File parentDir = dbFile.getParentFile();
            if (!parentDir.exists()) {
                parentDir.mkdirs();
            }

            connection = DriverManager.getConnection(DB_URL);
            createTables();
            System.out.println("Database connected successfully!");

        } catch (ClassNotFoundException e) {
            System.err.println("SQLite JDBC Driver not found!");
            e.printStackTrace();
        } catch (SQLException e) {
            System.err.println("Database connection failed: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static synchronized DatabaseConnection getInstance() {
        if (instance == null) {
            instance = new DatabaseConnection();
        }
        return instance;
    }

    public Connection getConnection() {
        try {
            if (connection == null || connection.isClosed()) {
                connection = DriverManager.getConnection(DB_URL);
            }
        } catch (SQLException e) {
            System.err.println("Error getting connection: " + e.getMessage());
        }
        return connection;
    }

    private void createTables() {
        String createUsersTable = """
                    CREATE TABLE IF NOT EXISTS users (
                        user_id INTEGER PRIMARY KEY AUTOINCREMENT,
                        username TEXT UNIQUE NOT NULL,
                        password TEXT NOT NULL,
                        role TEXT NOT NULL,
                        email TEXT UNIQUE NOT NULL,
                        created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                        last_login TIMESTAMP
                    )
                """;

        String createRoomsTable = """
                    CREATE TABLE IF NOT EXISTS rooms (
                        room_id INTEGER PRIMARY KEY AUTOINCREMENT,
                        room_number TEXT UNIQUE NOT NULL,
                        room_type TEXT NOT NULL,
                        rate_per_night REAL NOT NULL,
                        is_available BOOLEAN DEFAULT 1,
                        max_occupancy INTEGER NOT NULL,
                        description TEXT,
                        created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
                    )
                """;

        String createReservationsTable = """
                    CREATE TABLE IF NOT EXISTS reservations (
                        reservation_id INTEGER PRIMARY KEY AUTOINCREMENT,
                        reservation_number TEXT UNIQUE NOT NULL,
                        user_id INTEGER NOT NULL,
                        room_id INTEGER NOT NULL,
                        guest_name TEXT NOT NULL,
                        guest_address TEXT,
                        guest_contact TEXT NOT NULL,
                        check_in_date DATE NOT NULL,
                        check_out_date DATE NOT NULL,
                        number_of_guests INTEGER NOT NULL,
                        total_amount REAL DEFAULT 0,
                        status TEXT DEFAULT 'CONFIRMED',
                        booking_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                        special_requests TEXT,
                        FOREIGN KEY (user_id) REFERENCES users(user_id),
                        FOREIGN KEY (room_id) REFERENCES rooms(room_id)
                    )
                """;

        String createBillsTable = """
                    CREATE TABLE IF NOT EXISTS bills (
                        bill_id INTEGER PRIMARY KEY AUTOINCREMENT,
                        reservation_id INTEGER UNIQUE NOT NULL,
                        bill_number TEXT UNIQUE NOT NULL,
                        bill_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                        room_charges REAL NOT NULL,
                        service_charges REAL DEFAULT 0,
                        tax_amount REAL DEFAULT 0,
                        discount_amount REAL DEFAULT 0,
                        total_amount REAL NOT NULL,
                        amount_paid REAL DEFAULT 0,
                        balance_due REAL NOT NULL,
                        payment_status TEXT DEFAULT 'UNPAID',
                        FOREIGN KEY (reservation_id) REFERENCES reservations(reservation_id)
                    )
                """;

        String insertSampleUsers = """
                    INSERT OR IGNORE INTO users (username, password, role, email) VALUES
                    ('admin', 'admin123', 'ADMIN', 'admin@hotel.com'),
                    ('reception', 'reception123', 'RECEPTIONIST', 'reception@hotel.com')
                """;

        String insertSampleRooms = """
                    INSERT OR IGNORE INTO rooms (room_number, room_type, rate_per_night, max_occupancy, description) VALUES
                    ('101', 'Single', 75.00, 1, 'Single room with city view'),
                    ('102', 'Single', 75.00, 1, 'Single room with garden view'),
                    ('201', 'Double', 120.00, 2, 'Double room with queen bed'),
                    ('202', 'Double', 120.00, 2, 'Double room with twin beds'),
                    ('301', 'Suite', 200.00, 3, 'Luxury suite'),
                    ('401', 'Deluxe', 150.00, 2, 'Deluxe room')
                """;

        try (Statement stmt = connection.createStatement()) {
            stmt.execute(createUsersTable);
            stmt.execute(createRoomsTable);
            stmt.execute(createReservationsTable);
            stmt.execute(createBillsTable);
            stmt.execute(insertSampleUsers);
            stmt.execute(insertSampleRooms);
            System.out.println("Database tables created successfully!");
        } catch (SQLException e) {
            System.err.println("Error creating tables: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void closeConnection() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
                System.out.println("Database connection closed.");
            }
        } catch (SQLException e) {
            System.err.println("Error closing connection: " + e.getMessage());
        }
    }
}