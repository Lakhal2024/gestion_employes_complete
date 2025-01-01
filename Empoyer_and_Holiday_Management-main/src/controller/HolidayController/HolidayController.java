package controller;

import enums.HolidayType;
import model.Holiday;
import model.HolidayLogic;
import view.*;

import java.util.List;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

public class HolidayController {

    private FormFrame frame;
    private HolidayLogic holidayLogic;

    public HolidayController(FormFrame frame, HolidayLogic holidayLogic) {
        this.holidayLogic = holidayLogic;
        this.frame = frame;

        frame.getHolidayPanel().getBtnPanel().getAddBtn().addActionListener(addEvent -> addHoliday());
        frame.getHolidayPanel().getBtnPanel().getUpdateBtn().addActionListener(updateEvent -> updateHoliday());
        frame.getHolidayPanel().getBtnPanel().getRemoveBtn().addActionListener(deleteEvent -> deleteHoliday());
        frame.getHolidayPanel().getBtnPanel().getExpoBTN().addActionListener(expoEvent -> handleExport());
        frame.getHolidayPanel().getBtnPanel().getImpoBTN().addActionListener(impoEvent -> handleImport());
        loadHolidays();
    }

    private void addHoliday() {
        try {
            if (holidayLogic.addHoliday(
                    1,
                    frame.getEmployerPanel().getListPanel().getSelectedRowId(),
                    "fulll name",
                    frame.getHolidayPanel().getInPanel().getStartDate(),
                    frame.getHolidayPanel().getInPanel().getEndDate(),
                    HolidayType.valueOf(frame.getHolidayPanel().getInPanel().getSelectedHolidayType().toString())
                ))    
            {
                JOptionPane.showMessageDialog(frame, "Holiday added successfully.");
                loadHolidays();
                frame.getHolidayPanel().getInPanel().clearFields();
            } else {
                JOptionPane.showMessageDialog(null, "Failed to add Holiday", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Failed to add Holiday", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void updateHoliday() {
        try {
            if (holidayLogic.updateHoliday(
                       frame.getHolidayPanel().getListPanel().getSelectedRowId(),
                       frame.getEmployerPanel().getListPanel().getSelectedRowId(),
                       "fulll name",
                       frame.getHolidayPanel().getInPanel().getStartDate(),
                       frame.getHolidayPanel().getInPanel().getEndDate(),
                       HolidayType.valueOf(frame.getHolidayPanel().getInPanel().getSelectedHolidayType().toString())
               )) 
            {
               JOptionPane.showMessageDialog(frame, "Holiday updated successfully.");
               loadHolidays();
               frame.getHolidayPanel().getInPanel().clearFields();
            } else {
               JOptionPane.showMessageDialog(null, "Failed to update Holiday", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception e) {
           JOptionPane.showMessageDialog(null, "Failed to update Holiday", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void deleteHoliday() {
     try {
         if (holidayLogic.deleteHoliday(frame.getHolidayPanel().getListPanel().getSelectedRowId())) {
            JOptionPane.showMessageDialog(frame, "Holiday deleted successfully.");
            loadHolidays();
            frame.getHolidayPanel().getInPanel().clearFields();
         } else {
            JOptionPane.showMessageDialog(null, "Failed to delete Holiday", "Error", JOptionPane.ERROR_MESSAGE);
         }
     } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "Failed to delete Holiday", "Error", JOptionPane.ERROR_MESSAGE);
     }
    }

    private void loadHolidays() {
        frame.getHolidayPanel().getListPanel().updateHolidayList(holidayLogic.getAllHolidays());
    }
    private void handleImport() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileFilter(new FileNameExtensionFilter("Fichiers CSV", "txt"));

        if (fileChooser.showOpenDialog(frame.getHolidayPanel()) == JFileChooser.APPROVE_OPTION) {
            try {
                String filePath = fileChooser.getSelectedFile().getAbsolutePath();
                holidayLogic.importData(filePath);
                frame.getHolidayPanel().afficherMessageSucces("Importation réussie.");
            } catch (Exception e) {
                frame.getHolidayPanel().afficherMessageErreur("Erreur lors de l'importation :" + e.getMessage());
            }
        }
    }

    private void handleExport() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileFilter(new FileNameExtensionFilter("Fichiers CSV", "txt"));

        if (fileChooser.showSaveDialog(frame.getHolidayPanel()) == JFileChooser.APPROVE_OPTION) {
            try {
                String filePath = fileChooser.getSelectedFile().getAbsolutePath();
                if (!filePath.toLowerCase().endsWith(".txt")) {
                    filePath += ".txt";
                }
                List<Holiday> holidays = holidayLogic.getAllHolidays();
                holidayLogic.exportData(filePath, holidays);
                frame.getHolidayPanel().afficherMessageSucces("Exportation réussie.");
            } catch (Exception e) {
                frame.getHolidayPanel().afficherMessageErreur("Erreur lors de l'exportation :" + e.getMessage());
            }
        }
    }
}