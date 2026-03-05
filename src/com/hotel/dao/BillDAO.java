package com.hotel.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import main.java.com.hotel.service.Bill;
import main.java.com.hotel.service.DatabaseConnection;

public class BillDAO {

    public boolean createBill(Bill bill) {
        String sql = "INSERT INTO bills (reservation_id, bill_number, room_charges, service_charges, " +
                "tax_amount, discount_amount, total_amount, amount_paid, balance_due, payment_status) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getInstance().getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            pstmt.setInt(1, bill.getReservationId());
            pstmt.setString(2, bill.getBillNumber());
            pstmt.setDouble(3, bill.getRoomCharges());
            pstmt.setDouble(4, bill.getServiceCharges());
            pstmt.setDouble(5, bill.getTaxAmount());
            pstmt.setDouble(6, bill.getDiscountAmount());
            pstmt.setDouble(7, bill.getTotalAmount());
            pstmt.setDouble(8, bill.getAmountPaid());
            pstmt.setDouble(9, bill.getBalanceDue());
            pstmt.setString(10, bill.getPaymentStatus());

            int affectedRows = pstmt.executeUpdate();

            if (affectedRows > 0) {
                try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        bill.setBillId(generatedKeys.getInt(1));
                    }
                }
                return true;
            }
        } catch (SQLException e) {
            System.err.println("Error creating bill: " + e.getMessage());
        }
        return false;
    }

    public Bill getBillById(int billId) {
        String sql = "SELECT * FROM bills WHERE bill_id = ?";

        try (Connection conn = DatabaseConnection.getInstance().getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, billId);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                return extractBillFromResultSet(rs);
            }
        } catch (SQLException e) {
            System.err.println("Error getting bill: " + e.getMessage());
        }
        return null;
    }

    public Bill getBillByReservationId(int reservationId) {
        String sql = "SELECT * FROM bills WHERE reservation_id = ?";

        try (Connection conn = DatabaseConnection.getInstance().getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, reservationId);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                return extractBillFromResultSet(rs);
            }
        } catch (SQLException e) {
            System.err.println("Error getting bill: " + e.getMessage());
        }
        return null;
    }

    public boolean updateBill(Bill bill) {
        String sql = "UPDATE bills SET room_charges = ?, service_charges = ?, tax_amount = ?, " +
                "discount_amount = ?, total_amount = ?, amount_paid = ?, balance_due = ?, " +
                "payment_status = ? WHERE bill_id = ?";

        try (Connection conn = DatabaseConnection.getInstance().getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setDouble(1, bill.getRoomCharges());
            pstmt.setDouble(2, bill.getServiceCharges());
            pstmt.setDouble(3, bill.getTaxAmount());
            pstmt.setDouble(4, bill.getDiscountAmount());
            pstmt.setDouble(5, bill.getTotalAmount());
            pstmt.setDouble(6, bill.getAmountPaid());
            pstmt.setDouble(7, bill.getBalanceDue());
            pstmt.setString(8, bill.getPaymentStatus());
            pstmt.setInt(9, bill.getBillId());

            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error updating bill: " + e.getMessage());
        }
        return false;
    }

    public List<Bill> getBillsByStatus(String status) {
        List<Bill> bills = new ArrayList<>();
        String sql = "SELECT * FROM bills WHERE payment_status = ?";

        try (Connection conn = DatabaseConnection.getInstance().getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, status);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                bills.add(extractBillFromResultSet(rs));
            }
        } catch (SQLException e) {
            System.err.println("Error getting bills: " + e.getMessage());
        }
        return bills;
    }

    private Bill extractBillFromResultSet(ResultSet rs) throws SQLException {
        Bill bill = new Bill();
        bill.setBillId(rs.getInt("bill_id"));
        bill.setReservationId(rs.getInt("reservation_id"));
        bill.setBillNumber(rs.getString("bill_number"));
        bill.setRoomCharges(rs.getDouble("room_charges"));
        bill.setServiceCharges(rs.getDouble("service_charges"));
        bill.setTaxAmount(rs.getDouble("tax_amount"));
        bill.setDiscountAmount(rs.getDouble("discount_amount"));
        bill.setTotalAmount(rs.getDouble("total_amount"));
        bill.setAmountPaid(rs.getDouble("amount_paid"));
        bill.setBalanceDue(rs.getDouble("balance_due"));
        bill.setPaymentStatus(rs.getString("payment_status"));
        return bill;
    }
}