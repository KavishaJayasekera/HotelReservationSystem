// package main.java.com.hotel.view;

// import main.java.com.hotel.model.User;
// import javax.swing.*;
// import java.awt.*;

// public class MainDashboardFra extends JPanel {
//     private User currentUser;
//     private JTextField guestNameField;
//     private JTextField contactField;
//     private JTextField emailField;
//     private JTextField checkInField;
//     private JTextField checkOutField;
//     private JComboBox<String> roomTypeCombo;
//     private JTextField guestsField;
//     private JTextArea specialRequestsArea;

//     public MainDashboardFra(User user) {
//         this.currentUser = user;
//         initComponents();
//     }

//     private void initComponents() {
//         setLayout(new BorderLayout());
//         setBackground(Color.WHITE);

//         // Create title panel
//         JPanel titlePanel = new JPanel();
//         titlePanel.setBackground(new Color(70, 130, 180));
//         JLabel titleLabel = new JLabel("NEW RESERVATION");
//         titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
//         titleLabel.setForeground(Color.WHITE);
//         titlePanel.add(titleLabel);
//         add(titlePanel, BorderLayout.NORTH);

//         // Create main form panel
//         JPanel formPanel = new JPanel(new GridBagLayout());
//         formPanel.setBackground(Color.WHITE);
//         formPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

//         GridBagConstraints gbc = new GridBagConstraints();
//         gbc.insets = new Insets(5, 5, 5, 5);
//         gbc.fill = GridBagConstraints.HORIZONTAL;

//         // Guest Name
//         gbc.gridx = 0;
//         gbc.gridy = 0;
//         formPanel.add(createStyledLabel("Guest Name:"), gbc);
//         gbc.gridx = 1;
//         guestNameField = createStyledTextField(20);
//         formPanel.add(guestNameField, gbc);

//         // Contact Number
//         gbc.gridx = 0;
//         gbc.gridy = 1;
//         formPanel.add(createStyledLabel("Contact Number:"), gbc);
//         gbc.gridx = 1;
//         contactField = createStyledTextField(20);
//         formPanel.add(contactField, gbc);

//         // Email
//         gbc.gridx = 0;
//         gbc.gridy = 2;
//         formPanel.add(createStyledLabel("Email:"), gbc);
//         gbc.gridx = 1;
//         emailField = createStyledTextField(20);
//         formPanel.add(emailField, gbc);

//         // Check-in Date
//         gbc.gridx = 0;
//         gbc.gridy = 3;
//         formPanel.add(createStyledLabel("Check-in Date (YYYY-MM-DD):"), gbc);
//         gbc.gridx = 1;
//         checkInField = createStyledTextField(20);
//         formPanel.add(checkInField, gbc);

//         // Check-out Date
//         gbc.gridx = 0;
//         gbc.gridy = 4;
//         formPanel.add(createStyledLabel("Check-out Date (YYYY-MM-DD):"), gbc);
//         gbc.gridx = 1;
//         checkOutField = createStyledTextField(20);
//         formPanel.add(checkOutField, gbc);

//         // Room Type
//         gbc.gridx = 0;
//         gbc.gridy = 5;
//         formPanel.add(createStyledLabel("Room Type:"), gbc);
//         gbc.gridx = 1;
//         String[] roomTypes = { "Standard", "Deluxe", "Suite", "Family", "Presidential" };
//         roomTypeCombo = new JComboBox<>(roomTypes);
//         roomTypeCombo.setFont(new Font("Arial", Font.PLAIN, 14));
//         formPanel.add(roomTypeCombo, gbc);

//         // Number of Guests
//         gbc.gridx = 0;
//         gbc.gridy = 6;
//         formPanel.add(createStyledLabel("Number of Guests:"), gbc);
//         gbc.gridx = 1;
//         guestsField = createStyledTextField(20);
//         formPanel.add(guestsField, gbc);

//         // Special Requests
//         gbc.gridx = 0;
//         gbc.gridy = 7;
//         formPanel.add(createStyledLabel("Special Requests:"), gbc);
//         gbc.gridx = 1;
//         specialRequestsArea = new JTextArea(3, 20);
//         specialRequestsArea.setFont(new Font("Arial", Font.PLAIN, 14));
//         specialRequestsArea.setLineWrap(true);
//         JScrollPane scrollPane = new JScrollPane(specialRequestsArea);
//         formPanel.add(scrollPane, gbc);

//         // Buttons
//         gbc.gridx = 0;
//         gbc.gridy = 8;
//         gbc.gridwidth = 2;
//         JPanel buttonPanel = new JPanel(new FlowLayout());
//         buttonPanel.setBackground(Color.WHITE);

//         JButton saveButton = createStyledButton("Save Reservation", new Color(0, 153, 76));
//         JButton clearButton = createStyledButton("Clear", new Color(204, 0, 0));
//         JButton searchButton = createStyledButton("Check Availability", new Color(70, 130, 180));

//         saveButton.addActionListener(e -> saveReservation());
//         clearButton.addActionListener(e -> clearForm());
//         searchButton.addActionListener(e -> checkAvailability());

//         buttonPanel.add(saveButton);
//         buttonPanel.add(clearButton);
//         buttonPanel.add(searchButton);

//         formPanel.add(buttonPanel, gbc);

//         add(new JScrollPane(formPanel), BorderLayout.CENTER);

//         // Add status panel
//         JPanel statusPanel = new JPanel();
//         statusPanel.setBackground(new Color(240, 240, 240));
//         statusPanel.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
//         JLabel statusLabel = new JLabel(
//                 "Logged in as: " + currentUser.getUsername() + " (" + currentUser.getRole() + ")");
//         statusLabel.setFont(new Font("Arial", Font.ITALIC, 12));
//         statusPanel.add(statusLabel);
//         add(statusPanel, BorderLayout.SOUTH);
//     }

//     private JLabel createStyledLabel(String text) {
//         JLabel label = new JLabel(text);
//         label.setFont(new Font("Arial", Font.BOLD, 14));
//         return label;
//     }

//     private JTextField createStyledTextField(int columns) {
//         JTextField field = new JTextField(columns);
//         field.setFont(new Font("Arial", Font.PLAIN, 14));
//         return field;
//     }

//     private JButton createStyledButton(String text, Color bgColor) {
//         JButton button = new JButton(text);
//         button.setFont(new Font("Arial", Font.BOLD, 14));
//         button.setForeground(Color.WHITE);
//         button.setBackground(bgColor);
//         button.setFocusPainted(false);
//         button.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
//         return button;
//     }

//     private void saveReservation() {
//         // Validate fields
//         if (guestNameField.getText().trim().isEmpty() ||
//                 contactField.getText().trim().isEmpty()) {
//             JOptionPane.showMessageDialog(this,
//                     "Please fill in all required fields!",
//                     "Validation Error",
//                     JOptionPane.ERROR_MESSAGE);
//             return;
//         }

//         // Here you would save to database
//         JOptionPane.showMessageDialog(this,
//                 "Reservation saved successfully!\nReservation Number: RES" + System.currentTimeMillis(),
//                 "Success",
//                 JOptionPane.INFORMATION_MESSAGE);
//     }

//     private void clearForm() {
//         guestNameField.setText("");
//         contactField.setText("");
//         emailField.setText("");
//         checkInField.setText("");
//         checkOutField.setText("");
//         guestsField.setText("");
//         specialRequestsArea.setText("");
//         roomTypeCombo.setSelectedIndex(0);
//     }

//     private void checkAvailability() {
//         // Here you would check room availability from database
//         JOptionPane.showMessageDialog(this,
//                 "Available rooms found!\nStandard: 5\nDeluxe: 3\nSuite: 2",
//                 "Room Availability",
//                 JOptionPane.INFORMATION_MESSAGE);
//     }
// }

package main.java.com.hotel.view;

import com.hotel.model.User;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MainDashboardFrame extends JFrame {
    private User currentUser;
    private JPanel cardPanel;
    private CardLayout cardLayout;
    private JLabel statusLabel;

    public MainDashboardFrame(User user) {
        this.currentUser = user;
        initComponents();
    }

    private void initComponents() {
        setTitle("Hotel Reservation System - Dashboard");
        setSize(1000, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Menu Bar
        JMenuBar menuBar = new JMenuBar();

        JMenu fileMenu = new JMenu("File");
        JMenuItem logoutItem = new JMenuItem("Logout");
        JMenuItem exitItem = new JMenuItem("Exit");
        logoutItem.addActionListener(e -> logout());
        exitItem.addActionListener(e -> System.exit(0));
        fileMenu.add(logoutItem);
        fileMenu.addSeparator();
        fileMenu.add(exitItem);

        JMenu helpMenu = new JMenu("Help");
        JMenuItem helpItem = new JMenuItem("User Guide");
        JMenuItem aboutItem = new JMenuItem("About");
        helpItem.addActionListener(e -> showHelp());
        aboutItem.addActionListener(e -> showAbout());
        helpMenu.add(helpItem);
        helpMenu.add(aboutItem);

        menuBar.add(fileMenu);
        menuBar.add(helpMenu);
        setJMenuBar(menuBar);

        // Main Split Pane
        JSplitPane splitPane = new JSplitPane();
        splitPane.setDividerLocation(250);
        splitPane.setDividerSize(5);

        // Left Panel - Menu
        JPanel menuPanel = createMenuPanel();
        splitPane.setLeftComponent(menuPanel);

        // Right Panel - Card Layout for all pages
        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);
        cardPanel.setBackground(Color.WHITE);

        // ADD ALL PAGES TO CARD LAYOUT
        try {
            cardPanel.add(new ReservationPanel(currentUser), "RESERVATION");
            cardPanel.add(new GuestPanel(currentUser), "GUEST");
            cardPanel.add(new RoomPanel(currentUser), "ROOM");
            cardPanel.add(new BillingPanel(currentUser), "BILLING");
            cardPanel.add(new ReportPanel(currentUser), "REPORT");

            // Add a welcome panel as default
            JPanel welcomePanel = createWelcomePanel();
            cardPanel.add(welcomePanel, "WELCOME");

            // Show welcome panel first
            cardLayout.show(cardPanel, "WELCOME");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,
                    "Error loading panels: " + e.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }

        splitPane.setRightComponent(cardPanel);

        // Status Bar
        JPanel statusBar = new JPanel(new FlowLayout(FlowLayout.LEFT));
        statusBar.setBackground(new Color(240, 240, 240));
        statusBar.setBorder(BorderFactory.createEtchedBorder());
        User user = null;
        statusLabel = new JLabel(" Logged in as: " + user.getUsername() + " (" + user.getRole() + ") | Date: " +
                java.time.LocalDate.now());
        statusLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        statusBar.add(statusLabel);

        // Add everything to frame
        add(splitPane, BorderLayout.CENTER);
        add(statusBar, BorderLayout.SOUTH);
    }

    private JPanel createMenuPanel() {
        JPanel panel = new JPanel();
        panel.setBackground(new Color(45, 45, 45));
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        // Header
        JLabel headerLabel = new JLabel("HOTEL SYSTEM");
        headerLabel.setFont(new Font("Arial", Font.BOLD, 18));
        headerLabel.setForeground(Color.WHITE);
        headerLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        headerLabel.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
        panel.add(headerLabel);

        // User info
        JLabel userLabel = new JLabel("👤 " + currentUser.getUsername());
        userLabel.setFont(new Font("Arial", Font.BOLD, 14));
        userLabel.setForeground(new Color(200, 200, 200));
        userLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(userLabel);

        JLabel roleLabel = new JLabel("(" + currentUser.getRole() + ")");
        roleLabel.setFont(new Font("Arial", Font.ITALIC, 12));
        roleLabel.setForeground(new Color(150, 150, 150));
        roleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        roleLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 20, 0));
        panel.add(roleLabel);

        panel.add(Box.createRigidArea(new Dimension(0, 20)));

        // Menu Buttons
        String[][] menuItems = {
                { "🏠", "Dashboard", "WELCOME" },
                { "📅", "New Reservation", "RESERVATION" },
                { "👥", "Manage Guests", "GUEST" },
                { "🛏️", "Manage Rooms", "ROOM" },
                { "💰", "Billing", "BILLING" },
                { "📊", "Reports", "REPORT" }
        };

        for (String[] item : menuItems) {
            JButton button = new JButton(item[0] + "  " + item[1]);
            button.setFont(new Font("Arial", Font.PLAIN, 14));
            button.setForeground(Color.WHITE);
            button.setBackground(new Color(60, 60, 60));
            button.setFocusPainted(false);
            button.setBorderPainted(false);
            button.setAlignmentX(Component.CENTER_ALIGNMENT);
            button.setMaximumSize(new Dimension(220, 40));
            button.setPreferredSize(new Dimension(220, 40));

            button.addActionListener(e -> {
                cardLayout.show(cardPanel, item[2]);
                updateStatus("Viewing: " + item[1]);
            });

            button.addMouseListener(new MouseAdapter() {
                public void mouseEntered(MouseEvent e) {
                    button.setBackground(new Color(80, 80, 80));
                }

                public void mouseExited(MouseEvent e) {
                    button.setBackground(new Color(60, 60, 60));
                }
            });

            panel.add(button);
            panel.add(Box.createRigidArea(new Dimension(0, 10)));
        }

        panel.add(Box.createVerticalGlue());

        // Logout button at bottom
        JButton logoutBtn = new JButton("🚪  Logout");
        logoutBtn.setFont(new Font("Arial", Font.BOLD, 14));
        logoutBtn.setForeground(Color.WHITE);
        logoutBtn.setBackground(new Color(180, 0, 0));
        logoutBtn.setFocusPainted(false);
        logoutBtn.setBorderPainted(false);
        logoutBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        logoutBtn.setMaximumSize(new Dimension(220, 40));
        logoutBtn.addActionListener(e -> logout());
        panel.add(logoutBtn);
        panel.add(Box.createRigidArea(new Dimension(0, 20)));

        return panel;
    }

    private JPanel createWelcomePanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(Color.WHITE);

        // Welcome message
        JLabel welcomeLabel = new JLabel("Welcome to Hotel Reservation System", SwingConstants.CENTER);
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 28));
        welcomeLabel.setForeground(new Color(70, 130, 180));
        welcomeLabel.setBorder(BorderFactory.createEmptyBorder(50, 0, 20, 0));

        JLabel userWelcome = new JLabel("Hello, " + currentUser.getUsername() + "!", SwingConstants.CENTER);
        userWelcome.setFont(new Font("Arial", Font.PLAIN, 18));

        // Info panel
        JPanel infoPanel = new JPanel(new GridLayout(3, 2, 20, 20));
        infoPanel.setBackground(Color.WHITE);
        infoPanel.setBorder(BorderFactory.createEmptyBorder(50, 100, 50, 100));

        String[][] stats = {
                { "Total Rooms:", "50" },
                { "Available Rooms:", "15" },
                { "Current Guests:", "35" },
                { "Today's Check-ins:", "8" },
                { "Today's Check-outs:", "5" },
                { "Today's Revenue:", "Rs. 125,000" }
        };

        for (String[] stat : stats) {
            JLabel label1 = new JLabel(stat[0]);
            label1.setFont(new Font("Arial", Font.BOLD, 14));
            JLabel label2 = new JLabel(stat[1]);
            label2.setFont(new Font("Arial", Font.PLAIN, 14));
            label2.setForeground(new Color(70, 130, 180));
            infoPanel.add(label1);
            infoPanel.add(label2);
        }

        JLabel instruction = new JLabel("Select an option from the left menu to begin", SwingConstants.CENTER);
        instruction.setFont(new Font("Arial", Font.ITALIC, 14));
        instruction.setForeground(Color.GRAY);

        panel.add(welcomeLabel, BorderLayout.NORTH);
        panel.add(userWelcome, BorderLayout.CENTER);
        panel.add(infoPanel, BorderLayout.CENTER);
        panel.add(instruction, BorderLayout.SOUTH);

        return panel;
    }

    private void updateStatus(String message) {
        statusLabel.setText(" " + message + " | Logged in as: " + currentUser.getUsername() +
                " (" + currentUser.getRole() + ") | Date: " + java.time.LocalDate.now());
    }

    private void logout() {
        int confirm = JOptionPane.showConfirmDialog(this,
                "Are you sure you want to logout?",
                "Confirm Logout",
                JOptionPane.YES_NO_OPTION);

        if (confirm == JOptionPane.YES_OPTION) {
            new LoginFrame().setVisible(true);
            dispose();
        }
    }

    private void showHelp() {
        JOptionPane.showMessageDialog(this,
                "HOTEL RESERVATION SYSTEM - USER GUIDE\n\n" +
                        "1. New Reservation: Create a new booking\n" +
                        "2. Manage Guests: Add/edit guest information\n" +
                        "3. Manage Rooms: View/update room status\n" +
                        "4. Billing: Calculate and process payments\n" +
                        "5. Reports: View various reports\n\n" +
                        "For assistance, contact IT Support.",
                "Help",
                JOptionPane.INFORMATION_MESSAGE);
    }

    private void showAbout() {
        JOptionPane.showMessageDialog(this,
                "Hotel Reservation System\n" +
                        "Version 2.0\n\n" +
                        "Advanced Programming Assignment\n" +
                        "© 2026 All Rights Reserved",
                "About",
                JOptionPane.INFORMATION_MESSAGE);
    }
}