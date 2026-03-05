package com.hotel.ui;

import javax.swing.*;
import java.awt.*;

public class DashboardUI {

    public static void showDashboard(String user) {

        JFrame frame = new JFrame("Dashboard");
        frame.setSize(500, 450);
        frame.setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(7, 1, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        panel.setBackground(new Color(240, 248, 255));

        JLabel welcome = new JLabel("Welcome " + user, JLabel.CENTER);
        welcome.setFont(new Font("Arial", Font.BOLD, 22));

        JButton book = new JButton("Book Room");
        JButton view = new JButton("View Reservations");
        JButton availability = new JButton("Check Room Availability");
        JButton bill = new JButton("Generate Bill");
        JButton help = new JButton("Help");
        JButton exit = new JButton("Exit");

        panel.add(welcome);
        panel.add(book);
        panel.add(view);
        panel.add(availability);
        panel.add(bill);
        panel.add(help);
        panel.add(exit);

        frame.add(panel);
        frame.setVisible(true);

        book.addActionListener(e -> BookRoomUI.show());
        view.addActionListener(e -> ViewReservationsUI.show());
        availability.addActionListener(e -> AvailabilityUI.show());
        bill.addActionListener(e -> BillUI.show());
        help.addActionListener(e -> HelpUI.show());

        exit.addActionListener(e -> System.exit(0));

    }

}