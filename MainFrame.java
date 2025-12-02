package sis.ui;

import sis.forms.*;
import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    private SideMenu sideMenu;
    private TopBar topBar;
    private Dashboard dashboard;

    public MainFrame() {
        setTitle("Student Information System");
        setSize(1400, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        initComponents();
    }

    private void initComponents() {
        setLayout(new BorderLayout());

        sideMenu = new SideMenu();
        topBar = new TopBar();
        dashboard = new Dashboard();

        dashboard.addForm("Home", new HomeForm());
        dashboard.addForm("Students", new StudentForm());
        dashboard.addForm("Staff", new StaffForm());
        dashboard.addForm("Reports", new ReportForm());

        sideMenu.addMenuListener(menuName -> {
            dashboard.showForm(menuName);
            topBar.setTitle(menuName);
        });

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.add(topBar, BorderLayout.NORTH);
        mainPanel.add(dashboard, BorderLayout.CENTER);

        add(sideMenu, BorderLayout.WEST);
        add(mainPanel, BorderLayout.CENTER);
    }
}