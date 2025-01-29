import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MiniATMSimulator {
    private JFrame frame;
    private JLabel screen, balanceLabel;
    private JTextField cardInput;
    private JPasswordField pinInput;
    private JButton enterButton, toggleBalanceButton;
    private boolean isBalanceVisible = false;
    private int balance = 5000;
    private String transactionPin = "5678";
    
    public MiniATMSimulator() {
        frame = new JFrame("Mini ATM Simulator Project");
        frame.setSize(600, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);
        frame.setLocationRelativeTo(null);
        frame.getContentPane().setBackground(Color.DARK_GRAY);

        JLabel title = new JLabel("Mini ATM Simulator Project", SwingConstants.CENTER);
        title.setBounds(150, 10, 300, 30);
        title.setForeground(Color.WHITE);
        title.setFont(new Font("Arial", Font.BOLD, 18));
        frame.add(title);
        
        screen = new JLabel("Enter Card Number & PIN", SwingConstants.CENTER);
        screen.setBounds(150, 50, 300, 50);
        screen.setForeground(Color.WHITE);
        screen.setBackground(Color.BLACK);
        screen.setOpaque(true);
        frame.add(screen);
        
        cardInput = new JTextField();
        cardInput.setBounds(200, 120, 200, 30);
        frame.add(cardInput);
        
        pinInput = new JPasswordField();
        pinInput.setBounds(200, 170, 200, 30);
        frame.add(pinInput);
        
        enterButton = new JButton("Enter");
        enterButton.setBounds(250, 220, 100, 30);
        frame.add(enterButton);
        enterButton.addActionListener(e -> authenticateUser());
        
        frame.setVisible(true);
    }
    
    private void authenticateUser() {
        String cardNumber = cardInput.getText();
        String pin = new String(pinInput.getPassword());
        
        if (cardNumber.equals("12345678") && pin.equals("1234")) {
            showTransactionMenu();
        } else {
            screen.setText("Invalid Credentials! Try Again");
        }
    }
    
    private void showTransactionMenu() {
        frame.getContentPane().removeAll();
        
        screen.setText("Select Transaction");
        screen.setBounds(150, 30, 300, 50);
        frame.add(screen);
        
        balanceLabel = new JLabel("Balance: ****");
        balanceLabel.setBounds(200, 90, 200, 30);
        balanceLabel.setForeground(Color.WHITE);
        frame.add(balanceLabel);

        toggleBalanceButton = new JButton("👁");
        toggleBalanceButton.setBounds(400, 90, 50, 30);
        toggleBalanceButton.addActionListener(e -> toggleBalanceVisibility());
        frame.add(toggleBalanceButton);
        
        JButton checkBalanceButton = new JButton("Check Balance");
        checkBalanceButton.setBounds(200, 140, 200, 30);
        checkBalanceButton.addActionListener(e -> showBalanceInput());
        frame.add(checkBalanceButton);
        
        JButton withdrawButton = new JButton("Withdraw");
        withdrawButton.setBounds(200, 190, 200, 30);
        withdrawButton.addActionListener(e -> showWithdrawInput());
        frame.add(withdrawButton);
        
        JButton depositButton = new JButton("Deposit");
        depositButton.setBounds(200, 240, 200, 30);
        depositButton.addActionListener(e -> showDepositInput());
        frame.add(depositButton);
        
        frame.repaint();
        frame.revalidate();
    }
    
    private void toggleBalanceVisibility() {
        isBalanceVisible = !isBalanceVisible;
        balanceLabel.setText(isBalanceVisible ? "Balance: " + balance : "Balance: ****");
    }
    
    private void showBalanceInput() {
        String pin = JOptionPane.showInputDialog(frame, "Enter Transaction PIN:");
        if (pin != null && pin.equals(transactionPin)) {
            JOptionPane.showMessageDialog(frame, "Balance: " + balance, "Balance Check", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(frame, "Invalid PIN!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void showWithdrawInput() {
        String pin = JOptionPane.showInputDialog(frame, "Enter Transaction PIN:");
        if (pin != null && pin.equals(transactionPin)) {
            String amount = JOptionPane.showInputDialog(frame, "Enter amount to withdraw:");
            if (amount != null) {
                int withdrawAmount = Integer.parseInt(amount);
                if (withdrawAmount > 0 && withdrawAmount <= balance) {
                    balance -= withdrawAmount;
                    JOptionPane.showMessageDialog(frame, "Withdrawal Successful!", "Success", JOptionPane.INFORMATION_MESSAGE);
                    toggleBalanceVisibility();
                } else {
                    JOptionPane.showMessageDialog(frame, "Invalid Amount!", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        } else {
            JOptionPane.showMessageDialog(frame, "Invalid PIN!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void showDepositInput() {
        String amount = JOptionPane.showInputDialog(frame, "Enter amount to deposit:");
        if (amount != null) {
            int depositAmount = Integer.parseInt(amount);
            if (depositAmount > 0) {
                balance += depositAmount;
                JOptionPane.showMessageDialog(frame, "Deposit Successful!", "Success", JOptionPane.INFORMATION_MESSAGE);
                toggleBalanceVisibility();
            } else {
                JOptionPane.showMessageDialog(frame, "Invalid Amount!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    public static void main(String[] args) {
        new MiniATMSimulator();
    }
}