package com.hotel.ui;

import javax.swing.*;
import java.awt.*;

public class CustomerUI {

    public CustomerUI() {

        JFrame frame = new JFrame("Customer Details");
        frame.setSize(400, 300);
        frame.setLocationRelativeTo(null);

        JTextArea area = new JTextArea();

        area.setText(
                "Customer List\n\n" +
                        "1. John - Room 101\n" +
                        "2. David - Room 203\n" +
                        "3. Sarah - Room 305");

        JButton back = new JButton("Back");

        frame.add(new JScrollPane(area), BorderLayout.CENTER);
        frame.add(back, BorderLayout.SOUTH);

        frame.setVisible(true);

        back.addActionListener(e -> frame.dispose());

    }
}