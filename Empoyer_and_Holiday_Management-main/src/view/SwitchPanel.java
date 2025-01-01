package view;

import javax.swing.*;
import java.awt.*;

public class SwitchPanel extends JTabbedPane {
    private view.EmployerPanel employerPanel;
    private view.HolidayPanel holidayPanel;

    public SwitchPanel(view.EmployerPanel employerPanel, view.HolidayPanel holidayPanel) {
        this.employerPanel = employerPanel;
        this.holidayPanel = holidayPanel;

        addTab("Employer", employerPanel);
        addTab("Holiday", holidayPanel);

        styleTabs();

        setTabPlacement(JTabbedPane.TOP);
        setTabLayoutPolicy(JTabbedPane.WRAP_TAB_LAYOUT);
    }

    private void styleTabs() {
        setFont(new Font("Arial", Font.PLAIN, 16));
        setBackground(Color.WHITE);
        setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200)));
    }

}
