package org.example;
import javax.swing.*;
import java.awt.*;

public class Login extends JFrame {
    public Login() {
        setTitle("Login System");
        setSize(450,300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        JLabel usernameLabel = new JLabel("Username");
        JTextField usernameField = new JTextField(15);
        usernameField.setPreferredSize(new Dimension(180, 30));
        JLabel passwordLabel = new JLabel("Password");
        JPasswordField passwordField = new JPasswordField(15);
        passwordField.setPreferredSize(new Dimension(180, 30));

        JPanel panel = new JPanel(new GridLayout(0,2,3,3));

        panel.add(usernameLabel);
        panel.add(usernameField);
        panel.add(passwordLabel);
        panel.add(passwordField);

        add(panel);

        JButton loginButton = new JButton("Login");
        panel.add(loginButton);
        JButton registerButton = new JButton("Register");
        panel.add(registerButton);
        registerButton.addActionListener(e -> {

            JTextField newUsername = new JTextField(15);
            JPasswordField newPassword = new JPasswordField(15);
            JPasswordField confirmPassword = new JPasswordField(15);

            JPanel registerPanel = new JPanel(new GridLayout(3, 2, 5, 5));

            registerPanel.add(new JLabel("Username:"));
            registerPanel.add(newUsername);

            registerPanel.add(new JLabel("Password:"));
            registerPanel.add(newPassword);

            registerPanel.add(new JLabel("Confirm Password:"));
            registerPanel.add(confirmPassword);

            int result = JOptionPane.showConfirmDialog(
                    this,
                    registerPanel,
                    "Register Account",
                    JOptionPane.OK_CANCEL_OPTION
            );

            if (result == JOptionPane.OK_OPTION) {

                String username = newUsername.getText().trim();
                String password = new String(newPassword.getPassword());
                String confirm = new String(confirmPassword.getPassword());

                if (username.isEmpty() || password.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "All fields are required!");

                } else if (!password.equals(confirm)) {
                    JOptionPane.showMessageDialog(this, "Passwords do not match!");

                } else if (UserRegistration.registerUser(username, password)) {
                    JOptionPane.showMessageDialog(this, "Registration successful!");

                } else {
                    JOptionPane.showMessageDialog(this, "Registration failed. Username may already exist!");
                }
            }
        });

        JLabel messageLabel = new JLabel("");
        panel.add(messageLabel);

        loginButton.addActionListener(e -> {

            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());

            if (username.isEmpty() || password.isEmpty()) {
                messageLabel.setText("Please fill in all fields.");
            } else if (UserAuthentication.login(username, password)) {

            JFrame successFrame = new JFrame("Login Successful");
            successFrame.setSize(350, 200);
            successFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            successFrame.setLocationRelativeTo(this);

            JLabel successLabel = new JLabel("Login succesfull!! " +
                    "Welcome, " + username + "!", SwingConstants.CENTER);
            successFrame.add(successLabel);

            successFrame.setVisible(true);


            } else {
                messageLabel.setText("Invalid username or password!");
            }

        });
        setVisible(true);
    }
    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {
            new Login();
        });

    }
}
