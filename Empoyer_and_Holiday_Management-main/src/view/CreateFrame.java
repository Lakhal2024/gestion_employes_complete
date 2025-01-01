package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class CreateFrame extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField, confirmPasswordField;
    private JButton createBtn;

    public CreateFrame() {
        setTitle("Create Login Form");
        setSize(400, 350);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        usernameField = new JTextField(15);
        passwordField = new JPasswordField(15);
        confirmPasswordField = new JPasswordField(15);
        createBtn = new JButton("Create");
        createBtn.setFont(new Font("Arial", Font.PLAIN, 14));

        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(6, 1, 5, 5));
        inputPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JPanel btnPanel = new JPanel();
        btnPanel.setLayout(new GridLayout(1, 1, 5, 5));
        btnPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        inputPanel.add(createStyledLabel("Username"));
        inputPanel.add(usernameField);
        inputPanel.add(createStyledLabel("Password"));
        inputPanel.add(passwordField);
        inputPanel.add(createStyledLabel("Confirm Password"));
        inputPanel.add(confirmPasswordField);

        addButtonHoverEffect(createBtn);
        btnPanel.add(createBtn);

        add(inputPanel, BorderLayout.CENTER);
        add(btnPanel, BorderLayout.SOUTH);
        setVisible(false);
    }

    public String getUsername() {
        return usernameField.getText();
    }

    public String getPassword() {
        return new String(passwordField.getPassword());
    }

    public String getConfirmPassword() {
        return new String(confirmPasswordField.getPassword());
    }

    public JButton getCreateBtn() {
        return createBtn;
    }

    public void clearFields() {
        usernameField.setText("");
        passwordField.setText("");
        confirmPasswordField.setText("");
    }

    private JLabel createStyledLabel(String text) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("Arial", Font.PLAIN, 14));
        label.setForeground(new Color(70, 70, 70));
        return label;
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
}
