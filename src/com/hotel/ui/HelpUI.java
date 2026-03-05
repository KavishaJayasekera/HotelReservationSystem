package com.hotel.ui;

import javax.swing.*;

public class HelpUI {

    public static void show() {

        JFrame frame = new JFrame("Help Guide");
        frame.setSize(450, 300);
        frame.setLocationRelativeTo(null);

        JTextArea help = new JTextArea();

        help.setText(
                "Hotel Reservation System Guide\n\n" +
                        "1. Login using admin account\n" +
                        "2. Book rooms for customers\n" +
                        "3. View reservations\n" +
                        "4. Check room availability\n" +
                        "5. Generate customer bill\n" +
                        "6. Exit system safely");

        help.setEditable(false);

        frame.add(new JScrollPane(help));
        frame.setVisible(true);

    }

}