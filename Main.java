package sis.Main;

import javax.swing.*;
import sis.ui.MainFrame;
import sis.forms.LoginForm;

public class Main {
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        SwingUtilities.invokeLater(() -> {
            JFrame loginFrame = new JFrame("Student Information System - Login");
            loginFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            loginFrame.setSize(800, 600);
            loginFrame.setLocationRelativeTo(null);

            LoginForm loginForm = new LoginForm(() -> {
                loginFrame.dispose();
                MainFrame mainFrame = new MainFrame();
                mainFrame.setVisible(true);
            });

            loginFrame.add(loginForm);
            loginFrame.setVisible(true);
        });
    }
}