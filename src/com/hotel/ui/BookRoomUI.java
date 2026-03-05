package com.hotel.ui;

import javax.swing.*;
import java.awt.*;

public class BookRoomUI {

    public static void show() {

        JFrame frame = new JFrame("Book Room");
        frame.setSize(400, 350);
        frame.setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridLayout(6, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JTextField name = new JTextField();
        JComboBox room = new JComboBox(new String[] { "Single", "Double", "Suite" });
        JTextField nights = new JTextField();

        JButton book = new JButton("Confirm Booking");
        JButton back = new JButton("Back");

        panel.add(new JLabel("Customer Name"));
        panel.add(name);

        panel.add(new JLabel("Room Type"));
        panel.add(room);

        panel.add(new JLabel("Number of Nights"));
        panel.add(nights);

        panel.add(book);
        panel.add(back);

        frame.add(panel);
        frame.setVisible(true);

        book.addActionListener(e -> {

            JOptionPane.showMessageDialog(frame, "Room booked successfully!");

        });

        back.addActionListener(e -> frame.dispose());

    }

}