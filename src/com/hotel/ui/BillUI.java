// package com.hotel.ui;

// import javax.swing.*;
// import java.awt.*;

// public class BillUI {

//     public BillUI() {

//         JFrame frame = new JFrame("Generate Bill");
//         frame.setSize(350, 250);
//         frame.setLocationRelativeTo(null);

//         JPanel panel = new JPanel(new GridLayout(4, 2, 10, 10));

//         JTextField nights = new JTextField();
//         JTextField rate = new JTextField();

//         JLabel result = new JLabel("");

//         JButton calc = new JButton("Calculate");

//         panel.add(new JLabel("Number of Nights"));
//         panel.add(nights);

//         panel.add(new JLabel("Room Rate"));
//         panel.add(rate);

//         panel.add(calc);
//         panel.add(result);

//         frame.add(panel);
//         frame.setVisible(true);

//         calc.addActionListener(e -> {

//             int n = Integer.parseInt(nights.getText());
//             int r = Integer.parseInt(rate.getText());

//             result.setText("Total Bill: $" + (n * r));

//         });

//     }

//     public static Object show() {
//         // TODO Auto-generated method stub
//         throw new UnsupportedOperationException("Unimplemented method 'show'");
//     }
// }

package com.hotel.ui;

import java.util.Scanner;

public class BillUI {

    public static void calculate() {

        Scanner scanner = new Scanner(System.in);

        System.out.println("\n----- BILL CALCULATION -----");

        System.out.print("Enter Room Rate per Night: ");
        double rate = scanner.nextDouble();

        System.out.print("Enter Number of Nights: ");
        int nights = scanner.nextInt();

        double total = rate * nights;

        System.out.println("Total Bill = $" + total);
    }

    public static Object show() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'show'");
    }
}