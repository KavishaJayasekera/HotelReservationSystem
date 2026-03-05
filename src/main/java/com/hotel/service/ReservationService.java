package main.java.com.hotel.service;

import java.time.LocalDate;
import java.util.List;

import com.hotel.dao.RoomDAO;

public class ReservationService {
    private ReservationDAO reservationDAO;
    private RoomDAO roomDAO;

    public ReservationService() {
        this.reservationDAO = new ReservationDAO();
        this.roomDAO = new RoomDAO();
    }

    public String createReservation(int userId, int roomId, String guestName,
            String guestAddress, String guestContact,
            LocalDate checkIn, LocalDate checkOut,
            int guests, String specialRequests) {

        // Generate reservation number
        String reservationNumber = "RES" + System.currentTimeMillis();

        main.java.com.hotel.service.Reservation reservation = new main.java.com.hotel.service.Reservation();

        if (reservationDAO.createReservation(reservation)) {
            // Update room availability
            roomDAO.updateRoomAvailability(roomId, false);
            return reservationNumber;
        }
        return null;
    }

    public main.java.com.hotel.service.Reservation getReservation(String reservationNumber) {
        return reservationDAO.getReservationByNumber(reservationNumber);
    }

    public boolean cancelReservation(int reservationId) {
        main.java.com.hotel.service.Reservation reservation = reservationDAO.getReservationById(reservationId);
        if (reservation != null) {
            // Update room availability
            roomDAO.updateRoomAvailability(reservation.getRoomId(), true);
            // Update reservation status
            return reservationDAO.updateReservationStatus(reservationId, "CANCELLED");
        }
        return false;
    }

    public List<main.java.com.hotel.service.Reservation> getReservationsByGuest(String guestName) {
        return reservationDAO.getReservationsByGuestName(guestName);
    }

    public List<main.java.com.hotel.service.Reservation> getReservationsByDate(LocalDate date) {
        return reservationDAO.getReservationsByDate(date);
    }
}