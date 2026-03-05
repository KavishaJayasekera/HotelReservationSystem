// package main.java.com.hotel.service;

// import java.time.LocalDate;
// import java.util.List;

// import main.java.com.hotel.model.Reservation;

// public class ReservationDAO {

//     public boolean createReservation(Reservation reservation) {
//         // TODO Auto-generated method stub
//         throw new UnsupportedOperationException("Unimplemented method 'createReservation'");
//     }

//     public Reservation getReservationByNumber(String reservationNumber) {
//         // TODO Auto-generated method stub
//         throw new UnsupportedOperationException("Unimplemented method 'getReservationByNumber'");
//     }

//     public Reservation getReservationById(int reservationId) {
//         // TODO Auto-generated method stub
//         throw new UnsupportedOperationException("Unimplemented method 'getReservationById'");
//     }

//     public boolean updateReservationStatus(int reservationId, String string) {
//         // TODO Auto-generated method stub
//         throw new UnsupportedOperationException("Unimplemented method 'updateReservationStatus'");
//     }

//     public List<Reservation> getReservationsByGuestName(String guestName) {
//         // TODO Auto-generated method stub
//         throw new UnsupportedOperationException("Unimplemented method 'getReservationsByGuestName'");
//     }

//     public List<Reservation> getReservationsByDate(LocalDate date) {
//         // TODO Auto-generated method stub
//         throw new UnsupportedOperationException("Unimplemented method 'getReservationsByDate'");
//     }

// }

package main.java.com.hotel.service;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ReservationDAO {

    public boolean createReservation1(Reservation reservation) {
        String sql = "INSERT INTO reservations (reservation_number, user_id, room_id, guest_name, " +
                "guest_address, guest_contact, check_in_date, check_out_date, number_of_guests, " +
                "special_requests) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getInstance().getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            pstmt.setString(1, reservation.getReservationNumber());
            pstmt.setInt(2, reservation.getUserId());
            pstmt.setInt(3, reservation.getRoomId());
            pstmt.setString(4, reservation.getGuestName());
            pstmt.setString(5, reservation.getGuestAddress());
            pstmt.setString(6, reservation.getGuestContact());
            pstmt.setString(7, reservation.getCheckInDate().toString());
            pstmt.setString(8, reservation.getCheckOutDate().toString());
            pstmt.setInt(9, reservation.getNumberOfGuests());
            pstmt.setString(10, reservation.getSpecialRequests());

            int affectedRows = pstmt.executeUpdate();

            if (affectedRows > 0) {
                try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        reservation.setReservationId(generatedKeys.getInt(1));
                    }
                }
                return true;
            }
        } catch (SQLException e) {
            System.err.println("Error creating reservation: " + e.getMessage());
        }
        return false;
    }

    public Reservation getReservationById(int reservationId) {
        String sql = "SELECT * FROM reservations WHERE reservation_id = ?";

        try (Connection conn = DatabaseConnection.getInstance().getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, reservationId);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                return extractReservationFromResultSet(rs);
            }
        } catch (SQLException e) {
            System.err.println("Error getting reservation: " + e.getMessage());
        }
        return null;
    }

    public Reservation getReservationByNumber(String reservationNumber) {
        String sql = "SELECT * FROM reservations WHERE reservation_number = ?";

        try (Connection conn = DatabaseConnection.getInstance().getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, reservationNumber);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                return extractReservationFromResultSet(rs);
            }
        } catch (SQLException e) {
            System.err.println("Error getting reservation: " + e.getMessage());
        }
        return null;
    }

    public List<Reservation> getReservationsByGuestName(String guestName) {
        List<Reservation> reservations = new ArrayList<>();
        String sql = "SELECT * FROM reservations WHERE guest_name LIKE ? ORDER BY check_in_date";

        try (Connection conn = DatabaseConnection.getInstance().getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, "%" + guestName + "%");
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                reservations.add(extractReservationFromResultSet(rs));
            }
        } catch (SQLException e) {
            System.err.println("Error getting reservations: " + e.getMessage());
        }
        return reservations;
    }

    public List<Reservation> getReservationsByDate(LocalDate date) {
        List<Reservation> reservations = new ArrayList<>();
        String sql = "SELECT * FROM reservations WHERE check_in_date <= ? AND check_out_date >= ?";

        try (Connection conn = DatabaseConnection.getInstance().getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, date.toString());
            pstmt.setString(2, date.toString());
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                reservations.add(extractReservationFromResultSet(rs));
            }
        } catch (SQLException e) {
            System.err.println("Error getting reservations: " + e.getMessage());
        }
        return reservations;
    }

    public boolean updateReservationStatus(int reservationId, String status) {
        String sql = "UPDATE reservations SET status = ? WHERE reservation_id = ?";

        try (Connection conn = DatabaseConnection.getInstance().getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, status);
            pstmt.setInt(2, reservationId);

            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error updating reservation status: " + e.getMessage());
        }
        return false;
    }

    private Reservation extractReservationFromResultSet(ResultSet rs) throws SQLException {
        Reservation reservation = new Reservation();
        reservation.setReservationId(rs.getInt("reservation_id"));
        reservation.setReservationNumber(rs.getString("reservation_number"));
        reservation.setUserId(rs.getInt("user_id"));
        reservation.setRoomId(rs.getInt("room_id"));
        reservation.setGuestName(rs.getString("guest_name"));
        reservation.setGuestName(rs.getString("guest_address"));
        reservation.setGuestContact(rs.getString("guest_contact"));
        reservation.setCheckInDate(LocalDate.parse(rs.getString("check_in_date")));
        reservation.setCheckOutDate(LocalDate.parse(rs.getString("check_out_date")));
        reservation.setNumberOfGuests(rs.getInt("number_of_guests"));
        reservation.setTotalAmount(rs.getDouble("total_amount"));
        reservation.setStatus(rs.getString("status"));
        reservation.setSpecialRequests(rs.getString("special_requests"));
        return reservation;
    }

    public boolean createReservation(Reservation reservation) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'createReservation'");
    }
}