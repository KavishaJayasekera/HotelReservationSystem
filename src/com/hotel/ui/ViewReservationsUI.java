package com.hotel.ui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class ViewReservationsUI {

    public static void show() {

        JFrame frame = new JFrame("Reservations");
        frame.setSize(600, 400);
        frame.setLocationRelativeTo(null);

        String[] columns = { "ID", "Customer", "Room", "Check-in", "Check-out" };

        Object[][] data = {
                { "1", "John", "Single", "2026-03-10", "2026-03-12" },
                { "2", "Anna", "Suite", "2026-03-11", "2026-03-15" }
        };

        JTable table = new JTable(new DefaultTableModel(data, columns));

        frame.add(new JScrollPane(table));
        frame.setVisible(true);

    }

}