// package main.java.com.hotel.view;

// import main.java.com.hotel.model.User;
// import javax.swing.*;
// import javax.swing.table.DefaultTableCellRenderer;
// import javax.swing.table.DefaultTableModel;
// import java.awt.*;

// public class RoomPanel extends JPanel {
//     private User currentUser;
//     private JTable roomTable;
//     private DefaultTableModel tableModel;
//     private JComboBox<String> roomTypeCombo;

//     public RoomPanel(User user) {
//         this.currentUser = user;
//         initComponents();
//         loadRoomData();
//     }

//     private void initComponents() {
//         setLayout(new BorderLayout());
//         setBackground(Color.WHITE);

//         // Title panel
//         JPanel titlePanel = new JPanel();
//         titlePanel.setBackground(new Color(70, 130, 180));
//         JLabel titleLabel = new JLabel("MANAGE ROOMS");
//         titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
//         titleLabel.setForeground(Color.WHITE);
//         titlePanel.add(titleLabel);
//         add(titlePanel, BorderLayout.NORTH);

//         // Filter panel
//         JPanel filterPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
//         filterPanel.setBackground(Color.WHITE);
//         filterPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

//         filterPanel.add(new JLabel("Filter by Type:"));
//         String[] roomTypes = { "All", "Standard", "Deluxe", "Suite", "Family", "Presidential" };
//         roomTypeCombo = new JComboBox<>(roomTypes);
//         roomTypeCombo.setFont(new Font("Arial", Font.PLAIN, 14));

//         JButton filterButton = createStyledButton("Apply Filter", new Color(70, 130, 180));
//         JButton refreshButton = createStyledButton("Refresh", new Color(0, 153, 76));
//         JButton addButton = createStyledButton("Add Room", new Color(0, 102, 204));

//         filterButton.addActionListener(e -> filterRooms());
//         refreshButton.addActionListener(e -> loadRoomData());
//         addButton.addActionListener(e -> addRoom());

//         filterPanel.add(roomTypeCombo);
//         filterPanel.add(filterButton);
//         filterPanel.add(refreshButton);
//         filterPanel.add(addButton);

//         // Table
//         String[] columns = { "Room No", "Type", "Rate/Night", "Capacity", "Status", "Description" };
//         tableModel = new DefaultTableModel(columns, 0) {
//             @Override
//             public boolean isCellEditable(int row, int column) {
//                 return false;
//             }
//         };

//         roomTable = new JTable(tableModel);
//         roomTable.setRowHeight(30);
//         roomTable.setFont(new Font("Arial", Font.PLAIN, 14));
//         roomTable.getTableHeader().setFont(new Font("Arial", Font.BOLD, 14));
//         roomTable.getTableHeader().setBackground(new Color(70, 130, 180));
//         roomTable.getTableHeader().setForeground(Color.WHITE);

//         // Set column widths
//         roomTable.getColumnModel().getColumn(0).setPreferredWidth(80);
//         roomTable.getColumnModel().getColumn(1).setPreferredWidth(100);
//         roomTable.getColumnModel().getColumn(2).setPreferredWidth(100);
//         roomTable.getColumnModel().getColumn(3).setPreferredWidth(80);
//         roomTable.getColumnModel().getColumn(4).setPreferredWidth(100);
//         roomTable.getColumnModel().getColumn(5).setPreferredWidth(200);

//         // Color coding for room status
//         roomTable.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
//             /**
//              * @param table
//              * @param value
//              * @param isSelected
//              * @param hasFocus
//              * @param row
//              * @param column
//              * @return
//              */
//             public Component getTableCellRendererComponent(JTable table, Object value,
//                     boolean isSelected, boolean hasFocus, int row, int column) {
//                 Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
//                 if (!isSelected) {
//                     String status = (String) tableModel.getValueAt(row, 4);
//                     if ("Available".equals(status)) {
//                         c.setBackground(new Color(232, 245, 233)); // Light green
//                     } else if ("Occupied".equals(status)) {
//                         c.setBackground(new Color(255, 235, 238)); // Light red
//                     } else if ("Maintenance".equals(status)) {
//                         c.setBackground(new Color(255, 243, 224)); // Light orange
//                     } else {
//                         c.setBackground(Color.WHITE);
//                     }
//                 }
//                 return c;
//             }
//         });

//         JScrollPane scrollPane = new JScrollPane(roomTable);
//         scrollPane.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200)));

//         // Button panel
//         JPanel buttonPanel = new JPanel(new FlowLayout());
//         buttonPanel.setBackground(Color.WHITE);
//         buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

//         JButton editButton = createStyledButton("Edit Room", new Color(255, 153, 0));
//         JButton deleteButton = createStyledButton("Delete Room", new Color(204, 0, 0));
//         JButton statusButton = createStyledButton("Change Status", new Color(70, 130, 180));
//         JButton viewButton = createStyledButton("View Details", new Color(0, 102, 204));

//         editButton.addActionListener(e -> editRoom());
//         deleteButton.addActionListener(e -> deleteRoom());
//         statusButton.addActionListener(e -> changeStatus());
//         viewButton.addActionListener(e -> viewRoomDetails());

//         buttonPanel.add(editButton);
//         buttonPanel.add(deleteButton);
//         buttonPanel.add(statusButton);
//         buttonPanel.add(viewButton);

//         // Status panel
//         JPanel statusPanel = new JPanel();
//         statusPanel.setBackground(new Color(240, 240, 240));
//         statusPanel.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
//         JLabel statusLabel = new JLabel(
//                 "Logged in as: " + currentUser.getUsername() + " (" + currentUser.getRole() + ")");
//         statusLabel.setFont(new Font("Arial", Font.ITALIC, 12));
//         statusPanel.add(statusLabel);

//         // Add components
//         JPanel topPanel = new JPanel(new BorderLayout());
//         topPanel.setBackground(Color.WHITE);
//         topPanel.add(filterPanel, BorderLayout.NORTH);

//         add(topPanel, BorderLayout.NORTH);
//         add(scrollPane, BorderLayout.CENTER);

//         JPanel bottomPanel = new JPanel(new BorderLayout());
//         bottomPanel.add(buttonPanel, BorderLayout.CENTER);
//         bottomPanel.add(statusPanel, BorderLayout.SOUTH);
//         add(bottomPanel, BorderLayout.SOUTH);
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

//     private void loadRoomData() {
//         tableModel.setRowCount(0);

//         // Sample data - In real application, this would come from database
//         tableModel.addRow(new Object[] { "101", "Standard", "Rs. 5,000", "2", "Available", "City view with AC" });
//         tableModel.addRow(new Object[] { "102", "Standard", "Rs. 5,000", "2", "Available", "Garden view" });
//         tableModel.addRow(new Object[] { "201", "Deluxe", "Rs. 8,000", "3", "Available", "Sea view with balcony" });
//         tableModel.addRow(new Object[] { "202", "Deluxe", "Rs. 8,000", "3", "Occupied", "Mountain view" });
//         tableModel.addRow(new Object[] { "301", "Suite", "Rs. 12,000", "4", "Available", "Living area + bedroom" });
//         tableModel.addRow(new Object[] { "302", "Suite", "Rs. 12,000", "4", "Maintenance", "Under renovation" });
//         tableModel.addRow(new Object[] { "401", "Family", "Rs. 15,000", "5", "Available", "2 bedrooms, kitchen" });
//         tableModel.addRow(
//                 new Object[] { "501", "Presidential", "Rs. 25,000", "6", "Available", "Luxury suite with jacuzzi" });
//     }

//     private void filterRooms() {
//         String selectedType = (String) roomTypeCombo.getSelectedItem();
//         if ("All".equals(selectedType)) {
//             loadRoomData();
//         } else {
//             // Filter rooms by type
//             DefaultTableModel filteredModel = new DefaultTableModel();
//             for (int i = 0; i < tableModel.getRowCount(); i++) {
//                 if (tableModel.getValueAt(i, 1).equals(selectedType)) {
//                     filteredModel.addRow(new Object[] {
//                             tableModel.getValueAt(i, 0),
//                             tableModel.getValueAt(i, 1),
//                             tableModel.getValueAt(i, 2),
//                             tableModel.getValueAt(i, 3),
//                             tableModel.getValueAt(i, 4),
//                             tableModel.getValueAt(i, 5)
//                     });
//                 }
//             }
//             roomTable.setModel(filteredModel);
//         }
//     }

//     private void addRoom() {
//         // Open dialog to add new room
//         JTextField roomNoField = new JTextField();
//         JComboBox<String> typeCombo = new JComboBox<>(
//                 new String[] { "Standard", "Deluxe", "Suite", "Family", "Presidential" });
//         JTextField rateField = new JTextField();
//         JTextField capacityField = new JTextField();
//         JTextField descField = new JTextField();

//         Object[] fields = {
//                 "Room Number:", roomNoField,
//                 "Room Type:", typeCombo,
//                 "Rate per Night:", rateField,
//                 "Capacity:", capacityField,
//                 "Description:", descField
//         };

//         int result = JOptionPane.showConfirmDialog(this, fields, "Add New Room",
//                 JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

//         if (result == JOptionPane.OK_OPTION) {
//             JOptionPane.showMessageDialog(this,
//                     "Room added successfully!",
//                     "Success",
//                     JOptionPane.INFORMATION_MESSAGE);
//             loadRoomData();
//         }
//     }

//     private void editRoom() {
//         int selectedRow = roomTable.getSelectedRow();
//         if (selectedRow == -1) {
//             JOptionPane.showMessageDialog(this,
//                     "Please select a room to edit!",
//                     "No Selection",
//                     JOptionPane.WARNING_MESSAGE);
//             return;
//         }

//         JOptionPane.showMessageDialog(this,
//                 "Edit room: " + tableModel.getValueAt(selectedRow, 0),
//                 "Edit Room",
//                 JOptionPane.INFORMATION_MESSAGE);
//     }

//     private void deleteRoom() {
//         int selectedRow = roomTable.getSelectedRow();
//         if (selectedRow == -1) {
//             JOptionPane.showMessageDialog(this,
//                     "Please select a room to delete!",
//                     "No Selection",
//                     JOptionPane.WARNING_MESSAGE);
//             return;
//         }

//         int confirm = JOptionPane.showConfirmDialog(this,
//                 "Are you sure you want to delete this room?",
//                 "Confirm Delete",
//                 JOptionPane.YES_NO_OPTION);

//         if (confirm == JOptionPane.YES_OPTION) {
//             tableModel.removeRow(selectedRow);
//             JOptionPane.showMessageDialog(this,
//                     "Room deleted successfully!",
//                     "Success",
//                     JOptionPane.INFORMATION_MESSAGE);
//         }
//     }

//     private void changeStatus() {
//         int selectedRow = roomTable.getSelectedRow();
//         if (selectedRow == -1) {
//             JOptionPane.showMessageDialog(this,
//                     "Please select a room!",
//                     "No Selection",
//                     JOptionPane.WARNING_MESSAGE);
//             return;
//         }

//         String[] statuses = { "Available", "Occupied", "Maintenance" };
//         String newStatus = (String) JOptionPane.showInputDialog(this,
//                 "Select new status:", "Change Room Status",
//                 JOptionPane.QUESTION_MESSAGE, null, statuses, statuses[0]);

//         if (newStatus != null) {
//             tableModel.setValueAt(newStatus, selectedRow, 4);
//             JOptionPane.showMessageDialog(this,
//                     "Room status updated to: " + newStatus,
//                     "Success",
//                     JOptionPane.INFORMATION_MESSAGE);
//         }
//     }

//     private void viewRoomDetails() {
//         int selectedRow = roomTable.getSelectedRow();
//         if (selectedRow == -1) {
//             JOptionPane.showMessageDialog(this,
//                     "Please select a room to view!",
//                     "No Selection",
//                     JOptionPane.WARNING_MESSAGE);
//             return;
//         }

//         String details = "Room Details:\n\n" +
//                 "Room Number: " + tableModel.getValueAt(selectedRow, 0) + "\n" +
//                 "Type: " + tableModel.getValueAt(selectedRow, 1) + "\n" +
//                 "Rate per Night: " + tableModel.getValueAt(selectedRow, 2) + "\n" +
//                 "Capacity: " + tableModel.getValueAt(selectedRow, 3) + " persons\n" +
//                 "Status: " + tableModel.getValueAt(selectedRow, 4) + "\n" +
//                 "Description: " + tableModel.getValueAt(selectedRow, 5);

//         JOptionPane.showMessageDialog(this, details, "Room Details", JOptionPane.INFORMATION_MESSAGE);
//     }
// }

package main.java.com.hotel.view;

import com.hotel.model.User;
import javax.swing.*;

public class RoomPanel extends JPanel {
    private User currentUser;

    public RoomPanel(com.hotel.model.User currentUser2) {
        // TODO Auto-generated constructor stub
    }
}