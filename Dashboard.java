package sis.ui;

import sis.components.CustomScrollBar;
import sis.utils.Colors;
import javax.swing.*;
import java.awt.*;

public class Dashboard extends JPanel {
    private JPanel contentPanel;
    private CardLayout cardLayout;

    public Dashboard() {
        setLayout(new BorderLayout());
        setBackground(Colors.VAPOROUS_GRAY);
        initComponents();
    }

    private void initComponents() {
        cardLayout = new CardLayout();
        contentPanel = new JPanel(cardLayout);
        contentPanel.setBackground(Colors.VAPOROUS_GRAY);

        JScrollPane scrollPane = new JScrollPane(contentPanel);
        scrollPane.setBorder(null);
        scrollPane.setVerticalScrollBar(new CustomScrollBar());
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);

        add(scrollPane, BorderLayout.CENTER);
    }

    public void addForm(String name, JPanel form) {
        contentPanel.add(form, name);
    }

    public void showForm(String name) {
        cardLayout.show(contentPanel, name);
    }
}