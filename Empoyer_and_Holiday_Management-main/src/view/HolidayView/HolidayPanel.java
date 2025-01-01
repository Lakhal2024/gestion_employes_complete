package view;

import javax.swing.*;
import java.awt.*;

public class HolidayPanel extends JPanel {

    private view.HoliInputPanel inPanel;
    private view.HoliBtnPanel btnPanel;
    private view.HoliListPanel listPanel;

    public HolidayPanel() {
        setLayout(new BorderLayout());

        inPanel =new view.HoliInputPanel();
        btnPanel = new view.HoliBtnPanel();
        listPanel = new view.HoliListPanel();

        add(inPanel, BorderLayout.NORTH);
        add(listPanel, BorderLayout.CENTER);
        add(btnPanel, BorderLayout.SOUTH);
        JScrollPane scrollPane = new JScrollPane(listPanel);
        add(scrollPane, BorderLayout.CENTER);
        setVisible(false);
    }
    ///
    public void afficherMessageErreur(String message) {
        JOptionPane.showMessageDialog(this, message, "Erreur", JOptionPane.ERROR_MESSAGE);
    }
    ///
    public void afficherMessageSucces(String message) {
        JOptionPane.showMessageDialog(this, message, "Succès", JOptionPane.INFORMATION_MESSAGE);
    }

    public view.HoliInputPanel getInPanel() {
        return inPanel;
    }

    public view.HoliBtnPanel getBtnPanel() {
        return btnPanel;
    }

    public view.HoliListPanel getListPanel() {
        return listPanel;
    }
}
