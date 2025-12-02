package sis.forms;

import sis.components.RoundPanel;
import sis.utils.Colors;
import javax.swing.*;
import java.awt.*;

public class ReportForm extends JPanel {

    public ReportForm() {
        setLayout(new BorderLayout());
        setBackground(Colors.VAPOROUS_GRAY);
        setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));
        initComponents();
    }

    private void initComponents() {
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setOpaque(false);

        RoundPanel statsPanel = createStatsPanel();
        statsPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 350));

        mainPanel.add(statsPanel);

        add(mainPanel, BorderLayout.NORTH);
    }

    private RoundPanel createStatsPanel() {
        RoundPanel panel = new RoundPanel();
        panel.setBackground(Color.WHITE);
        panel.setLayout(new BorderLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel title = new JLabel("Quick Statistics");
        title.setFont(new Font("Tahoma", Font.BOLD, 18));
        title.setForeground(Colors.CAVIAR);
        title.setBorder(BorderFactory.createEmptyBorder(0, 0, 20, 0));

        JPanel statsGrid = new JPanel(new GridLayout(2, 2, 20, 20));
        statsGrid.setOpaque(false);

        statsGrid.add(createStatCard("Total Enrollment", "7", Colors.BLUE));
        statsGrid.add(createStatCard("Active Courses", "24", Colors.GREEN));
        statsGrid.add(createStatCard("Staff Members", "6", Colors.YELLOW));
        statsGrid.add(createStatCard("Completion Rate", "92%", Colors.RED));

        panel.add(title, BorderLayout.NORTH);
        panel.add(statsGrid, BorderLayout.CENTER);

        return panel;
    }

    private JPanel createStatCard(String label, String value, Color color) {
        JPanel card = new JPanel(new BorderLayout(10, 10));
        card.setBackground(Colors.LIGHT_GRAY);
        card.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Colors.BORDER_DARK, 1),
                BorderFactory.createEmptyBorder(20, 20, 20, 20)
        ));

        JLabel valueLabel = new JLabel(value);
        valueLabel.setFont(new Font("Tahoma", Font.BOLD, 36));
        valueLabel.setForeground(color);
        valueLabel.setHorizontalAlignment(SwingConstants.CENTER);

        JLabel labelComponent = new JLabel(label);
        labelComponent.setFont(new Font("Tahoma", Font.PLAIN, 14));
        labelComponent.setForeground(Colors.CAVIAR);
        labelComponent.setHorizontalAlignment(SwingConstants.CENTER);

        card.add(valueLabel, BorderLayout.CENTER);
        card.add(labelComponent, BorderLayout.SOUTH);

        return card;
    }
}