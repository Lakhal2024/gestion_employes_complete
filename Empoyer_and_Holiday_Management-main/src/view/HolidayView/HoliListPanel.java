package view;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import model.Holiday;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class HoliListPanel extends JPanel {

    private JPanel contentPanel;
    private int selectedRowId = -1;
    private List<JPanel> rowPanels = new ArrayList<>();

    public HoliListPanel() {
        setLayout(new BorderLayout());

        JPanel titlePanel = new JPanel();
        titlePanel.setLayout(new GridLayout(1, 5));
        titlePanel.add(createStyledLabel("Id"));
        titlePanel.add(createStyledLabel("Nom"));
        titlePanel.add(createStyledLabel("Start Date"));
        titlePanel.add(createStyledLabel("End Date"));
        titlePanel.add(createStyledLabel("Holiday Type"));
        titlePanel.setBorder(new LineBorder(Color.BLACK));
        add(titlePanel, BorderLayout.NORTH);

        contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        JScrollPane scrollPane = new JScrollPane(contentPanel);
        add(scrollPane, BorderLayout.CENTER);
    }

    public void updateHolidayList(List<Holiday> holidays) {
        contentPanel.removeAll();
        rowPanels.clear();

        for (Holiday holiday : holidays) {
            JPanel rowPanel = new JPanel(new GridLayout(1, 5));
            rowPanel.setBorder(new LineBorder(Color.GRAY));
            rowPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

            JLabel idLabel = createStyledLabel(String.valueOf(holiday.getId()));
            JLabel fullNameLabel = createStyledLabel(holiday.getFullName());
            JLabel startDateLabel = createStyledLabel(holiday.getStartDate().format(formatter));
            JLabel endDateLabel = createStyledLabel(holiday.getEndDate().format(formatter));
            JLabel holidayTypeLabel = createStyledLabel(String.valueOf(holiday.getHolidayType()));

            rowPanel.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    highlightRow(rowPanel, holiday.getId());
                }
            });

            rowPanel.add(idLabel);
            rowPanel.add(fullNameLabel);
            rowPanel.add(startDateLabel);
            rowPanel.add(endDateLabel);
            rowPanel.add(holidayTypeLabel);

            contentPanel.add(rowPanel);
            rowPanels.add(rowPanel);
        }

        contentPanel.revalidate();
        contentPanel.repaint();
    }

    private void highlightRow(JPanel selectedRow, int holidayId) {
        for (JPanel row : rowPanels) {
            row.setBackground(Color.WHITE);
        }

        selectedRow.setBackground(Color.LIGHT_GRAY);
        selectedRowId = holidayId;
    }

    public int getSelectedRowId() {
        return selectedRowId;
    }

    private JLabel createStyledLabel(String text) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("Arial", Font.PLAIN, 14));
        label.setForeground(new Color(70, 70, 70));
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setVerticalAlignment(SwingConstants.CENTER);
        return label;
    }
}
