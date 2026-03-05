// package main.java.com.hotel.view;

// import main.java.com.hotel.model.User;
// import javax.swing.*;
// import javax.swing.border.TitledBorder;
// import javax.swing.table.DefaultTableModel;
// import java.awt.*;
// import java.time.LocalDate;
// import java.time.format.DateTimeFormatter;
// import java.util.HashMap;
// import java.util.Map;

// public class ReportPanel extends JPanel {
//     private User currentUser;
//     private JComboBox<String> reportTypeCombo;
//     private JComboBox<String> periodCombo;
//     private JComboBox<String> formatCombo;
//     private JTextField fromDateField;
//     private JTextField toDateField;
//     private JTextArea reportArea;
//     private JPanel chartPanel;
//     private CardLayout chartCardLayout;
//     private Map<String, DefaultTableModel> reportData;

//     public ReportPanel(User user) {
//         this.currentUser = user;
//         this.reportData = new HashMap<>();
//         initComponents();
//         initializeReportData();
//     }

//     private void initComponents() {
//         setLayout(new BorderLayout());
//         setBackground(Color.WHITE);

//         // Title panel
//         JPanel titlePanel = new JPanel();
//         titlePanel.setBackground(new Color(70, 130, 180));
//         JLabel titleLabel = new JLabel("REPORTS & ANALYTICS");
//         titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
//         titleLabel.setForeground(Color.WHITE);
//         titlePanel.add(titleLabel);
//         add(titlePanel, BorderLayout.NORTH);

//         // Main content panel with split
//         JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
//         splitPane.setDividerLocation(350);
//         splitPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

//         // Left panel - Report controls
//         JPanel controlPanel = createControlPanel();
//         splitPane.setLeftComponent(controlPanel);

//         // Right panel - Report display
//         JPanel displayPanel = createDisplayPanel();
//         splitPane.setRightComponent(displayPanel);

//         add(splitPane, BorderLayout.CENTER);

//         // Status panel
//         JPanel statusPanel = new JPanel();
//         statusPanel.setBackground(new Color(240, 240, 240));
//         statusPanel.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
//         JLabel statusLabel = new JLabel("Logged in as: " + currentUser.getUsername() + " (" + currentUser.getRole()
//                 + ") | " + LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
//         statusLabel.setFont(new Font("Arial", Font.ITALIC, 12));
//         statusPanel.add(statusLabel);
//         add(statusPanel, BorderLayout.SOUTH);
//     }

//     private JPanel createControlPanel() {
//         JPanel panel = new JPanel(new BorderLayout());
//         panel.setBackground(Color.WHITE);
//         panel.setBorder(BorderFactory.createTitledBorder(
//                 BorderFactory.createLineBorder(new Color(70, 130, 180)),
//                 "Report Controls",
//                 TitledBorder.LEFT,
//                 TitledBorder.TOP,
//                 new Font("Arial", Font.BOLD, 14),
//                 new Color(70, 130, 180)));

//         JPanel controlsPanel = new JPanel(new GridBagLayout());
//         controlsPanel.setBackground(Color.WHITE);
//         GridBagConstraints gbc = new GridBagConstraints();
//         gbc.insets = new Insets(10, 10, 10, 10);
//         gbc.fill = GridBagConstraints.HORIZONTAL;
//         gbc.gridwidth = GridBagConstraints.REMAINDER;

//         // Report Type
//         gbc.gridy = 0;
//         controlsPanel.add(createStyledLabel("Report Type:"), gbc);

//         String[] reportTypes = {
//                 "Occupancy Report",
//                 "Revenue Report",
//                 "Guest History Report",
//                 "Room Availability Report",
//                 "Monthly Summary",
//                 "Yearly Report",
//                 "Payment Summary",
//                 "Cancellation Report"
//         };
//         reportTypeCombo = new JComboBox<>(reportTypes);
//         reportTypeCombo.setFont(new Font("Arial", Font.PLAIN, 14));
//         reportTypeCombo.setPreferredSize(new Dimension(250, 30));
//         controlsPanel.add(reportTypeCombo, gbc);

//         // Period
//         gbc.gridy = 1;
//         controlsPanel.add(createStyledLabel("Period:"), gbc);

//         String[] periods = { "Today", "This Week", "This Month", "This Quarter", "This Year", "Custom Range" };
//         periodCombo = new JComboBox<>(periods);
//         periodCombo.setFont(new Font("Arial", Font.PLAIN, 14));
//         periodCombo.addActionListener(e -> toggleDateFields());
//         controlsPanel.add(periodCombo, gbc);

//         // From Date
//         gbc.gridy = 2;
//         controlsPanel.add(createStyledLabel("From Date (YYYY-MM-DD):"), gbc);
//         fromDateField = new JTextField(15);
//         fromDateField.setFont(new Font("Arial", Font.PLAIN, 14));
//         fromDateField.setText(LocalDate.now().minusDays(30).toString());
//         fromDateField.setEnabled(false);
//         controlsPanel.add(fromDateField, gbc);

//         // To Date
//         gbc.gridy = 3;
//         controlsPanel.add(createStyledLabel("To Date (YYYY-MM-DD):"), gbc);
//         toDateField = new JTextField(15);
//         toDateField.setFont(new Font("Arial", Font.PLAIN, 14));
//         toDateField.setText(LocalDate.now().toString());
//         toDateField.setEnabled(false);
//         controlsPanel.add(toDateField, gbc);

//         // Format
//         gbc.gridy = 4;
//         controlsPanel.add(createStyledLabel("Format:"), gbc);
//         String[] formats = { "Summary View", "Detailed View", "Chart View" };
//         formatCombo = new JComboBox<>(formats);
//         formatCombo.setFont(new Font("Arial", Font.PLAIN, 14));
//         formatCombo.addActionListener(e -> changeView());
//         controlsPanel.add(formatCombo, gbc);

//         // Buttons
//         gbc.gridy = 5;
//         JPanel buttonPanel = new JPanel(new GridLayout(3, 1, 10, 10));
//         buttonPanel.setBackground(Color.WHITE);

//         JButton generateButton = createStyledButton("Generate Report", new Color(0, 153, 76));
//         JButton exportButton = createStyledButton("Export to PDF", new Color(70, 130, 180));
//         JButton printButton = createStyledButton("Print Report", new Color(255, 153, 0));

//         generateButton.addActionListener(e -> generateReport());
//         exportButton.addActionListener(e -> exportReport());
//         printButton.addActionListener(e -> printReport());

//         buttonPanel.add(generateButton);
//         buttonPanel.add(exportButton);
//         buttonPanel.add(printButton);

//         controlsPanel.add(buttonPanel, gbc);

//         // Quick Stats
//         gbc.gridy = 6;
//         controlsPanel.add(createQuickStatsPanel(), gbc);

//         JScrollPane scrollPane = new JScrollPane(controlsPanel);
//         scrollPane.setBorder(BorderFactory.createEmptyBorder());
//         scrollPane.getVerticalScrollBar().setUnitIncrement(16);

//         panel.add(scrollPane, BorderLayout.CENTER);

//         return panel;
//     }

//     private JPanel createDisplayPanel() {
//         JPanel panel = new JPanel(new BorderLayout());
//         panel.setBackground(Color.WHITE);
//         panel.setBorder(BorderFactory.createTitledBorder(
//                 BorderFactory.createLineBorder(new Color(70, 130, 180)),
//                 "Report Preview",
//                 TitledBorder.LEFT,
//                 TitledBorder.TOP,
//                 new Font("Arial", Font.BOLD, 14),
//                 new Color(70, 130, 180)));

//         // Create card layout for different views
//         chartCardLayout = new CardLayout();
//         chartPanel = new JPanel(chartCardLayout);

//         // Report text area
//         reportArea = new JTextArea(30, 50);
//         reportArea.setEditable(false);
//         reportArea.setFont(new Font("Monospaced", Font.PLAIN, 12));
//         JScrollPane textScrollPane = new JScrollPane(reportArea);
//         chartPanel.add(textScrollPane, "TEXT");

//         // Chart view
//         JPanel chartViewPanel = createChartViewPanel();
//         chartPanel.add(chartViewPanel, "CHART");

//         panel.add(chartPanel, BorderLayout.CENTER);

//         return panel;
//     }

//     private JPanel createQuickStatsPanel() {
//         JPanel panel = new JPanel(new GridLayout(4, 2, 5, 5));
//         panel.setBackground(new Color(240, 248, 255));
//         panel.setBorder(BorderFactory.createTitledBorder(
//                 BorderFactory.createEtchedBorder(),
//                 "Quick Stats",
//                 TitledBorder.LEFT,
//                 TitledBorder.TOP,
//                 new Font("Arial", Font.BOLD, 12)));

//         String[] stats = {
//                 "Total Rooms:", "50",
//                 "Occupied:", "35",
//                 "Available:", "15",
//                 "Today's Revenue:", "Rs. 125,000"
//         };

//         for (int i = 0; i < stats.length; i += 2) {
//             JLabel label1 = new JLabel(stats[i]);
//             label1.setFont(new Font("Arial", Font.BOLD, 12));
//             panel.add(label1);

//             JLabel label2 = new JLabel(stats[i + 1]);
//             label2.setFont(new Font("Arial", Font.PLAIN, 12));
//             if (i == 6) { // Revenue
//                 label2.setForeground(new Color(0, 153, 76));
//             }
//             panel.add(label2);
//         }

//         return panel;
//     }

//     private JPanel createChartViewPanel() {
//         JPanel panel = new JPanel(new BorderLayout());
//         panel.setBackground(Color.WHITE);

//         // Create a simple bar chart using colored panels
//         JPanel chartContainer = new JPanel() {
//             @Override
//             protected void paintComponent(Graphics g) {
//                 super.paintComponent(g);
//                 Graphics2D g2d = (Graphics2D) g;

//                 int width = getWidth() - 100;
//                 int height = getHeight() - 100;
//                 int barWidth = width / 7 - 20;

//                 // Draw bars
//                 int[] values = { 85, 70, 60, 45, 30, 25, 20 };
//                 String[] labels = { "Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul" };
//                 Color[] colors = {
//                         new Color(70, 130, 180),
//                         new Color(100, 149, 237),
//                         new Color(135, 206, 250),
//                         new Color(173, 216, 230),
//                         new Color(176, 224, 230),
//                         new Color(135, 206, 235),
//                         new Color(70, 130, 180)
//                 };

//                 for (int i = 0; i < values.length; i++) {
//                     int x = 50 + i * (barWidth + 30);
//                     int barHeight = (values[i] * height) / 100;
//                     int y = getHeight() - 50 - barHeight;

//                     g2d.setColor(colors[i]);
//                     g2d.fillRect(x, y, barWidth, barHeight);

//                     g2d.setColor(Color.BLACK);
//                     g2d.drawRect(x, y, barWidth, barHeight);

//                     // Label
//                     g2d.setFont(new Font("Arial", Font.PLAIN, 10));
//                     g2d.drawString(labels[i], x + 5, getHeight() - 30);

//                     // Value
//                     g2d.drawString(values[i] + "%", x + 5, y - 5);
//                 }
//             }
//         };
//         chartContainer.setBackground(Color.WHITE);

//         JLabel titleLabel = new JLabel("Monthly Occupancy Rate (%)", SwingConstants.CENTER);
//         titleLabel.setFont(new Font("Arial", Font.BOLD, 14));

//         panel.add(titleLabel, BorderLayout.NORTH);
//         panel.add(chartContainer, BorderLayout.CENTER);

//         return panel;
//     }

//     private JLabel createStyledLabel(String text) {
//         JLabel label = new JLabel(text);
//         label.setFont(new Font("Arial", Font.BOLD, 12));
//         return label;
//     }

//     private JButton createStyledButton(String text, Color bgColor) {
//         JButton button = new JButton(text);
//         button.setFont(new Font("Arial", Font.BOLD, 12));
//         button.setForeground(Color.WHITE);
//         button.setBackground(bgColor);
//         button.setFocusPainted(false);
//         button.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
//         return button;
//     }

//     private void toggleDateFields() {
//         boolean enabled = periodCombo.getSelectedItem().equals("Custom Range");
//         fromDateField.setEnabled(enabled);
//         toDateField.setEnabled(enabled);
//     }

//     private void changeView() {
//         String selectedFormat = (String) formatCombo.getSelectedItem();
//         if ("Chart View".equals(selectedFormat)) {
//             chartCardLayout.show(chartPanel, "CHART");
//         } else {
//             chartCardLayout.show(chartPanel, "TEXT");
//             generateReport(); // Regenerate report for text view
//         }
//     }

//     private void initializeReportData() {
//         // Occupancy Report
//         DefaultTableModel occupancyModel = new DefaultTableModel(
//                 new String[] { "Room Type", "Total Rooms", "Occupied", "Available", "Occupancy Rate" }, 0);
//         occupancyModel.addRow(new Object[] { "Standard", "20", "16", "4", "80%" });
//         occupancyModel.addRow(new Object[] { "Deluxe", "15", "12", "3", "80%" });
//         occupancyModel.addRow(new Object[] { "Suite", "8", "5", "3", "62.5%" });
//         occupancyModel.addRow(new Object[] { "Family", "5", "3", "2", "60%" });
//         occupancyModel.addRow(new Object[] { "Presidential", "2", "1", "1", "50%" });
//         reportData.put("Occupancy Report", occupancyModel);

//         // Revenue Report
//         DefaultTableModel revenueModel = new DefaultTableModel(
//                 new String[] { "Month", "Room Revenue", "Other Revenue", "Total Revenue", "Growth" }, 0);
//         revenueModel.addRow(new Object[] { "January", "450,000", "25,000", "475,000", "+5%" });
//         revenueModel.addRow(new Object[] { "February", "520,000", "30,000", "550,000", "+15.8%" });
//         revenueModel.addRow(new Object[] { "March", "580,000", "28,000", "608,000", "+10.5%" });
//         revenueModel.addRow(new Object[] { "April", "610,000", "32,000", "642,000", "+5.6%" });
//         revenueModel.addRow(new Object[] { "May", "590,000", "35,000", "625,000", "-2.6%" });
//         reportData.put("Revenue Report", revenueModel);

//         // Guest History Report
//         DefaultTableModel guestModel = new DefaultTableModel(
//                 new String[] { "Guest Name", "Total Stays", "Total Spent", "Last Visit", "Favorite Room" }, 0);
//         guestModel.addRow(new Object[] { "John Doe", "5", "Rs. 125,000", "2025-02-20", "Deluxe" });
//         guestModel.addRow(new Object[] { "Jane Smith", "3", "Rs. 75,000", "2025-02-18", "Suite" });
//         guestModel.addRow(new Object[] { "Robert Johnson", "2", "Rs. 45,000", "2025-02-15", "Standard" });
//         reportData.put("Guest History Report", guestModel);
//     }

//     private void generateReport() {
//         String reportType = (String) reportTypeCombo.getSelectedItem();
//         String period = (String) periodCombo.getSelectedItem();
//         String fromDate = fromDateField.getText();
//         String toDate = toDateField.getText();

//         StringBuilder report = new StringBuilder();
//         report.append("=").append("=".repeat(70)).append("\n");
//         report.append(String.format("           %s\n", reportType.toUpperCase()));
//         report.append("=").append("=".repeat(70)).append("\n\n");

//         report.append(String.format("Generated By: %s (%s)\n", currentUser.getUsername(), currentUser.getRole()));
//         report.append(String.format("Date: %s\n",
//                 LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))));
//         report.append(String.format("Period: %s\n", period));

//         if (period.equals("Custom Range")) {
//             report.append(String.format("Date Range: %s to %s\n", fromDate, toDate));
//         }

//         report.append("\n");
//         report.append("-".repeat(70)).append("\n\n");

//         // Generate report based on type
//         switch (reportType) {
//             case "Occupancy Report":
//                 generateOccupancyReport(report);
//                 break;
//             case "Revenue Report":
//                 generateRevenueReport(report);
//                 break;
//             case "Guest History Report":
//                 generateGuestReport(report);
//                 break;
//             case "Room Availability Report":
//                 generateAvailabilityReport(report);
//                 break;
//             case "Monthly Summary":
//                 generateMonthlySummary(report);
//                 break;
//             case "Yearly Report":
//                 generateYearlyReport(report);
//                 break;
//             case "Payment Summary":
//                 generatePaymentSummary(report);
//                 break;
//             case "Cancellation Report":
//                 generateCancellationReport(report);
//                 break;
//             default:
//                 report.append("No data available for selected report type.\n");
//         }

//         report.append("\n");
//         report.append("=").append("=".repeat(70)).append("\n");
//         report.append("           END OF REPORT\n");
//         report.append("=").append("=".repeat(70)).append("\n");

//         reportArea.setText(report.toString());
//         reportArea.setCaretPosition(0);

//         // Show success message
//         JOptionPane.showMessageDialog(this,
//                 "Report generated successfully!",
//                 "Success",
//                 JOptionPane.INFORMATION_MESSAGE);
//     }

//     private void generateOccupancyReport(StringBuilder report) {
//         DefaultTableModel model = reportData.get("Occupancy Report");
//         if (model != null) {
//             report.append(String.format("%-15s %-12s %-12s %-12s %-15s\n",
//                     "Room Type", "Total", "Occupied", "Available", "Rate"));
//             report.append("-".repeat(70)).append("\n");

//             int totalRooms = 0;
//             int totalOccupied = 0;

//             for (int i = 0; i < model.getRowCount(); i++) {
//                 String type = (String) model.getValueAt(i, 0);
//                 String total = (String) model.getValueAt(i, 1);
//                 String occupied = (String) model.getValueAt(i, 2);
//                 String available = (String) model.getValueAt(i, 3);
//                 String rate = (String) model.getValueAt(i, 4);

//                 report.append(String.format("%-15s %-12s %-12s %-12s %-15s\n",
//                         type, total, occupied, available, rate));

//                 totalRooms += Integer.parseInt(total);
//                 totalOccupied += Integer.parseInt(occupied);
//             }

//             report.append("-".repeat(70)).append("\n");
//             double overallRate = (totalOccupied * 100.0) / totalRooms;
//             report.append(String.format("Overall Occupancy Rate: %.1f%%\n", overallRate));
//         }
//     }

//     private void generateRevenueReport(StringBuilder report) {
//         DefaultTableModel model = reportData.get("Revenue Report");
//         if (model != null) {
//             report.append(String.format("%-10s %-15s %-15s %-15s %-10s\n",
//                     "Month", "Room Revenue", "Other Revenue", "Total Revenue", "Growth"));
//             report.append("-".repeat(70)).append("\n");

//             double totalRevenue = 0;

//             for (int i = 0; i < model.getRowCount(); i++) {
//                 String month = (String) model.getValueAt(i, 0);
//                 String roomRev = (String) model.getValueAt(i, 1);
//                 String otherRev = (String) model.getValueAt(i, 2);
//                 String total = (String) model.getValueAt(i, 3);
//                 String growth = (String) model.getValueAt(i, 4);

//                 report.append(String.format("%-10s Rs.%-12s Rs.%-12s Rs.%-12s %-10s\n",
//                         month, roomRev, otherRev, total, growth));

//                 totalRevenue += Double.parseDouble(total.replace(",", ""));
//             }

//             report.append("-".repeat(70)).append("\n");
//             report.append(String.format("Total Revenue: Rs. %,.2f\n", totalRevenue));
//         }
//     }

//     private void generateGuestReport(StringBuilder report) {
//         DefaultTableModel model = reportData.get("Guest History Report");
//         if (model != null) {
//             report.append(String.format("%-20s %-12s %-15s %-12s %-15s\n",
//                     "Guest Name", "Stays", "Total Spent", "Last Visit", "Favorite Room"));
//             report.append("-".repeat(70)).append("\n");

//             for (int i = 0; i < model.getRowCount(); i++) {
//                 String name = (String) model.getValueAt(i, 0);
//                 String stays = (String) model.getValueAt(i, 1);
//                 String spent = (String) model.getValueAt(i, 2);
//                 String lastVisit = (String) model.getValueAt(i, 3);
//                 String favorite = (String) model.getValueAt(i, 4);

//                 report.append(String.format("%-20s %-12s %-15s %-12s %-15s\n",
//                         name, stays, spent, lastVisit, favorite));
//             }
//         }
//     }

//     private void generateAvailabilityReport(StringBuilder report) {
//         report.append("Current Room Availability\n\n");
//         report.append(String.format("%-10s %-15s %-12s %-10s\n",
//                 "Room No", "Type", "Status", "Next Available"));
//         report.append("-".repeat(50)).append("\n");

//         // Sample data
//         report.append(String.format("%-10s %-15s %-12s %-10s\n", "101", "Standard", "Available", "Now"));
//         report.append(String.format("%-10s %-15s %-12s %-10s\n", "102", "Standard", "Occupied", "2025-02-28"));
//         report.append(String.format("%-10s %-15s %-12s %-10s\n", "201", "Deluxe", "Available", "Now"));
//         report.append(String.format("%-10s %-15s %-12s %-10s\n", "202", "Deluxe", "Maintenance", "2025-03-05"));
//         report.append(String.format("%-10s %-15s %-12s %-10s\n", "301", "Suite", "Available", "Now"));
//         report.append(String.format("%-10s %-15s %-12s %-10s\n", "401", "Family", "Occupied", "2025-03-01"));

//         report.append("-".repeat(50)).append("\n");
//         report.append("Total Available Rooms: 3\n");
//     }

//     private void generateMonthlySummary(StringBuilder report) {
//         report.append("Monthly Summary - " + LocalDate.now().format(DateTimeFormatter.ofPattern("MMMM yyyy")) + "\n\n");

//         report.append("Key Metrics:\n");
//         report.append("-".repeat(30)).append("\n");
//         report.append("Total Reservations: 45\n");
//         report.append("Check-ins: 38\n");
//         report.append("Check-outs: 35\n");
//         report.append("Cancellations: 3\n");
//         report.append("No-shows: 2\n\n");

//         report.append("Revenue Summary:\n");
//         report.append("-".repeat(30)).append("\n");
//         report.append("Room Revenue: Rs. 325,000\n");
//         report.append("Food & Beverage: Rs. 45,000\n");
//         report.append("Other Services: Rs. 15,000\n");
//         report.append("Total Revenue: Rs. 385,000\n");
//     }

//     private void generateYearlyReport(StringBuilder report) {
//         report.append("Yearly Report - " + LocalDate.now().getYear() + "\n\n");

//         report.append("Monthly Performance:\n");
//         report.append("-".repeat(60)).append("\n");
//         report.append(String.format("%-10s %-15s %-15s %-15s\n",
//                 "Month", "Reservations", "Revenue", "Occupancy"));

//         String[][] months = {
//                 { "January", "45", "325,000", "75%" },
//                 { "February", "52", "385,000", "82%" },
//                 { "March", "48", "360,000", "78%" },
//                 { "April", "55", "410,000", "85%" },
//                 { "May", "50", "375,000", "80%" }
//         };

//         for (String[] month : months) {
//             report.append(String.format("%-10s %-15s Rs.%-12s %-15s\n",
//                     month[0], month[1], month[2], month[3]));
//         }

//         report.append("-".repeat(60)).append("\n");
//         report.append("Year to Date Revenue: Rs. 1,855,000\n");
//     }

//     private void generatePaymentSummary(StringBuilder report) {
//         report.append("Payment Summary\n\n");
//         report.append(String.format("%-15s %-15s %-15s\n",
//                 "Method", "Count", "Amount"));
//         report.append("-".repeat(45)).append("\n");

//         report.append(String.format("%-15s %-15s Rs.%-12s\n", "Cash", "25", "125,000"));
//         report.append(String.format("%-15s %-15s Rs.%-12s\n", "Credit Card", "18", "215,000"));
//         report.append(String.format("%-15s %-15s Rs.%-12s\n", "Debit Card", "12", "98,000"));
//         report.append(String.format("%-15s %-15s Rs.%-12s\n", "Online", "8", "142,000"));

//         report.append("-".repeat(45)).append("\n");
//         report.append(String.format("%-15s %-15s Rs.%-12s\n", "Total", "63", "580,000"));
//     }

//     private void generateCancellationReport(StringBuilder report) {
//         report.append("Cancellation Report\n\n");
//         report.append(String.format("%-15s %-12s %-15s %-15s\n",
//                 "Date", "Reservations", "Cancellations", "Rate"));
//         report.append("-".repeat(60)).append("\n");

//         report.append(String.format("%-15s %-12s %-15s %-15s\n", "2025-02-20", "12", "1", "8.3%"));
//         report.append(String.format("%-15s %-12s %-15s %-15s\n", "2025-02-21", "15", "2", "13.3%"));
//         report.append(String.format("%-15s %-12s %-15s %-15s\n", "2025-02-22", "10", "0", "0%"));
//         report.append(String.format("%-15s %-12s %-15s %-15s\n", "2025-02-23", "14", "1", "7.1%"));
//         report.append(String.format("%-15s %-12s %-15s %-15s\n", "2025-02-24", "11", "1", "9.1%"));

//         report.append("-".repeat(60)).append("\n");
//         report.append("Total Cancellation Rate: 7.8%\n");
//     }

//     private void exportReport() {
//         String reportType = (String) reportTypeCombo.getSelectedItem();
//         String content = reportArea.getText();

//         if (content.isEmpty()) {
//             JOptionPane.showMessageDialog(this,
//                     "Please generate a report first!",
//                     "No Report",
//                     JOptionPane.WARNING_MESSAGE);
//             return;
//         }

//         JFileChooser fileChooser = new JFileChooser();
//         fileChooser.setSelectedFile(new java.io.File(reportType.replace(" ", "_") + "_" +
//                 LocalDate.now() + ".txt"));

//         int result = fileChooser.showSaveDialog(this);
//         if (result == JFileChooser.APPROVE_OPTION) {
//             try {
//                 java.io.File file = fileChooser.getSelectedFile();
//                 java.io.FileWriter writer = new java.io.FileWriter(file);
//                 writer.write(content);
//                 writer.close();

//                 JOptionPane.showMessageDialog(this,
//                         "Report exported successfully to:\n" + file.getAbsolutePath(),
//                         "Export Successful",
//                         JOptionPane.INFORMATION_MESSAGE);
//             } catch (Exception ex) {
//                 JOptionPane.showMessageDialog(this,
//                         "Error exporting report: " + ex.getMessage(),
//                         "Export Error",
//                         JOptionPane.ERROR_MESSAGE);
//             }
//         }
//     }

//     private void printReport() {
//         String content = reportArea.getText();

//         if (content.isEmpty()) {
//             JOptionPane.showMessageDialog(this,
//                     "Please generate a report first!",
//                     "No Report",
//                     JOptionPane.WARNING_MESSAGE);
//             return;
//         }

//         try {
//             boolean printed = reportArea.print();
//             if (printed) {
//                 JOptionPane.showMessageDialog(this,
//                         "Print job sent successfully!",
//                         "Print Successful",
//                         JOptionPane.INFORMATION_MESSAGE);
//             }
//         } catch (Exception ex) {
//             JOptionPane.showMessageDialog(this,
//                     "Error printing report: " + ex.getMessage(),
//                     "Print Error",
//                     JOptionPane.ERROR_MESSAGE);
//         }
//     }
// }

package main.java.com.hotel.view;

import com.hotel.model.User;
import javax.swing.*;

public class ReportPanel extends JPanel {
    private User currentUser;

    public ReportPanel(com.hotel.model.User currentUser2) {
        // TODO Auto-generated constructor stub
    }
}
