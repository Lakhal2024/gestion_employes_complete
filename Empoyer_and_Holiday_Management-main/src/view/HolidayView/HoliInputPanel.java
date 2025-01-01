package view;

import javax.swing.*;
import javax.swing.text.DateFormatter;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.time.ZoneId;
import java.time.LocalDate;
import enums.*;
import enums.HolidayType;

public class HoliInputPanel extends JPanel {

    private JFormattedTextField startDateField;
    private JFormattedTextField endDateField;
    private JComboBox<HolidayType> typeField;

    public HoliInputPanel() {
        setLayout(new GridLayout(3, 2, 5, 5));
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        setBackground(new Color(245, 245, 245)); 

        DateFormatter dateFormatter = new DateFormatter(new SimpleDateFormat("dd/MM/yyyy"));

        startDateField = new JFormattedTextField(dateFormatter);
        startDateField.setValue(new Date());
        startDateField.setColumns(15);

        endDateField = new JFormattedTextField(dateFormatter);
        endDateField.setValue(new Date());
        endDateField.setColumns(15);

        typeField = new JComboBox<>(HolidayType.values());


        add(createStyledLabel("Start Date (dd/MM/yyyy):"));
        add(startDateField);

        add(createStyledLabel("End Date (dd/MM/yyyy):"));
        add(endDateField);

        add(createStyledLabel("Holiday Type:"));
        add(typeField);

        styleTextField(startDateField);
        styleTextField(endDateField);
        styleComboBox(typeField);
    }

    private JLabel createStyledLabel(String text) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("Arial", Font.PLAIN, 14));
        label.setForeground(new Color(70, 70, 70));
        return label;
    }

    private void styleTextField(JFormattedTextField textField) {
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

    public LocalDate getStartDate() {
        Date date = (Date) startDateField.getValue();
        return date != null ? date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate() : null;
    }

    public LocalDate getEndDate() {
        Date date = (Date) endDateField.getValue();
        return date != null ? date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate() : null;
    }

    public HolidayType getSelectedHolidayType() {
        return (HolidayType) typeField.getSelectedItem();
    }

    public void clearFields() {
        startDateField.setValue(null);
        endDateField.setValue(null);
        typeField.setSelectedIndex(0);
    }
}
