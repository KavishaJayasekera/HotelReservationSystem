package com.hotel.ui;

import javax.swing.*;
import java.awt.*;
import com.hotel.dao.UserDAO;

public class LoginUI {

    public static void main(String[] args) {

        JFrame frame = new JFrame("Hotel Reservation System");
        frame.setSize(450, 300);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setBackground(new Color(30, 144, 255));
        panel.setLayout(new GridLayout(4, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel title = new JLabel("HOTEL LOGIN", JLabel.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 22));
        title.setForeground(Color.WHITE);

        JTextField username = new JTextField();
        JPasswordField password = new JPasswordField();

        JButton loginBtn = new JButton("Login");

        panel.add(new JLabel("Username"));
        panel.add(username);

        panel.add(new JLabel("Password"));
        panel.add(password);

        panel.add(new JLabel(""));
        panel.add(loginBtn);

        frame.add(title, BorderLayout.NORTH);
        frame.add(panel);
        frame.setVisible(true);

        loginBtn.addActionListener(e -> {

            String user = username.getText();
            String pass = new String(password.getPassword());

            UserDAO dao = new UserDAO();

            if (dao.authenticate(user, pass)) {

                JOptionPane.showMessageDialog(frame, "Login Success");
                frame.dispose();
                DashboardUI.showDashboard(user);

            } else {

                JOptionPane.showMessageDialog(frame, "Invalid Login");

            }

        });

    }
}