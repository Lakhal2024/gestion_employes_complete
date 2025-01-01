package view;

import javax.swing.*;
import java.awt.*;
import enums.*;
import enums.Poste;
import enums.Role;

public class InputPanel extends JPanel {

    JTextField firstNameField, lastNameField, emailField, telephoneNumberField, salaryField;
    JComboBox<Role> roleField;
    JComboBox<Poste> posteField;

    public InputPanel() {
        setLayout(new GridLayout(7, 2, 5, 5));
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        setBackground(new Color(245, 245, 245));

        firstNameField = new JTextField(15);
        lastNameField = new JTextField(15);
        emailField = new JTextField(15);
        telephoneNumberField = new JTextField(15);
        salaryField = new JTextField(15);
        roleField = new JComboBox<>(Role.values());
        posteField = new JComboBox<>(Poste.values());

        add(createStyledLabel("First Name"));
        add(firstNameField);

        add(createStyledLabel("Last Name"));
        add(lastNameField);

        add(createStyledLabel("Email"));
        add(emailField);

        add(createStyledLabel("Telephone Number"));
        add(telephoneNumberField);

        add(createStyledLabel("Salary"));
        add(salaryField);

        add(createStyledLabel("Role"));
        add(roleField);

        add(createStyledLabel("Poste"));
        add(posteField);
        
        styleTextField(firstNameField);
        styleTextField(lastNameField);
        styleTextField(emailField);
        styleTextField(telephoneNumberField);
        styleTextField(salaryField);

        styleComboBox(roleField);
        styleComboBox(posteField);
    }

    private JLabel createStyledLabel(String text) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("Arial", Font.PLAIN, 14));
        label.setForeground(new Color(70, 70, 70));
        return label;
    }

    private void styleTextField(JTextField textField) {
        textField.setFont(new Font("Arial", Font.PLAIN, 14));
        textField.setBackground(new Color(255, 255, 255));
        textField.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200), 1));
        textField.setPreferredSize(new Dimension(150, 25));
    }

    private void styleComboBox(JComboBox<?> comboBox) {
        comboBox.setFont(new Font("Arial", Font.PLAIN, 14));
        comboBox.setBackground(new Color(255, 255, 255)); 
        comboBox.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200), 1));
        comboBox.setPreferredSize(new Dimension(150, 25));
    }

    public JTextField getFirstNameField() {
        return firstNameField;
    }

    public JTextField getLastNameField() {
        return lastNameField;
    }

    public JTextField getEmailField() {
        return emailField;
    }

    public JTextField getTelephoneNumberField() {
        return telephoneNumberField;
    }

    public JTextField getSalaryField() {
        return salaryField;
    }

    public Role getSelectedRole() {
        return (Role) roleField.getSelectedItem();
    }

    public Poste getSelectedPoste() {
        return (Poste) posteField.getSelectedItem();
    }

    public void clearFields() {
        firstNameField.setText("");
        lastNameField.setText("");
        emailField.setText("");
        telephoneNumberField.setText("");
        salaryField.setText("");
        roleField.setSelectedIndex(0);
        posteField.setSelectedIndex(0);
    }
}
