-- database/setup.sql
-- Run this in MySQL Workbench or MySQL command line

CREATE DATABASE IF NOT EXISTS hotel_reservation_system;
USE hotel_reservation_system;

-- Users table for authentication
CREATE TABLE IF NOT EXISTS users (
    user_id INT PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(50) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    role VARCHAR(20) DEFAULT 'staff',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Guests table
CREATE TABLE IF NOT EXISTS guests (
    guest_id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL,
    address TEXT,
    contact_number VARCHAR(20),
    email VARCHAR(100),
    id_proof VARCHAR(50),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Rooms table
CREATE TABLE IF NOT EXISTS rooms (
    room_id INT PRIMARY KEY AUTO_INCREMENT,
    room_number VARCHAR(10) UNIQUE NOT NULL,
    room_type ENUM('Standard', 'Deluxe', 'Suite', 'Family', 'Presidential') NOT NULL,
    rate_per_night DECIMAL(10,2) NOT NULL,
    capacity INT,
    status ENUM('Available', 'Occupied', 'Maintenance') DEFAULT 'Available',
    description TEXT
);

-- Reservations table
CREATE TABLE IF NOT EXISTS reservations (
    reservation_id INT PRIMARY KEY AUTO_INCREMENT,
    reservation_number VARCHAR(20) UNIQUE NOT NULL,
    guest_id INT,
    room_id INT,
    check_in_date DATE NOT NULL,
    check_out_date DATE NOT NULL,
    number_of_guests INT,
    total_amount DECIMAL(10,2),
    status ENUM('Confirmed', 'Checked-In', 'Checked-Out', 'Cancelled') DEFAULT 'Confirmed',
    special_requests TEXT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (guest_id) REFERENCES guests(guest_id),
    FOREIGN KEY (room_id) REFERENCES rooms(room_id)
);

-- Payments table
CREATE TABLE IF NOT EXISTS payments (
    payment_id INT PRIMARY KEY AUTO_INCREMENT,
    reservation_id INT,
    amount DECIMAL(10,2),
    payment_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    payment_method ENUM('Cash', 'Credit Card', 'Debit Card', 'Online'),
    status ENUM('Pending', 'Completed', 'Failed') DEFAULT 'Completed',
    FOREIGN KEY (reservation_id) REFERENCES reservations(reservation_id)
);

-- Insert sample data
INSERT INTO users (username, password, role) VALUES 
('admin', 'admin123', 'admin'),
('staff', 'staff123', 'staff')
ON DUPLICATE KEY UPDATE username=username;

INSERT INTO rooms (room_number, room_type, rate_per_night, capacity, description) VALUES
('101', 'Standard', 5000, 2, 'Standard room with city view'),
('102', 'Standard', 5000, 2, 'Standard room with garden view'),
('201', 'Deluxe', 8000, 3, 'Deluxe room with balcony'),
('202', 'Deluxe', 8000, 3, 'Deluxe room with sea view'),
('301', 'Suite', 12000, 4, 'Luxury suite with living area'),
('302', 'Suite', 12000, 4, 'Executive suite'),
('401', 'Family', 15000, 5, 'Family room with 2 bedrooms'),
('501', 'Presidential', 25000, 6, 'Presidential suite with jacuzzi')
ON DUPLICATE KEY UPDATE room_number=room_number;