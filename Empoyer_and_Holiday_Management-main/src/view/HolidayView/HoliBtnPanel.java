package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class HoliBtnPanel extends JPanel {

    private JButton addBtn, removeBtn, updateBtn,expoBTN,impoBTN;

    public HoliBtnPanel() {
        setLayout(new FlowLayout());

        addBtn = new JButton("Add");
        removeBtn = new JButton("Remove");
        updateBtn = new JButton("Update");
        expoBTN =new JButton("export");
        impoBTN =new JButton("import");
        addBtn.setFont(new Font("Arial", Font.PLAIN, 14));
        updateBtn.setFont(new Font("Arial", Font.PLAIN, 14));
        removeBtn.setFont(new Font("Arial", Font.PLAIN, 14));
        expoBTN.setFont(new Font("Arial",Font.PLAIN,14));
        impoBTN.setFont(new Font("Arial",Font.PLAIN,14));

        addButtonHoverEffect(addBtn);
        addButtonHoverEffect(removeBtn);
        addButtonHoverEffect(updateBtn);
        addButtonHoverEffect(expoBTN);
        addButtonHoverEffect(impoBTN);

        add(addBtn);
        add(removeBtn);
        add(updateBtn);
        add(expoBTN);
        add(impoBTN);
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

    public JButton getExpoBTN() {
        return expoBTN;
    }

    public JButton getImpoBTN() {
        return impoBTN;
    }
}
