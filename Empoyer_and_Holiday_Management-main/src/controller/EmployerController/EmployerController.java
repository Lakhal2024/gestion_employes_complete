package controller;

import enums.Poste;
import enums.Role;
import model.EmployerLogic;
import model.EmployerModel.Employer;
import view.EmployerPanel;
import view.*;

import java.util.List;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

public class EmployerController {
    //private  static EmployerLogic model_employe;
    private static EmployerPanel view;
    private FormFrame frame;
    private LoginFrame loginFrame;
    private EmployerLogic employerLogic;
    private CreateFrame createFrame;

    public EmployerController(FormFrame frame, EmployerLogic employerLogic) {
        this.loginFrame = new LoginFrame();
        this.employerLogic = employerLogic;
        this.frame = frame;
        this.createFrame = new CreateFrame();

        frame.getEmployerPanel().getBtnPanel().getAddBtn().addActionListener(addEvent -> addEmployer());
        frame.getEmployerPanel().getBtnPanel().getUpdateBtn().addActionListener(updateEvent -> updateEmployer());
        frame.getEmployerPanel().getBtnPanel().getRemoveBtn().addActionListener(deleteEvent -> deleteEmployer());
        frame.getEmployerPanel().getBtnPanel().getCreateBtn().addActionListener(createEvent -> createEmployer());
        frame.getEmployerPanel().getBtnPanel().getImportBtn().addActionListener(ImportEvent -> handleImport());
        frame.getEmployerPanel().getBtnPanel().getExportbtn().addActionListener(ExportEvent -> handleExport());

        createFrame.getCreateBtn().addActionListener(createEvent -> createLogin());
        loginFrame.getLoginButton().addActionListener(addEvent -> isValidLogin());

        loadEmployers();
    }


    private void addEmployer() {
        try {

            if (employerLogic.addEmployer(
                    1,
                    frame.getEmployerPanel().getInPanel().getFirstNameField().getText(),
                    frame.getEmployerPanel().getInPanel().getLastNameField().getText(),
                    frame.getEmployerPanel().getInPanel().getEmailField().getText(),
                    Integer.parseInt(frame.getEmployerPanel().getInPanel().getTelephoneNumberField().getText()),
                    Double.parseDouble(frame.getEmployerPanel().getInPanel().getSalaryField().getText()),
                    Role.valueOf(frame.getEmployerPanel().getInPanel().getSelectedRole().toString()),
                    Poste.valueOf(frame.getEmployerPanel().getInPanel().getSelectedPoste().toString())
            )) {
                JOptionPane.showMessageDialog(frame, "Employer added successfully.");
                loadEmployers();
                frame.getEmployerPanel().getInPanel().clearFields();
            } else {
                JOptionPane.showMessageDialog(null, "Failed to ADD employer", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Failed to Add employer", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void updateEmployer() {
        try {
            if (employerLogic.updateEmployer(
                    frame.getEmployerPanel().getListPanel().getSelectedRowId(),
                    frame.getEmployerPanel().getInPanel().getFirstNameField().getText(),
                    frame.getEmployerPanel().getInPanel().getLastNameField().getText(),
                    frame.getEmployerPanel().getInPanel().getEmailField().getText(),
                    Integer.parseInt(frame.getEmployerPanel().getInPanel().getTelephoneNumberField().getText()),
                    Double.parseDouble(frame.getEmployerPanel().getInPanel().getSalaryField().getText()),
                    Role.valueOf(frame.getEmployerPanel().getInPanel().getSelectedRole().toString()),
                    Poste.valueOf(frame.getEmployerPanel().getInPanel().getSelectedPoste().toString())
            )) {
                JOptionPane.showMessageDialog(frame, "Employer updated successfully.");
                loadEmployers();
                frame.getEmployerPanel().getInPanel().clearFields();
            } else {
                JOptionPane.showMessageDialog(null, "Failed to update employer", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Failed to update employer", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }


    private void deleteEmployer() {
        try {
            if (employerLogic.deleteEmployer(frame.getEmployerPanel().getListPanel().getSelectedRowId())) {
                JOptionPane.showMessageDialog(frame, "Employer deleted successfully.");
                loadEmployers();
                frame.getEmployerPanel().getInPanel().clearFields();
            } else {
                JOptionPane.showMessageDialog(null, "Failed to delete employer", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Failed to delete employer", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void loadEmployers() {
        frame.getEmployerPanel().getListPanel().updateEmployerList(employerLogic.getAllEmployers());
    }

    private void isValidLogin() {
        if (employerLogic.handleLogin(loginFrame.getUsername(), loginFrame.getPassword())) {
            loginFrame.setVisible(false);
            frame.setVisible(true);
            if (!employerLogic.handleAccess(loginFrame.getUsername())) {
                System.out.println("This is Employer");
            }
            return;
        } else {
            JOptionPane.showMessageDialog(null, "Invalid username or password", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void createEmployer() {
        if (frame.getEmployerPanel().getListPanel().getSelectedRowId() != -1) {
            createFrame.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(null, "Failed Create Login Please select a row ", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void createLogin() {
        if (employerLogic.createLogin(frame.getEmployerPanel().getListPanel().getSelectedRowId(), createFrame.getUsername(), createFrame.getPassword(), createFrame.getConfirmPassword())) {
            JOptionPane.showMessageDialog(frame, "Login created successfully.");
            createFrame.clearFields();
            createFrame.setVisible(false);
        } else {
            JOptionPane.showMessageDialog(null, "Failed Create Login", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void handleImport() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileFilter(new FileNameExtensionFilter("Fichiers CSV", "txt"));

        if (fileChooser.showOpenDialog(frame.getEmployerPanel()) == JFileChooser.APPROVE_OPTION) {
            try {
                String filePath = fileChooser.getSelectedFile().getAbsolutePath();
                employerLogic.importData(filePath);
                frame.getEmployerPanel().afficherMessageSucces("Importation réussie.");
            } catch (Exception e) {
                frame.getEmployerPanel().afficherMessageErreur("Erreur lors de l'importation :" + e.getMessage());
            }
        }
    }

    private void handleExport() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileFilter(new FileNameExtensionFilter("Fichiers CSV", "txt"));

        if (fileChooser.showSaveDialog(frame.getEmployerPanel()) == JFileChooser.APPROVE_OPTION) {
            try {
                String filePath = fileChooser.getSelectedFile().getAbsolutePath();
                if (!filePath.toLowerCase().endsWith(".txt")) {
                    filePath += ".txt";
                }
                List<Employer> employes = employerLogic.getAllEmployers();
                employerLogic.exportData(filePath, employes);
                frame.getEmployerPanel().afficherMessageSucces("Exportation réussie.");
            } catch (Exception e) {
                frame.getEmployerPanel().afficherMessageErreur("Erreur lors de l'exportation :" + e.getMessage());
            }
        }
    }


}
