package view;

import javax.swing.*;
import java.awt.*;

public class FormFrame extends JFrame {

    private view.EmployerPanel employerPanel;
    private view.HolidayPanel holidayPanel;
    private SwitchPanel switchPanel;

    public FormFrame() {
        
        setTitle("Employer Platform");
        setSize(900, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
    
        employerPanel = new view.EmployerPanel();
        holidayPanel = new view.HolidayPanel();
        switchPanel = new SwitchPanel(employerPanel, holidayPanel);
        add(switchPanel, BorderLayout.CENTER);
        
        
        setVisible(false);
    }

    public view.EmployerPanel getEmployerPanel() {
        return employerPanel;
    }

    public view.HolidayPanel getHolidayPanel() {
        return holidayPanel;
    }

}
