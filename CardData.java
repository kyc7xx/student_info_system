package sis.models;

import java.awt.Color;

public class CardData {
    private String title;
    private String value;
    private Color color;

    public CardData(String title, String value, Color color, String icon) {
        this.title = title;
        this.value = value;
        this.color = color;
    }

    public String getTitle() { 
        return title; 
    }

    public String getValue() { 
        return value; 
    }

    public Color getColor() { 
        return color; 
    }

    public void setTitle(String title) { 
        this.title = title; 
    }

    public void setValue(String value) { 
        this.value = value; 
    }

    public void setColor(Color color) { 
        this.color = color; 
    }
}