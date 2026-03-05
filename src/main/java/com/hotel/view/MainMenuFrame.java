package main.java.com.hotel.view;

import javax.swing.*;
import java.awt.*;

public class MainMenuFrame extends JFrame {
    private User currentUser;

    public MainMenuFrame(User user) {
        this.currentUser = user;
        initComponents();
    }

    private void initComponents() {
        setTitle("Hotel Reservation System - Main Menu");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Welcome panel
        JPanel welcomePanel = new JPanel();
        welcomePanel.setBackground(new Color(0, 102, 204));
        welcomePanel.setPreferredSize(new Dimension(800, 60));

        JLabel welcomeLabel = new JLabel("Welcome, " + currentUser.getUsername() + " (" + currentUser.getRole() + ")");
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 18));
        welcomeLabel.setForeground(Color.WHITE);
        welcomePanel.add(welcomeLabel);

        add(welcomePanel, BorderLayout.NORTH);

        // Menu panel
        JPanel menuPanel = new JPanel(new GridBagLayout());
        menuPanel.setBackground(Color.WHITE);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Menu buttons
        String[] menuItems = {
                "Add New Reservation",
                "Display Reservation Details",
                "Calculate and Print Bill",
                "View Available Rooms",
                "Help Section",
                "Logout"
        };

        for (int i = 0; i < menuItems.length; i++) {
            JButton button = createMenuButton(menuItems[i]);
            gbc.gridx = 0;
            gbc.gridy = i;
            menuPanel.add(button, gbc);
        }

        add(new JScrollPane(menuPanel), BorderLayout.CENTER);

        setSize(500, 500);
        setLocationRelativeTo(null);
    }

    private JButton createMenuButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.PLAIN, 14));
        button.setPreferredSize(new Dimension(250, 40));
        button.setBackground(new Color(240, 248, 255));
        button.setFocusPainted(false);

        button.addActionListener(e -> handleMenuAction(text));

        return button;
    }

    private void handleMenuAction(String action) {
        switch (action) {
            case "Add New Reservation":
                JOptionPane.showMessageDialog(this, "Add Reservation feature coming soon!");
                break;
            case "Display Reservation Details":
                JOptionPane.showMessageDialog(this, "Display Reservation feature coming soon!");
                break;
            case "Calculate and Print Bill":
                JOptionPane.showMessageDialog(this, "Calculate Bill feature coming soon!");
                break;
            case "View Available Rooms":
                showAvailableRooms();
                break;
            case "Help Section":
                showHelp();
                break;
            case "Logout":
                logout();
                break;
        }
    }

    private void showAvailableRooms() {
        String rooms = """
                AVAILABLE ROOMS
                ------------------------
                Room 101 - Single   - $75/night
                Room 102 - Single   - $75/night
                Room 201 - Double   - $120/night
                Room 202 - Double   - $120/night
                Room 301 - Suite    - $200/night
                Room 401 - Deluxe   - $150/night
                """;

        JTextArea textArea = new JTextArea(rooms);
        textArea.setEditable(false);
        textArea.setFont(new Font("Monospaced", Font.PLAIN, 12));
        JOptionPane.showMessageDialog(this, new JScrollPane(textArea), "Available Rooms",
                JOptionPane.INFORMATION_MESSAGE);
    }

    private void showHelp() {
        String helpText = """
                HOTEL RESERVATION SYSTEM - HELP

                1. Add New Reservation: Create a new booking for a guest
                2. Display Reservation: View details of existing reservation
                3. Calculate Bill: Generate invoice for stay
                4. View Available Rooms: Check room availability
                5. Help Section: This help menu
                6. Logout: Return to login screen

                For assistance, contact system administrator.
                """;

        JTextArea textArea = new JTextArea(helpText);
        textArea.setEditable(false);
        textArea.setFont(new Font("Monospaced", Font.PLAIN, 12));
        JOptionPane.showMessageDialog(this, new JScrollPane(textArea), "Help", JOptionPane.INFORMATION_MESSAGE);
    }

    private void logout() {
        int confirm = JOptionPane.showConfirmDialog(this,
                "Are you sure you want to logout?",
                "Confirm Logout",
                JOptionPane.YES_NO_OPTION);

        if (confirm == JOptionPane.YES_OPTION) {
            dispose();
            new LoginFrame().setVisible(true);
        }
    }
}