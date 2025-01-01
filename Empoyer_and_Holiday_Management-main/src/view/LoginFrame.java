package view;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class LoginFrame extends JFrame {
    private JTextField usernameField, passwordField;
    private JButton loginButton, signupButton;

    public LoginFrame() {

        setTitle("Person Form");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        usernameField = new JTextField(25);
        passwordField = new JPasswordField(25);

        loginButton = new JButton("Login");
        loginButton.setFont(new Font("Arial", Font.PLAIN, 14));

        signupButton = new JButton("Sign Up");
        signupButton.setFont(new Font("Arial", Font.PLAIN, 14));

        addButtonHoverEffect(loginButton);
        addButtonHoverEffect(signupButton);

        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(4, 1, 5, 5));
        inputPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JPanel btnPanel = new JPanel();
        btnPanel.setLayout(new GridLayout(2, 1, 5, 5));
        btnPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        inputPanel.add(createStyledLabel("Username:"));
        inputPanel.add(usernameField);
        inputPanel.add(createStyledLabel("Password:"));
        inputPanel.add(passwordField);

        btnPanel.add(loginButton);
        btnPanel.add(signupButton);

        add(inputPanel, BorderLayout.CENTER);
        add(btnPanel, BorderLayout.SOUTH);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void addButtonHoverEffect(JButton button) {
        button.setBackground(new Color(238, 238, 238));

        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                button.setBackground(new Color(173, 216, 230)); 
            }

            @Override
            public void mouseExited(MouseEvent e) {
                button.setBackground(new Color(238, 238, 238));
            }
        });
    }

    private JLabel createStyledLabel(String text) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("Arial", Font.PLAIN, 14));
        label.setForeground(new Color(70, 70, 70));
        return label;
    }

    public String getUsername() {
        return usernameField.getText();
    }

    public String getPassword() {
        return passwordField.getText();
    }

    public JButton getLoginButton() {
        return loginButton;
    }
}
