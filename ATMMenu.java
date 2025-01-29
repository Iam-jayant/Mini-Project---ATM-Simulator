import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class ATMMenu {
    private JFrame frame;
    private JLabel balanceLabel;
    private int balance = 5000; // Default Balance

    public ATMMenu() {
        frame = new JFrame("ATM Banking Services");
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridLayout(4, 1));

        balanceLabel = new JLabel("Current Balance: ₹" + balance, SwingConstants.CENTER);
        JButton depositButton = new JButton("Deposit");
        JButton withdrawButton = new JButton("Withdraw");
        JButton exitButton = new JButton("Exit");

        frame.add(balanceLabel);
        frame.add(depositButton);
        frame.add(withdrawButton);
        frame.add(exitButton);

        // Button Actions
        depositButton.addActionListener(e -> depositMoney());
        withdrawButton.addActionListener(e -> withdrawMoney());
        exitButton.addActionListener(e -> frame.dispose()); // Close ATM

        frame.setVisible(true);
    }

    private void depositMoney() {
        String input = JOptionPane.showInputDialog(frame, "Enter amount to deposit:");
        if (input != null) {
            int amount = Integer.parseInt(input);
            balance += amount;
            balanceLabel.setText("Current Balance: ₹" + balance);
            JOptionPane.showMessageDialog(frame, "Deposited ₹" + amount);
        }
    }

    private void withdrawMoney() {
        String input = JOptionPane.showInputDialog(frame, "Enter amount to withdraw:");
        if (input != null) {
            int amount = Integer.parseInt(input);
            if (amount <= balance) {
                balance -= amount;
                balanceLabel.setText("Current Balance: ₹" + balance);
                JOptionPane.showMessageDialog(frame, "Withdrawn ₹" + amount);
            } else {
                JOptionPane.showMessageDialog(frame, "Insufficient Balance!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
