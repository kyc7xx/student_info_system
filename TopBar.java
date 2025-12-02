package sis.ui;

import sis.utils.Colors;
import javax.swing.*;
import java.awt.*;

public class TopBar extends JPanel {
    private JLabel titleLabel;

    public TopBar() {
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);
        setPreferredSize(new Dimension(0, 60));
        setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Colors.BORDER_NEUTRAL));
        initComponents();
    }

    private void initComponents() {
        titleLabel = new JLabel("Dashboard");
        titleLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
        titleLabel.setForeground(Colors.CAVIAR);
        titleLabel.setBorder(BorderFactory.createEmptyBorder(0, 30, 0, 0));

        UserProfile userProfile = new UserProfile();

        add(titleLabel, BorderLayout.WEST);
        add(userProfile, BorderLayout.EAST);
    }

    public void setTitle(String title) {
        titleLabel.setText(title);
    }
}