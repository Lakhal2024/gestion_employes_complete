package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class BtnPanel extends JPanel {

    private JButton addBtn, removeBtn, updateBtn, createBtn,exportbtn,importBtn;


    public BtnPanel() {
        setLayout(new FlowLayout());

        addBtn = new JButton("Add");
        removeBtn = new JButton("Remove");
        updateBtn = new JButton("Update");
        createBtn = new JButton("Create");
        exportbtn =new JButton("exporte");
        importBtn =new JButton("importe");
        addBtn.setFont(new Font("Arial", Font.PLAIN, 14));
        removeBtn.setFont(new Font("Arial", Font.PLAIN, 14));
        updateBtn.setFont(new Font("Arial", Font.PLAIN, 14));
        createBtn.setFont(new Font("Arial", Font.PLAIN, 14));
        exportbtn.setFont(new Font("Arial",Font.PLAIN,14));
        importBtn.setFont(new Font("Arial",Font.PLAIN,14));


        addButtonHoverEffect(addBtn);
        addButtonHoverEffect(removeBtn);
        addButtonHoverEffect(updateBtn);
        addButtonHoverEffect(createBtn);addButtonHoverEffect(exportbtn);addButtonHoverEffect(importBtn);

        add(addBtn);
        add(removeBtn);
        add(updateBtn);
        add(createBtn);
        add(exportbtn);
        add(importBtn);
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

    public JButton getAddBtn() {
        return addBtn;
    }

    public JButton getRemoveBtn() {
        return removeBtn;
    }

    public JButton getUpdateBtn() {
        return updateBtn;
    }

    public JButton getCreateBtn() {
        return createBtn;
    }

    public JButton getExportbtn() {
        return exportbtn;
    }

    public JButton getImportBtn() {
        return importBtn;
    }
}
