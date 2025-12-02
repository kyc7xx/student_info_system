package sis.ui;

import sis.utils.Colors;
import sis.utils.MenuListener;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

public class SideMenu extends JPanel {
    private List<MenuListener> listeners = new ArrayList<>();
    private List<JPanel> menuItems = new ArrayList<>();
    private int selectedIndex = 0;
    private JLabel activeLabel = null;

    public SideMenu() {
        setLayout(new BorderLayout());
        setBackground(Colors.MULBERRY);
        setPreferredSize(new Dimension(250, 0));
        initComponents();
    }

    private void initComponents() {
        JPanel logoPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 30));
        logoPanel.setBackground(Colors.MULBERRY);
        JLabel logoLabel = new JLabel("SIS");
        logoLabel.setFont(new Font("Tahoma", Font.BOLD, 28));
        logoLabel.setForeground(Color.WHITE);
        logoPanel.add(logoLabel);

        JPanel menuPanel = new JPanel();
        menuPanel.setLayout(new BoxLayout(menuPanel, BoxLayout.Y_AXIS));
        menuPanel.setBackground(Colors.MULBERRY);
        menuPanel.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));

        String[] menus = {"Home", "Students", "Staff", "Reports"};

        for (int i = 0; i < menus.length; i++) {
            JPanel item = createMenuItem(menus[i], i);
            menuItems.add(item);
            menuPanel.add(item);
            menuPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        }

        add(logoPanel, BorderLayout.NORTH);
        add(menuPanel, BorderLayout.CENTER);

        selectMenuItem(0);
    }

    private JPanel createMenuItem(String menuName, int index) {
        JPanel item = new JPanel(new FlowLayout(FlowLayout.LEFT, 30, 15));
        item.setBackground(Colors.MULBERRY);
        item.setMaximumSize(new Dimension(250, 50));
        item.setCursor(new Cursor(Cursor.HAND_CURSOR));

        JLabel nameLabel = new JLabel(menuName);
        nameLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
        nameLabel.setForeground(Color.WHITE);
        item.add(nameLabel);

        item.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                selectMenuItem(index);
                setActiveLabel(nameLabel);
                notifyListeners(menuName);
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                if (index != selectedIndex) {
                    item.setBackground(Colors.CHARCOAL_DARK);
                }
            }

            @Override
            public void mouseExited(MouseEvent e) {
                if (index != selectedIndex) {
                    item.setBackground(Colors.MULBERRY);
                }
            }
        });

        return item;
    }

    private void selectMenuItem(int index) {
        if (selectedIndex >= 0 && selectedIndex < menuItems.size()) {
            menuItems.get(selectedIndex).setBackground(Colors.MULBERRY);
        }
        selectedIndex = index;
        menuItems.get(index).setBackground(Colors.CHARCOAL);
    }

    private void setActiveLabel(JLabel label) {
        if (activeLabel != null) {
            activeLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
        }
        label.setFont(new Font("Tahoma", Font.BOLD, 15));
        activeLabel = label;
    }

    public void addMenuListener(MenuListener listener) {
        listeners.add(listener);
    }

    private void notifyListeners(String menuName) {
        for (MenuListener listener : listeners) {
            listener.onMenuSelected(menuName);
        }
    }
}