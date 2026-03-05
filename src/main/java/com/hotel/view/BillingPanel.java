// package main.java.com.hotel.view;

// import main.java.com.hotel.model.User;
// import javax.swing.*;
// import javax.swing.border.TitledBorder;
// import javax.swing.table.DefaultTableCellRenderer;
// import javax.swing.table.DefaultTableModel;
// import java.awt.*;
// import java.time.LocalDate;
// import java.time.format.DateTimeFormatter;

// public class BillingPanel extends JPanel {
//     private User currentUser;
//     private JTable billTable;
//     private DefaultTableModel tableModel;
//     private JTextField reservationField;
//     private JTextField guestNameField;
//     private JTextField roomTypeField;
//     private JTextField checkInField;
//     private JTextField checkOutField;
//     private JTextField nightsField;
//     private JTextField roomRateField;
//     private JTextField totalAmountField;
//     private JTextField taxField;
//     private JTextField discountField;
//     private JTextField grandTotalField;
//     private JComboBox<String> paymentMethodCombo;

//     public BillingPanel(User user) {
//         this.currentUser = user;
//         initComponents();
//         loadBillingData();
//     }

//     private void initComponents() {
//         setLayout(new BorderLayout());
//         setBackground(Color.WHITE);

//         // Title panel
//         JPanel titlePanel = new JPanel();
//         titlePanel.setBackground(new Color(70, 130, 180));
//         JLabel titleLabel = new JLabel("BILLING & PAYMENT");
//         titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
//         titleLabel.setForeground(Color.WHITE);
//         titlePanel.add(titleLabel);
//         add(titlePanel, BorderLayout.NORTH);

//         // Main content panel with split
//         JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
//         splitPane.setDividerLocation(350);
//         splitPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

//         // Top panel - Bill calculation
//         JPanel billPanel = createBillPanel();
//         splitPane.setTopComponent(billPanel);

//         // Bottom panel - Recent bills table
//         JPanel recentBillsPanel = createRecentBillsPanel();
//         splitPane.setBottomComponent(recentBillsPanel);

//         add(splitPane, BorderLayout.CENTER);

//         // Status panel
//         JPanel statusPanel = new JPanel();
//         statusPanel.setBackground(new Color(240, 240, 240));
//         statusPanel.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
//         JLabel statusLabel = new JLabel("Logged in as: " + currentUser.getUsername() + " (" + currentUser.getRole()
//                 + ") | Today: " + LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
//         statusLabel.setFont(new Font("Arial", Font.ITALIC, 12));
//         statusPanel.add(statusLabel);
//         add(statusPanel, BorderLayout.SOUTH);
//     }

//     private JPanel createBillPanel() {
//         JPanel panel = new JPanel(new BorderLayout());
//         panel.setBackground(Color.WHITE);
//         panel.setBorder(BorderFactory.createTitledBorder(
//                 BorderFactory.createLineBorder(new Color(70, 130, 180)),
//                 "Bill Calculation",
//                 TitledBorder.LEFT,
//                 TitledBorder.TOP,
//                 new Font("Arial", Font.BOLD, 14),
//                 new Color(70, 130, 180)));

//         // Search panel
//         JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
//         searchPanel.setBackground(Color.WHITE);
//         searchPanel.add(new JLabel("Reservation Number:"));
//         reservationField = new JTextField(15);
//         reservationField.setFont(new Font("Arial", Font.PLAIN, 14));
//         JButton searchButton = createStyledButton("Search", new Color(70, 130, 180));
//         JButton calculateButton = createStyledButton("Calculate Bill", new Color(0, 153, 76));

//         searchButton.addActionListener(e -> searchReservation());
//         calculateButton.addActionListener(e -> calculateBill());

//         searchPanel.add(reservationField);
//         searchPanel.add(searchButton);
//         searchPanel.add(calculateButton);

//         // Bill details panel
//         JPanel detailsPanel = new JPanel(new GridBagLayout());
//         detailsPanel.setBackground(Color.WHITE);
//         GridBagConstraints gbc = new GridBagConstraints();
//         gbc.insets = new Insets(5, 10, 5, 10);
//         gbc.fill = GridBagConstraints.HORIZONTAL;

//         // Row 1: Guest Name and Room Type
//         gbc.gridx = 0;
//         gbc.gridy = 0;
//         detailsPanel.add(createStyledLabel("Guest Name:"), gbc);
//         gbc.gridx = 1;
//         guestNameField = createStyledTextField(15);
//         guestNameField.setEditable(false);
//         detailsPanel.add(guestNameField, gbc);

//         gbc.gridx = 2;
//         detailsPanel.add(createStyledLabel("Room Type:"), gbc);
//         gbc.gridx = 3;
//         roomTypeField = createStyledTextField(15);
//         roomTypeField.setEditable(false);
//         detailsPanel.add(roomTypeField, gbc);

//         // Row 2: Check-in and Check-out
//         gbc.gridx = 0;
//         gbc.gridy = 1;
//         detailsPanel.add(createStyledLabel("Check-in Date:"), gbc);
//         gbc.gridx = 1;
//         checkInField = createStyledTextField(15);
//         checkInField.setEditable(false);
//         detailsPanel.add(checkInField, gbc);

//         gbc.gridx = 2;
//         detailsPanel.add(createStyledLabel("Check-out Date:"), gbc);
//         gbc.gridx = 3;
//         checkOutField = createStyledTextField(15);
//         checkOutField.setEditable(false);
//         detailsPanel.add(checkOutField, gbc);

//         // Row 3: Nights and Room Rate
//         gbc.gridx = 0;
//         gbc.gridy = 2;
//         detailsPanel.add(createStyledLabel("Nights:"), gbc);
//         gbc.gridx = 1;
//         nightsField = createStyledTextField(15);
//         nightsField.setEditable(false);
//         detailsPanel.add(nightsField, gbc);

//         gbc.gridx = 2;
//         detailsPanel.add(createStyledLabel("Room Rate/Night:"), gbc);
//         gbc.gridx = 3;
//         roomRateField = createStyledTextField(15);
//         roomRateField.setEditable(false);
//         detailsPanel.add(roomRateField, gbc);

//         // Row 4: Total Amount
//         gbc.gridx = 0;
//         gbc.gridy = 3;
//         detailsPanel.add(createStyledLabel("Total Amount:"), gbc);
//         gbc.gridx = 1;
//         totalAmountField = createStyledTextField(15);
//         totalAmountField.setEditable(false);
//         totalAmountField.setForeground(new Color(0, 102, 204));
//         totalAmountField.setFont(new Font("Arial", Font.BOLD, 14));
//         detailsPanel.add(totalAmountField, gbc);

//         // Row 5: Tax and Discount
//         gbc.gridx = 0;
//         gbc.gridy = 4;
//         detailsPanel.add(createStyledLabel("Tax (10%):"), gbc);
//         gbc.gridx = 1;
//         taxField = createStyledTextField(15);
//         taxField.setEditable(false);
//         detailsPanel.add(taxField, gbc);

//         gbc.gridx = 2;
//         detailsPanel.add(createStyledLabel("Discount (%):"), gbc);
//         gbc.gridx = 3;
//         discountField = createStyledTextField(15);
//         discountField.setText("0");
//         discountField.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
//             public void changedUpdate(javax.swing.event.DocumentEvent e) {
//                 updateGrandTotal();
//             }

//             public void removeUpdate(javax.swing.event.DocumentEvent e) {
//                 updateGrandTotal();
//             }

//             public void insertUpdate(javax.swing.event.DocumentEvent e) {
//                 updateGrandTotal();
//             }
//         });
//         detailsPanel.add(discountField, gbc);

//         // Row 6: Grand Total
//         gbc.gridx = 0;
//         gbc.gridy = 5;
//         detailsPanel.add(createStyledLabel("Grand Total:"), gbc);
//         gbc.gridx = 1;
//         gbc.gridwidth = 3;
//         grandTotalField = createStyledTextField(30);
//         grandTotalField.setEditable(false);
//         grandTotalField.setForeground(new Color(0, 153, 76));
//         grandTotalField.setFont(new Font("Arial", Font.BOLD, 16));
//         detailsPanel.add(grandTotalField, gbc);

//         // Row 7: Payment Method and Buttons
//         gbc.gridx = 0;
//         gbc.gridy = 6;
//         gbc.gridwidth = 1;
//         detailsPanel.add(createStyledLabel("Payment Method:"), gbc);
//         gbc.gridx = 1;
//         String[] methods = { "Cash", "Credit Card", "Debit Card", "Online Payment" };
//         paymentMethodCombo = new JComboBox<>(methods);
//         paymentMethodCombo.setFont(new Font("Arial", Font.PLAIN, 14));
//         detailsPanel.add(paymentMethodCombo, gbc);

//         gbc.gridx = 2;
//         JButton processButton = createStyledButton("Process Payment", new Color(0, 153, 76));
//         JButton printButton = createStyledButton("Print Bill", new Color(70, 130, 180));
//         JButton clearButton = createStyledButton("Clear", new Color(204, 0, 0));

//         processButton.addActionListener(e -> processPayment());
//         printButton.addActionListener(e -> printBill());
//         clearButton.addActionListener(e -> clearBillForm());

//         JPanel buttonPanel = new JPanel(new FlowLayout());
//         buttonPanel.setBackground(Color.WHITE);
//         buttonPanel.add(processButton);
//         buttonPanel.add(printButton);
//         buttonPanel.add(clearButton);

//         gbc.gridx = 3;
//         detailsPanel.add(buttonPanel, gbc);

//         panel.add(searchPanel, BorderLayout.NORTH);
//         panel.add(detailsPanel, BorderLayout.CENTER);

//         return panel;
//     }

//     private JPanel createRecentBillsPanel() {
//         JPanel panel = new JPanel(new BorderLayout());
//         panel.setBackground(Color.WHITE);
//         panel.setBorder(BorderFactory.createTitledBorder(
//                 BorderFactory.createLineBorder(new Color(70, 130, 180)),
//                 "Recent Bills",
//                 TitledBorder.LEFT,
//                 TitledBorder.TOP,
//                 new Font("Arial", Font.BOLD, 14),
//                 new Color(70, 130, 180)));

//         // Table
//         String[] columns = { "Bill No", "Reservation No", "Guest Name", "Amount", "Date", "Status", "Payment Method" };
//         tableModel = new DefaultTableModel(columns, 0) {
//             @Override
//             public boolean isCellEditable(int row, int column) {
//                 return false;
//             }
//         };

//         billTable = new JTable(tableModel);
//         billTable.setRowHeight(30);
//         billTable.setFont(new Font("Arial", Font.PLAIN, 12));
//         billTable.getTableHeader().setFont(new Font("Arial", Font.BOLD, 12));
//         billTable.getTableHeader().setBackground(new Color(70, 130, 180));
//         billTable.getTableHeader().setForeground(Color.WHITE);
//         billTable.setSelectionBackground(new Color(135, 206, 250));

//         // Color coding for payment status
//         billTable.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
//             @Override
//             public Component getTableCellRendererComponent(JTable table, Object value,
//                     boolean isSelected, boolean hasFocus, int row, int column) {
//                 Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
//                 if (!isSelected && column == 5) { // Status column
//                     String status = (String) tableModel.getValueAt(row, 5);
//                     if ("Paid".equals(status)) {
//                         c.setBackground(new Color(232, 245, 233)); // Light green
//                     } else if ("Pending".equals(status)) {
//                         c.setBackground(new Color(255, 243, 224)); // Light orange
//                     } else if ("Failed".equals(status)) {
//                         c.setBackground(new Color(255, 235, 238)); // Light red
//                     } else {
//                         c.setBackground(Color.WHITE);
//                     }
//                 } else if (!isSelected) {
//                     c.setBackground(Color.WHITE);
//                 }
//                 return c;
//             }
//         });

//         JScrollPane scrollPane = new JScrollPane(billTable);
//         scrollPane.setPreferredSize(new Dimension(800, 150));

//         panel.add(scrollPane, BorderLayout.CENTER);

//         return panel;
//     }

//     private JLabel createStyledLabel(String text) {
//         JLabel label = new JLabel(text);
//         label.setFont(new Font("Arial", Font.BOLD, 12));
//         return label;
//     }

//     private JTextField createStyledTextField(int columns) {
//         JTextField field = new JTextField(columns);
//         field.setFont(new Font("Arial", Font.PLAIN, 12));
//         return field;
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

//     private void loadBillingData() {
//         tableModel.setRowCount(0);

//         // Sample data - In real application, this would come from database
//         tableModel.addRow(
//                 new Object[] { "B001", "RES001", "John Doe", "Rs. 44,000", "2025-02-25", "Paid", "Credit Card" });
//         tableModel.addRow(new Object[] { "B002", "RES002", "Jane Smith", "Rs. 27,500", "2025-02-24", "Paid", "Cash" });
//         tableModel.addRow(
//                 new Object[] { "B003", "RES003", "Robert Johnson", "Rs. 66,000", "2025-02-24", "Pending", "Online" });
//         tableModel.addRow(
//                 new Object[] { "B004", "RES004", "Maria Garcia", "Rs. 16,500", "2025-02-23", "Paid", "Debit Card" });
//         tableModel.addRow(
//                 new Object[] { "B005", "RES005", "David Lee", "Rs. 55,000", "2025-02-23", "Failed", "Credit Card" });
//     }

//     private void searchReservation() {
//         String resNo = reservationField.getText().trim();
//         if (resNo.isEmpty()) {
//             JOptionPane.showMessageDialog(this,
//                     "Please enter reservation number!",
//                     "Input Required",
//                     JOptionPane.WARNING_MESSAGE);
//             return;
//         }

//         // Sample data - In real application, this would fetch from database
//         if (resNo.equals("RES001")) {
//             guestNameField.setText("John Doe");
//             roomTypeField.setText("Deluxe");
//             checkInField.setText("2025-02-20");
//             checkOutField.setText("2025-02-25");
//             nightsField.setText("5");
//             roomRateField.setText("8000");
//             totalAmountField.setText("40000");
//             updateGrandTotal();
//         } else {
//             JOptionPane.showMessageDialog(this,
//                     "Reservation not found!",
//                     "Not Found",
//                     JOptionPane.ERROR_MESSAGE);
//         }
//     }

//     private void calculateBill() {
//         try {
//             double total = Double.parseDouble(totalAmountField.getText());
//             double tax = total * 0.10; // 10% tax
//             taxField.setText(String.format("%.2f", tax));
//             updateGrandTotal();

//             JOptionPane.showMessageDialog(this,
//                     "Bill calculated successfully!\nTotal: Rs. " + total + "\nTax: Rs. " + tax,
//                     "Calculation Complete",
//                     JOptionPane.INFORMATION_MESSAGE);
//         } catch (NumberFormatException e) {
//             JOptionPane.showMessageDialog(this,
//                     "Please search for a reservation first!",
//                     "Error",
//                     JOptionPane.ERROR_MESSAGE);
//         }
//     }

//     private void updateGrandTotal() {
//         try {
//             double total = Double.parseDouble(totalAmountField.getText());
//             double tax = total * 0.10;
//             double discount = Double.parseDouble(discountField.getText());
//             double grandTotal = total + tax - (total * discount / 100);
//             grandTotalField.setText(String.format("Rs. %.2f", grandTotal));
//         } catch (NumberFormatException e) {
//             // Ignore
//         }
//     }

//     private void processPayment() {
//         if (guestNameField.getText().isEmpty()) {
//             JOptionPane.showMessageDialog(this,
//                     "Please calculate bill first!",
//                     "Error",
//                     JOptionPane.ERROR_MESSAGE);
//             return;
//         }

//         int confirm = JOptionPane.showConfirmDialog(this,
//                 "Process payment of " + grandTotalField.getText() + " via " + paymentMethodCombo.getSelectedItem()
//                         + "?",
//                 "Confirm Payment",
//                 JOptionPane.YES_NO_OPTION);

//         if (confirm == JOptionPane.YES_OPTION) {
//             // Add to recent bills table
//             String billNo = "B" + String.format("%03d", tableModel.getRowCount() + 1);
//             tableModel.addRow(new Object[] {
//                     billNo,
//                     reservationField.getText(),
//                     guestNameField.getText(),
//                     grandTotalField.getText(),
//                     LocalDate.now().toString(),
//                     "Paid",
//                     paymentMethodCombo.getSelectedItem()
//             });

//             JOptionPane.showMessageDialog(this,
//                     "Payment processed successfully!\nBill No: " + billNo,
//                     "Success",
//                     JOptionPane.INFORMATION_MESSAGE);

//             clearBillForm();
//         }
//     }

//     private void printBill() {
//         if (guestNameField.getText().isEmpty()) {
//             JOptionPane.showMessageDialog(this,
//                     "No bill to print!",
//                     "Error",
//                     JOptionPane.ERROR_MESSAGE);
//             return;
//         }

//         String billContent = "=================================\n" +
//                 "       HOTEL RESERVATION SYSTEM\n" +
//                 "=================================\n" +
//                 "           PAYMENT RECEIPT\n" +
//                 "=================================\n\n" +
//                 "Date: " + LocalDate.now() + "\n" +
//                 "Bill No: B" + String.format("%03d", tableModel.getRowCount() + 1) + "\n" +
//                 "Reservation No: " + reservationField.getText() + "\n\n" +
//                 "Guest Name: " + guestNameField.getText() + "\n" +
//                 "Room Type: " + roomTypeField.getText() + "\n" +
//                 "Check-in: " + checkInField.getText() + "\n" +
//                 "Check-out: " + checkOutField.getText() + "\n" +
//                 "Nights: " + nightsField.getText() + "\n\n" +
//                 "Room Charges: Rs. " + totalAmountField.getText() + "\n" +
//                 "Tax (10%): Rs. " + taxField.getText() + "\n" +
//                 "Discount: " + discountField.getText() + "%\n" +
//                 "---------------------------------\n" +
//                 "GRAND TOTAL: " + grandTotalField.getText() + "\n" +
//                 "---------------------------------\n" +
//                 "Payment Method: " + paymentMethodCombo.getSelectedItem() + "\n" +
//                 "Status: PAID\n\n" +
//                 "=================================\n" +
//                 "     Thank you for staying with us!\n" +
//                 "=================================";

//         JTextArea textArea = new JTextArea(billContent);
//         textArea.setFont(new Font("Monospaced", Font.PLAIN, 12));
//         JOptionPane.showMessageDialog(this,
//                 new JScrollPane(textArea),
//                 "Print Bill",
//                 JOptionPane.PLAIN_MESSAGE);
//     }

//     private void clearBillForm() {
//         reservationField.setText("");
//         guestNameField.setText("");
//         roomTypeField.setText("");
//         checkInField.setText("");
//         checkOutField.setText("");
//         nightsField.setText("");
//         roomRateField.setText("");
//         totalAmountField.setText("");
//         taxField.setText("");
//         discountField.setText("0");
//         grandTotalField.setText("");
//     }
// }

package main.java.com.hotel.view;

import com.hotel.model.User;
import javax.swing.*;
import java.awt.*;

public class BillingPanel extends JPanel {
    private User currentUser;

    public BillingPanel(User user) {
        this.currentUser = user;
        setBackground(new Color(255, 255, 240));
        setLayout(new BorderLayout());

        JLabel label = new JLabel("💰 BILLING PANEL", SwingConstants.CENTER);
        label.setFont(new Font("Arial", Font.BOLD, 24));
        add(label, BorderLayout.CENTER);
    }

    public BillingPanel(main.java.com.hotel.view.User currentUser2) {
        // TODO Auto-generated constructor stub
    }
}