package sis.ui;

import sis.utils.Colors;
import javax.swing.*;
import java.awt.*;

public class UserProfile extends JPanel {

    public UserProfile() {
        setLayout(new FlowLayout(FlowLayout.RIGHT, 15, 10));
        setBackground(Color.WHITE);
        setPreferredSize(new Dimension(300, 60));
        initComponents();
    }

    private void initComponents() {
        JPanel infoPanel = new JPanel(new GridLayout(2, 1));
        infoPanel.setOpaque(false);

        JLabel nameLabel = new JLabel("Admin User");
        nameLabel.setFont(new Font("Tahoma", Font.BOLD, 13));
        nameLabel.setForeground(Colors.CAVIAR);

        JLabel roleLabel = new JLabel("Administrator");
        roleLabel.setFont(new Font("Tahoma", Font.PLAIN, 11));
        roleLabel.setForeground(Colors.CAVIAR);

        infoPanel.add(nameLabel);
        infoPanel.add(roleLabel);

        add(infoPanel);
    }
}