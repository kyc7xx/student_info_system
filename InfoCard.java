package sis.ui;

import sis.components.RoundPanel;
import sis.models.CardData;
import javax.swing.*;
import java.awt.*;

public class InfoCard extends RoundPanel {
    private CardData data;
    private JLabel titleLabel;
    private JLabel valueLabel;

    public InfoCard(CardData data) {
        this.data = data;
        setLayout(new BorderLayout(10, 10));
        setBackground(Color.WHITE);
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        initComponents();
    }

    private void initComponents() {
        JPanel textPanel = new JPanel(new GridLayout(2, 1, 0, 5));
        textPanel.setOpaque(false);

        titleLabel = new JLabel(data.getTitle());
        titleLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
        titleLabel.setForeground(new Color(120, 120, 120));

        valueLabel = new JLabel(data.getValue());
        valueLabel.setFont(new Font("Tahoma", Font.BOLD, 28));
        valueLabel.setForeground(new Color(85, 85, 85));

        textPanel.add(titleLabel);
        textPanel.add(valueLabel);

        add(textPanel, BorderLayout.CENTER);
    }

    public void updateValue(String value) {
        valueLabel.setText(value);
    }
}