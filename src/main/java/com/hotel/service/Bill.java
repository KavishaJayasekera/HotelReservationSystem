package main.java.com.hotel.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Bill {
    private int billId;
    private int reservationId;
    private String billNumber;
    private LocalDateTime billDate;
    private double roomCharges;
    private double serviceCharges;
    private double taxAmount;
    private double discountAmount;
    private double totalAmount;
    private double amountPaid;
    private double balanceDue;
    private String paymentStatus;
    private List<BillItem> additionalCharges;

    public static class BillItem {
        private String description;
        private double amount;
        private int quantity;

        public BillItem(String description, double amount, int quantity) {
            this.description = description;
            this.amount = amount;
            this.quantity = quantity;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public double getAmount() {
            return amount;
        }

        public void setAmount(double amount) {
            this.amount = amount;
        }

        public int getQuantity() {
            return quantity;
        }

        public void setQuantity(int quantity) {
            this.quantity = quantity;
        }

        public double getSubtotal() {
            return amount * quantity;
        }
    }

    public Bill() {
        this.additionalCharges = new ArrayList<>();
        this.billDate = LocalDateTime.now();
    }

    public Bill(int reservationId, String billNumber) {
        this();
        this.reservationId = reservationId;
        this.billNumber = billNumber;
    }

    public void addCharge(String description, double amount, int quantity) {
        additionalCharges.add(new BillItem(description, amount, quantity));
    }

    public double calculateTotal() {
        double subtotal = roomCharges + serviceCharges;

        for (BillItem item : additionalCharges) {
            subtotal += item.getSubtotal();
        }

        double tax = subtotal * (taxAmount / 100);
        double discount = subtotal * (discountAmount / 100);

        totalAmount = subtotal + tax - discount;
        balanceDue = totalAmount - amountPaid;

        if (balanceDue <= 0) {
            paymentStatus = "PAID";
        } else if (amountPaid > 0) {
            paymentStatus = "PARTIAL";
        } else {
            paymentStatus = "UNPAID";
        }

        return totalAmount;
    }

    // Getters and Setters
    public int getBillId() {
        return billId;
    }

    public void setBillId(int billId) {
        this.billId = billId;
    }

    public int getReservationId() {
        return reservationId;
    }

    public void setReservationId(int reservationId) {
        this.reservationId = reservationId;
    }

    public String getBillNumber() {
        return billNumber;
    }

    public void setBillNumber(String billNumber) {
        this.billNumber = billNumber;
    }

    public LocalDateTime getBillDate() {
        return billDate;
    }

    public void setBillDate(LocalDateTime billDate) {
        this.billDate = billDate;
    }

    public double getRoomCharges() {
        return roomCharges;
    }

    public void setRoomCharges(double roomCharges) {
        this.roomCharges = roomCharges;
    }

    public double getServiceCharges() {
        return serviceCharges;
    }

    public void setServiceCharges(double serviceCharges) {
        this.serviceCharges = serviceCharges;
    }

    public double getTaxAmount() {
        return taxAmount;
    }

    public void setTaxAmount(double taxAmount) {
        this.taxAmount = taxAmount;
    }

    public double getDiscountAmount() {
        return discountAmount;
    }

    public void setDiscountAmount(double discountAmount) {
        this.discountAmount = discountAmount;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public double getAmountPaid() {
        return amountPaid;
    }

    public void setAmountPaid(double amountPaid) {
        this.amountPaid = amountPaid;
    }

    public double getBalanceDue() {
        return balanceDue;
    }

    public void setBalanceDue(double balanceDue) {
        this.balanceDue = balanceDue;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public List<BillItem> getAdditionalCharges() {
        return additionalCharges;
    }

    public void setAdditionalCharges(List<BillItem> additionalCharges) {
        this.additionalCharges = additionalCharges;
    }
}