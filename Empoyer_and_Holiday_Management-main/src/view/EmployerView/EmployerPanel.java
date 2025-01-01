package view;

import javax.swing.*;
import java.awt.*;

public class EmployerPanel extends JPanel {

    private view.InputPanel inPanel;
    private view.BtnPanel btnPanel;
    private view.ListPanel listPanel;

    public EmployerPanel() {
        setLayout(new BorderLayout());

        inPanel = new view.InputPanel();
        btnPanel = new view.BtnPanel();
        listPanel = new view.ListPanel();

        add(inPanel, BorderLayout.NORTH);
        add(listPanel, BorderLayout.CENTER);
        add(btnPanel, BorderLayout.SOUTH);
        JScrollPane scrollPane = new JScrollPane(listPanel);
        add(scrollPane, BorderLayout.CENTER);
        setVisible(false);
    }

    public view.InputPanel getInPanel() {
        return inPanel;
    }

    public view.BtnPanel getBtnPanel() {
        return btnPanel;
    }

    public view.ListPanel getListPanel() {
        return listPanel;
    }
    ///
    public void afficherMessageErreur(String message) {
        JOptionPane.showMessageDialog(this, message, "Erreur", JOptionPane.ERROR_MESSAGE);
    }
    ///
    public void afficherMessageSucces(String message) {
        JOptionPane.showMessageDialog(this, message, "Succès", JOptionPane.INFORMATION_MESSAGE);
    }
}
