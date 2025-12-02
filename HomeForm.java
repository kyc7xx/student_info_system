package sis.forms;

import sis.components.RoundPanel;
import sis.utils.Colors;
import sis.database.StudentDAO;
import sis.database.StaffDAO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class HomeForm extends JPanel {
    private JLabel timeLabel;
    
    // DAO objects para sa statistics
    private StudentDAO studentDAO;
    private StaffDAO staffDAO;
    
    // Labels para sa dynamic statistics
    private JLabel totalStudentsLabel;
    private JLabel totalStaffLabel;

    public HomeForm() {
        setLayout(new BorderLayout(20, 20));
        setBackground(Colors.VAPOROUS_GRAY);
        setBorder(new EmptyBorder(30, 30, 30, 30));
        
        // Initialize DAOs
        studentDAO = new StudentDAO();
        staffDAO = new StaffDAO();
        
        initComponents();
        startClock();
    }

    private void initComponents() {
        JPanel headerPanel = createHeaderPanel();
        JPanel topCardsPanel = createTopCardsPanel();
        JPanel bottomPanel = createBottomPanel();

        add(headerPanel, BorderLayout.NORTH);
        add(topCardsPanel, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);
    }

    private JPanel createHeaderPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setOpaque(false);
        panel.setBorder(new EmptyBorder(0, 0, 20, 0));
        
        JPanel welcomePanel = new JPanel(new GridLayout(2, 1, 0, 5));
        welcomePanel.setOpaque(false);

        JLabel welcome = new JLabel("Welcome Back, Admin!");
        welcome.setFont(new Font("Tahoma", Font.BOLD, 28));
        welcome.setForeground(Colors.CAVIAR);

        JLabel subtitle = new JLabel("Here's what's happening with your institution today");
        subtitle.setFont(new Font("Tahoma", Font.PLAIN, 14));
        subtitle.setForeground(Colors.CAVIAR);

        welcomePanel.add(welcome);
        welcomePanel.add(subtitle);

        timeLabel = new JLabel();
        timeLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
        timeLabel.setForeground(Colors.CAVIAR);

        panel.add(welcomePanel, BorderLayout.WEST);
        panel.add(timeLabel, BorderLayout.EAST);

        return panel;
    }

    private JPanel createTopCardsPanel() {
        JPanel grid = new JPanel(new GridLayout(1, 4, 10, 0));
        grid.setOpaque(false);
        grid.setBorder(new EmptyBorder(0, 0, 10, 0));
        grid.setMaximumSize(new Dimension(Integer.MAX_VALUE, 120));

        // Get real data from database
        int totalStudents = studentDAO.getTotalStudents();
        int totalStaff = staffDAO.getTotalStaff();
        
        // Create cards with real data
        grid.add(createInfoCard("Total Students", String.valueOf(totalStudents), Colors.BLUE));
        grid.add(createInfoCard("Total Staff", String.valueOf(totalStaff), Colors.GREEN));
        grid.add(createInfoCard("Courses", "24", Colors.YELLOW));
        grid.add(createInfoCard("Dropout Rate", "2.18%", Colors.RED));

        return grid;
    }

    private JPanel createInfoCard(String label, String value, Color color) {
        RoundPanel card = new RoundPanel();
        card.setBackground(Color.WHITE);
        card.setLayout(new BorderLayout());
        card.setBorder(new EmptyBorder(15, 15, 15, 15));

        JLabel valueLabel = new JLabel(value);
        valueLabel.setFont(new Font("Tahoma", Font.BOLD, 32));
        valueLabel.setForeground(color);

        JLabel nameLabel = new JLabel(label);
        nameLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
        nameLabel.setForeground(Colors.CAVIAR);

        JPanel textPanel = new JPanel(new GridLayout(2, 1, 0, 5));
        textPanel.setOpaque(false);
        textPanel.add(valueLabel);
        textPanel.add(nameLabel);

        card.add(textPanel, BorderLayout.CENTER);

        return card;
    }

    private JPanel createBottomPanel() {
        JPanel panel = new JPanel(new GridLayout(1, 2, 20, 0));
        panel.setOpaque(false);
        panel.setPreferredSize(new Dimension(0, 300));

        panel.add(createPerformancePanel());
        panel.add(createQuickStatsPanel());

        return panel;
    }

    private RoundPanel createPerformancePanel() {
        RoundPanel panel = new RoundPanel();
        panel.setBackground(Color.WHITE);
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(new EmptyBorder(20, 20, 20, 20));
        panel.setPreferredSize(new Dimension(0, 300));

        JLabel title = new JLabel("Department Performance");
        title.setFont(new Font("Tahoma", Font.BOLD, 16));
        title.setForeground(Colors.CAVIAR);
        title.setAlignmentX(Component.LEFT_ALIGNMENT);

        panel.add(title);
        panel.add(Box.createVerticalStrut(15));

        String[] departments = {"Computer Science", "Mathematics", "Engineering", "Business"};
        int[] values = {85, 72, 91, 68};

        for (int i = 0; i < departments.length; i++) {
            panel.add(createProgressItem(departments[i], values[i]));
            panel.add(Box.createVerticalStrut(10));
        }

        return panel;
    }

    private JPanel createProgressItem(String name, int value) {
        JPanel panel = new JPanel(new BorderLayout(10, 5));
        panel.setOpaque(false);
        panel.setAlignmentX(Component.LEFT_ALIGNMENT);
        panel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));

        JLabel label = new JLabel(name + " - " + value + "%");
        label.setFont(new Font("Tahoma", Font.PLAIN, 12));
        label.setForeground(Colors.CAVIAR);

        JProgressBar progressBar = new JProgressBar(0, 100);
        progressBar.setValue(value);
        progressBar.setForeground(Colors.WHITE);
        progressBar.setBackground(Colors.VAPOROUS_GRAY);
        progressBar.setPreferredSize(new Dimension(0, 8));
        progressBar.setBorderPainted(false);
        progressBar.setStringPainted(false);

        panel.add(label, BorderLayout.NORTH);
        panel.add(progressBar, BorderLayout.CENTER);

        return panel;
    }

    private RoundPanel createQuickStatsPanel() {
        RoundPanel panel = new RoundPanel();
        panel.setBackground(Colors.VAPOROUS_GRAY);
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(new EmptyBorder(20, 20, 20, 20));
        panel.setPreferredSize(new Dimension(0, 300));

        JLabel gpaLabel = new JLabel("2.27");
        gpaLabel.setFont(new Font("Tahoma", Font.BOLD, 48));
        gpaLabel.setForeground(Colors.CAVIAR);
        gpaLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel gpaText = new JLabel("Average GPA");
        gpaText.setFont(new Font("Tahoma", Font.PLAIN, 14));
        gpaText.setForeground(Colors.CAVIAR);
        gpaText.setAlignmentX(Component.LEFT_ALIGNMENT);

        panel.add(Box.createVerticalGlue());
        panel.add(gpaLabel);
        panel.add(Box.createVerticalStrut(10));
        panel.add(gpaText);
        panel.add(Box.createVerticalGlue());

        return panel;
    }

    private void startClock() {
        Timer timer = new Timer(1000, e -> {
            String time = new SimpleDateFormat("EEEE, MMM dd, yyyy  |  HH:mm:ss").format(new Date());
            timeLabel.setText(time);
            timeLabel.setForeground(new Color(160, 82, 45));
        });
        timer.start();
    }
}
