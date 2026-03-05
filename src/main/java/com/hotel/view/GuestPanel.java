// package main.java.com.hotel.view;

// import main.java.com.hotel.model.User;
// import javax.swing.*;
// import javax.swing.table.DefaultTableModel;
// import java.awt.*;

// public class GuestPanel extends JPanel {
//     private User currentUser;
//     private JTable guestTable;
//     private DefaultTableModel tableModel;
//     private JTextField searchField;

//     public GuestPanel(User user) {
//         this.currentUser = user;
//         initComponents();
//         loadGuestData();
//     }

//     private void initComponents() {
//         setLayout(new BorderLayout());
//         setBackground(Color.WHITE);

//         // Title panel
//         JPanel titlePanel = new JPanel();
//         titlePanel.setBackground(new Color(70, 130, 180));
//         JLabel titleLabel = new JLabel("MANAGE GUESTS");
//         titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
//         titleLabel.setForeground(Color.WHITE);
//         titlePanel.add(titleLabel);
//         add(titlePanel, BorderLayout.NORTH);

//         // Search panel
//         JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
//         searchPanel.setBackground(Color.WHITE);
//         searchPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

//         searchPanel.add(new JLabel("Search:"));
//         searchField = new JTextField(20);
//         searchField.setFont(new Font("Arial", Font.PLAIN, 14));
//         JButton searchButton = createStyledButton("Search", new Color(70, 130, 180));
//         JButton addButton = createStyledButton("Add New Guest", new Color(0, 153, 76));

//         searchButton.addActionListener(e -> searchGuests());
//         addButton.addActionListener(e -> addNewGuest());

//         searchPanel.add(searchField);
//         searchPanel.add(searchButton);
//         searchPanel.add(addButton);

//         // Table
//         String[] columns = { "Guest ID", "Name", "Contact", "Email", "Address", "ID Proof" };
//         tableModel = new DefaultTableModel(columns, 0) {
//             @Override
//             public boolean isCellEditable(int row, int column) {
//                 return false;
//             }
//         };

//         guestTable = new JTable(tableModel);
//         guestTable.setRowHeight(30);
//         guestTable.setFont(new Font("Arial", Font.PLAIN, 14));
//         guestTable.getTableHeader().setFont(new Font("Arial", Font.BOLD, 14));
//         guestTable.getTableHeader().setBackground(new Color(70, 130, 180));
//         guestTable.getTableHeader().setForeground(Color.WHITE);
//         guestTable.setSelectionBackground(new Color(135, 206, 250));

//         JScrollPane scrollPane = new JScrollPane(guestTable);
//         scrollPane.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200)));

//         // Button panel
//         JPanel buttonPanel = new JPanel(new FlowLayout());
//         buttonPanel.setBackground(Color.WHITE);
//         buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

//         JButton editButton = createStyledButton("Edit Guest", new Color(255, 153, 0));
//         JButton deleteButton = createStyledButton("Delete Guest", new Color(204, 0, 0));
//         JButton refreshButton = createStyledButton("Refresh", new Color(70, 130, 180));
//         JButton viewButton = createStyledButton("View Details", new Color(0, 102, 204));

//         editButton.addActionListener(e -> editGuest());
//         deleteButton.addActionListener(e -> deleteGuest());
//         refreshButton.addActionListener(e -> loadGuestData());
//         viewButton.addActionListener(e -> viewGuestDetails());

//         buttonPanel.add(editButton);
//         buttonPanel.add(deleteButton);
//         buttonPanel.add(viewButton);
//         buttonPanel.add(refreshButton);

//         // Add components
//         JPanel topPanel = new JPanel(new BorderLayout());
//         topPanel.setBackground(Color.WHITE);
//         topPanel.add(searchPanel, BorderLayout.NORTH);

//         add(topPanel, BorderLayout.NORTH);
//         add(scrollPane, BorderLayout.CENTER);
//         add(buttonPanel, BorderLayout.SOUTH);

//         // Status panel
//         JPanel statusPanel = new JPanel();
//         statusPanel.setBackground(new Color(240, 240, 240));
//         statusPanel.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
//         JLabel statusLabel = new JLabel("Logged in as: " + currentUser.getUsername() + " (" + currentUser.getRole()
//                 + ") | Total Guests: " + tableModel.getRowCount());
//         statusLabel.setFont(new Font("Arial", Font.ITALIC, 12));
//         statusPanel.add(statusLabel);
//         add(statusPanel, BorderLayout.SOUTH);
//     }

//     private JButton createStyledButton(String text, Color bgColor) {
//         JButton button = new JButton(text);
//         button.setFont(new Font("Arial", Font.BOLD, 12));
//         button.setForeground(Color.WHITE);
//         button.setBackground(bgColor);
//         button.setFocusPainted(false);
//         button.setBorder(BorderFactory.createEmptyBorder(8, 15, 8, 15));
//         return button;
//     }

//     private void loadGuestData() {
//         tableModel.setRowCount(0);

//         // Sample data - In real application, this would come from database
//         tableModel
//                 .addRow(new Object[] { "1", "John Doe", "0771234567", "john@email.com", "Colombo", "NIC: 901234567V" });
//         tableModel.addRow(
//                 new Object[] { "2", "Jane Smith", "0777654321", "jane@email.com", "Kandy", "Passport: P123456" });
//         tableModel.addRow(
//                 new Object[] { "3", "Robert Johnson", "0782345678", "robert@email.com", "Galle", "NIC: 880123456V" });
//         tableModel.addRow(new Object[] { "4", "Maria Garcia", "0779876543", "maria@email.com", "Negombo",
//                 "Driving License: B123456" });
//     }

//     private void searchGuests() {
//         String keyword = searchField.getText().trim().toLowerCase();
//         if (keyword.isEmpty()) {
//             loadGuestData();
//             return;
//         }

//         // Filter the table based on search keyword
//         // In real application, this would query the database
//         JOptionPane.showMessageDialog(this,
//                 "Searching for: " + keyword,
//                 "Search",
//                 JOptionPane.INFORMATION_MESSAGE);
//     }

//     private void addNewGuest() {
//         // Open dialog to add new guest
//         JTextField nameField = new JTextField();
//         JTextField contactField = new JTextField();
//         JTextField emailField = new JTextField();
//         JTextField addressField = new JTextField();
//         JTextField idProofField = new JTextField();

//         Object[] fields = {
//                 "Name:", nameField,
//                 "Contact:", contactField,
//                 "Email:", emailField,
//                 "Address:", addressField,
//                 "ID Proof:", idProofField
//         };

//         int result = JOptionPane.showConfirmDialog(this, fields, "Add New Guest",
//                 JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

//         if (result == JOptionPane.OK_OPTION) {
//             // Here you would save to database
//             JOptionPane.showMessageDialog(this,
//                     "Guest added successfully!",
//                     "Success",
//                     JOptionPane.INFORMATION_MESSAGE);
//             loadGuestData();
//         }
//     }

//     private void editGuest() {
//         int selectedRow = guestTable.getSelectedRow();
//         if (selectedRow == -1) {
//             JOptionPane.showMessageDialog(this,
//                     "Please select a guest to edit!",
//                     "No Selection",
//                     JOptionPane.WARNING_MESSAGE);
//             return;
//         }

//         // Here you would open edit dialog
//         JOptionPane.showMessageDialog(this,
//                 "Edit guest: " + tableModel.getValueAt(selectedRow, 1),
//                 "Edit Guest",
//                 JOptionPane.INFORMATION_MESSAGE);
//     }

//     private void deleteGuest() {
//         int selectedRow = guestTable.getSelectedRow();
//         if (selectedRow == -1) {
//             JOptionPane.showMessageDialog(this,
//                     "Please select a guest to delete!",
//                     "No Selection",
//                     JOptionPane.WARNING_MESSAGE);
//             return;
//         }

//         int confirm = JOptionPane.showConfirmDialog(this,
//                 "Are you sure you want to delete this guest?",
//                 "Confirm Delete",
//                 JOptionPane.YES_NO_OPTION);

//         if (confirm == JOptionPane.YES_OPTION) {
//             tableModel.removeRow(selectedRow);
//             JOptionPane.showMessageDialog(this,
//                     "Guest deleted successfully!",
//                     "Success",
//                     JOptionPane.INFORMATION_MESSAGE);
//         }
//     }

//     private void viewGuestDetails() {
//         int selectedRow = guestTable.getSelectedRow();
//         if (selectedRow == -1) {
//             JOptionPane.showMessageDialog(this,
//                     "Please select a guest to view!",
//                     "No Selection",
//                     JOptionPane.WARNING_MESSAGE);
//             return;
//         }

//         String details = "Guest Details:\n\n" +
//                 "ID: " + tableModel.getValueAt(selectedRow, 0) + "\n" +
//                 "Name: " + tableModel.getValueAt(selectedRow, 1) + "\n" +
//                 "Contact: " + tableModel.getValueAt(selectedRow, 2) + "\n" +
//                 "Email: " + tableModel.getValueAt(selectedRow, 3) + "\n" +
//                 "Address: " + tableModel.getValueAt(selectedRow, 4) + "\n" +
//                 "ID Proof: " + tableModel.getValueAt(selectedRow, 5);

//         JOptionPane.showMessageDialog(this, details, "Guest Details", JOptionPane.INFORMATION_MESSAGE);
//     }
// }

package main.java.com.hotel.view;

import com.hotel.model.User;
import javax.swing.*;

public class GuestPanel extends JPanel {
    private User currentUser;

    public GuestPanel(main.java.com.hotel.view.User currentUser2) {
        // TODO Auto-generated constructor stub
    }

    public GuestPanel(User currentUser2) {
        // TODO Auto-generated constructor stub
    }
}