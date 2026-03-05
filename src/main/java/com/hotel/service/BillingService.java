package main.java.com.hotel.service;

import com.hotel.dao.RoomDAO;
import com.hotel.model.Room;

public class BillingService {
    private BillDAO billDAO;
    private ReservationDAO reservationDAO;
    private RoomDAO roomDAO;

    public BillingService() {
        this.billDAO = new BillDAO();
        this.reservationDAO = new ReservationDAO();
        this.roomDAO = new RoomDAO();
    }

    public Bill generateBill(int reservationId) {
        main.java.com.hotel.service.Reservation reservation = reservationDAO.getReservationById(reservationId);
        if (reservation == null) {
            return null;
        }

        Room room = roomDAO.getRoomById(reservation.getRoomId());
        if (room == null) {
            return null;
        }

        // Calculate room charges
        long nights = reservation.getNumberOfNights();
        double roomCharges = nights * room.getRatePerNight();

        Bill bill = new Bill();
        bill.setRoomCharges(roomCharges);
        bill.setRoomCharges(0); // Default
        bill.setTaxAmount(10.0); // 10% tax
        bill.setTaxAmount(0); // No discount
        bill.setAmountPaid(0);

        // Calculate total
        bill.calculateTotal();

        if (billDAO.createBill(bill)) {
            return bill;
        }
        return null;
    }

    public Bill getBill(int reservationId) {
        return billDAO.getBillByReservationId(reservationId);
    }

    public boolean processPayment(int billId, double amount) {
        Bill bill = billDAO.getBillByReservationId(billId);
        if (bill != null) {
            double newAmountPaid = bill.getAmountPaid() + amount;
            bill.setAmountPaid(newAmountPaid);
            bill.calculateTotal();
            return billDAO.createBill(bill);
        }
        return false;
    }
}