package sis.components;

import javax.swing.*;
import java.awt.*;

public class RoundPanel extends JPanel {
    private int cornerRadius = 15;
    
    public RoundPanel() {
        super();
        setOpaque(false);
    }
    
    public RoundPanel(LayoutManager layout) {
        super(layout);
        setOpaque(false);
    }
    
    public RoundPanel(int radius) {
        super();
        this.cornerRadius = radius;
        setOpaque(false);
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(getBackground());
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), cornerRadius, cornerRadius);
        g2.dispose();
    }
}