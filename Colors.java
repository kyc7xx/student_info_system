package sis.utils;

import java.awt.Color;

public class Colors {

    // PRIMARY COLORS
    public static final Color CHARCOAL = new Color(60, 60, 60);       // Charcoal
    public static final Color CHARCOAL_DARK = new Color(40, 40, 40);  // Dark Charcoal
    public static final Color CHARCOAL_LIGHT = new Color(100, 100, 100); // Light Charcoal

    // BACKGROUND COLORS
    public static final Color VAPOROUS_GRAY = new Color(230, 224, 218); // Vaporous Gray
    public static final Color MULBERRY = new Color(114, 56, 61);       // Mulberry
    public static final Color WHITE = Color.WHITE;                     // White
    public static final Color LIGHT_GRAY = new Color(245, 245, 245);   // Light Gray
    public static final Color ACCENT_GRAY = new Color(240, 240, 240);  // Accent Gray

    // TEXT COLORS
    public static final Color CAVIAR = new Color(44, 43, 44);          // Caviar
    public static final Color TEXT_WHITE = Color.WHITE;                // White
    public static final Color BLUE = new Color(73, 118, 186);          // Blue
    public static final Color GREEN = new Color(19, 134, 54);          // Green
    public static final Color YELLOW = new Color(255, 188, 17);        // Yellow
    public static final Color RED = new Color(201, 20, 50);            // Red

    // BORDER COLORS
    public static final Color BORDER_NEUTRAL = new Color(210, 205, 200); // Neutral Gray
    public static final Color BORDER_LIGHT = new Color(240, 240, 240);   // Light Gray
    public static final Color BORDER_DARK = new Color(160, 160, 160);    // Dark Gray

    public static Color getColor(String name) {
        switch (name.toUpperCase()) {
            case "INFO": return BLUE;     // Blue
            case "SUCCESS": return GREEN; // Green
            case "WARNING": return YELLOW; // Yellow
            case "DANGER": return RED;    // Red
            default: return CAVIAR;       // Caviar
        }
    }
}