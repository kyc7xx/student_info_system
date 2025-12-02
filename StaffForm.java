package sis.forms;

import sis.components.*;
import sis.utils.Colors;
import sis.database.StaffDAO;
import sis.models.Staff;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class StaffForm extends JPanel {
    private CustomTable table;
    private DefaultTableModel tableModel;
    private JTextField searchField;

    private StaffDAO staffDAO;

    public StaffForm() {
        setLayout(new BorderLayout());
        setBackground(Colors.VAPOROUS_GRAY);
        setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));

        staffDAO = new StaffDAO();

        initComponents();
    }

    private void initComponents() {
        JPanel mainPanel = new JPanel(new BorderLayout(0, 20));
        mainPanel.setOpaque(false);

        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0));
        topPanel.setOpaque(false);

        searchField = new JTextField(20);
        searchField.setFont(new Font("Tahoma", Font.PLAIN, 14));
        searchField.setForeground(Colors.CAVIAR);

        CustomButton searchButton = new CustomButton("Search");
        searchButton.setPreferredSize(new Dimension(100, 35));
        searchButton.addActionListener(e -> searchStaff());
        searchButton.setFont(new Font("Tahoma", Font.PLAIN, 14));

        CustomButton refreshButton = new CustomButton("Refresh");
        refreshButton.setPreferredSize(new Dimension(100, 35));
        refreshButton.addActionListener(e -> loadStaffFromDatabase());
        refreshButton.setFont(new Font("Tahoma", Font.PLAIN, 14));

        topPanel.add(searchField);
        topPanel.add(searchButton);
        topPanel.add(refreshButton);

        RoundPanel tablePanel = createTablePanel();

        mainPanel.add(topPanel, BorderLayout.NORTH);
        mainPanel.add(tablePanel, BorderLayout.CENTER);

        add(mainPanel, BorderLayout.CENTER);
    }

    private RoundPanel createTablePanel() {
        RoundPanel panel = new RoundPanel();
        panel.setBackground(Color.WHITE);
        panel.setLayout(new BorderLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel title = new JLabel("Staff List");
        title.setFont(new Font("Tahoma", Font.BOLD, 18));
        title.setForeground(Colors.CAVIAR);
        title.setBorder(BorderFactory.createEmptyBorder(0, 0, 15, 0));

        String[] columns = {"Name", "Email", "Phone", "Department", "Status"};

        tableModel = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        table = new CustomTable();
        table.setModel(tableModel);

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBorder(null);
        scrollPane.setVerticalScrollBar(new CustomScrollBar());

        panel.add(title, BorderLayout.NORTH);
        panel.add(scrollPane, BorderLayout.CENTER);

        loadStaffFromDatabase();

        return panel;
    }

    private void loadStaffFromDatabase() {
        tableModel.setRowCount(0);

        List<Staff> staffList = staffDAO.getAllStaff();

        for (Staff staff : staffList) {
            Object[] row = {
                staff.getName(),
                staff.getEmail(),
                staff.getPhone(),
                staff.getDepartment(),
                staff.getStatus()
            };
            tableModel.addRow(row);
        }

        searchField.setText("");
    }

    private void searchStaff() {
        String keyword = searchField.getText().trim();

        if (keyword.isEmpty()) {
            loadStaffFromDatabase();
            return;
        }

        tableModel.setRowCount(0);

        List<Staff> results = staffDAO.searchStaff(keyword);

        for (Staff staff : results) {
            Object[] row = {
                staff.getName(),
                staff.getEmail(),
                staff.getPhone(),
                staff.getDepartment(),
                staff.getStatus()
            };
            tableModel.addRow(row);
        }

        if (results.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                "No staff found matching: " + keyword,
                "Search Result",
                JOptionPane.INFORMATION_MESSAGE);
        }
    }
}