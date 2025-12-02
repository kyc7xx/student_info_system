package sis.forms;

import sis.components.*;
import sis.utils.Colors;
import sis.database.StudentDAO;
import sis.models.Student;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class StudentForm extends JPanel {
    private CustomTable table;
    private DefaultTableModel tableModel;
    private JTextField searchField;
    private JTextField idField, nameField, emailField, phoneField, courseField;
    private JComboBox<String> statusBox;
    
    private StudentDAO studentDAO;
    
    public StudentForm() {
        setLayout(new BorderLayout());
        setBackground(Colors.VAPOROUS_GRAY);
        setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));
        
        studentDAO = new StudentDAO();
        
        initComponents();
    }
    
    private void initComponents() {
        JPanel mainPanel = new JPanel(new BorderLayout(0, 20));
        mainPanel.setOpaque(false);
        
        RoundPanel formPanel = createFormPanel();
        
        JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0));
        searchPanel.setOpaque(false);
        
        searchField = new JTextField(20);
        searchField.setFont(new Font("Tahoma", Font.PLAIN, 14));
        searchField.setForeground(Colors.CAVIAR);
        
        CustomButton searchButton = new CustomButton("Search");
        searchButton.setPreferredSize(new Dimension(100, 35));
        searchButton.addActionListener(e -> searchStudent());
        searchButton.setFont(new Font("Tahoma", Font.PLAIN, 12));
        
        CustomButton refreshButton = new CustomButton("Refresh");
        refreshButton.setPreferredSize(new Dimension(100, 35));
        refreshButton.addActionListener(e -> loadStudentsFromDatabase());
        refreshButton.setFont(new Font("Tahoma", Font.PLAIN, 12));
        
        searchPanel.add(searchField);
        searchPanel.add(searchButton);
        searchPanel.add(refreshButton);
        
        RoundPanel tablePanel = createTablePanel();
        
        JPanel topSection = new JPanel(new BorderLayout(0, 20));
        topSection.setOpaque(false);
        topSection.add(formPanel, BorderLayout.NORTH);
        topSection.add(searchPanel, BorderLayout.SOUTH);
        
        mainPanel.add(topSection, BorderLayout.NORTH);
        mainPanel.add(tablePanel, BorderLayout.CENTER);
        
        add(mainPanel, BorderLayout.CENTER);
    }
    
    private RoundPanel createFormPanel() {
        RoundPanel panel = new RoundPanel();
        panel.setBackground(Color.WHITE);
        panel.setLayout(new BorderLayout(0, 15));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        JLabel title = new JLabel("Student Management");
        title.setFont(new Font("Tahoma", Font.BOLD, 18));
        title.setForeground(Colors.CAVIAR);
        
        JPanel fieldsPanel = new JPanel(new GridLayout(2, 3, 15, 15));
        fieldsPanel.setOpaque(false);
        
        idField = new JTextField();
        idField.setFont(new Font("Tahoma", Font.PLAIN, 14));
        idField.setForeground(Colors.CAVIAR);
        
        nameField = new JTextField();
        nameField.setFont(new Font("Tahoma", Font.PLAIN, 14));
        nameField.setForeground(Colors.CAVIAR);
        
        emailField = new JTextField();
        emailField.setFont(new Font("Tahoma", Font.PLAIN, 14));
        emailField.setForeground(Colors.CAVIAR);
        
        phoneField = new JTextField();
        phoneField.setFont(new Font("Tahoma", Font.PLAIN, 14));
        phoneField.setForeground(Colors.CAVIAR);
        
        courseField = new JTextField();
        courseField.setFont(new Font("Tahoma", Font.PLAIN, 14));
        courseField.setForeground(Colors.CAVIAR);
        
        String[] statuses = {"Active", "Inactive", "Graduated"};
        statusBox = new JComboBox<>(statuses);
        statusBox.setFont(new Font("Tahoma", Font.PLAIN, 14));
        statusBox.setForeground(Colors.CAVIAR);
        
        fieldsPanel.add(createFieldPanel("Student ID:", idField));
        fieldsPanel.add(createFieldPanel("Full Name:", nameField));
        fieldsPanel.add(createFieldPanel("Email:", emailField));
        fieldsPanel.add(createFieldPanel("Phone:", phoneField));
        fieldsPanel.add(createFieldPanel("Course:", courseField));
        fieldsPanel.add(createFieldPanel("Status:", statusBox));
        
        JPanel buttonsPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 0));
        buttonsPanel.setOpaque(false);
        
        CustomButton addButton = new CustomButton("Add Student");
        addButton.setPreferredSize(new Dimension(120, 35));
        addButton.addActionListener(e -> addStudent());
        addButton.setBackground(Colors.GREEN);
        addButton.setFont(new Font("Tahoma", Font.BOLD, 12));
        
        CustomButton updateButton = new CustomButton("Update");
        updateButton.setPreferredSize(new Dimension(100, 35));
        updateButton.addActionListener(e -> updateStudent());
        updateButton.setBackground(Colors.YELLOW);
        updateButton.setFont(new Font("Tahoma", Font.BOLD, 12));
        
        CustomButton deleteButton = new CustomButton("Delete");
        deleteButton.setPreferredSize(new Dimension(100, 35));
        deleteButton.addActionListener(e -> deleteStudent());
        deleteButton.setBackground(Colors.RED);
        deleteButton.setFont(new Font("Tahoma", Font.BOLD, 12));
        
        CustomButton clearButton = new CustomButton("Clear");
        clearButton.setPreferredSize(new Dimension(100, 35));
        clearButton.addActionListener(e -> clearFields());
        clearButton.setBackground(Colors.BLUE);
        clearButton.setFont(new Font("Tahoma", Font.BOLD, 12));
        
        buttonsPanel.add(clearButton);
        buttonsPanel.add(deleteButton);
        buttonsPanel.add(updateButton);
        buttonsPanel.add(addButton);
        
        panel.add(title, BorderLayout.NORTH);
        panel.add(fieldsPanel, BorderLayout.CENTER);
        panel.add(buttonsPanel, BorderLayout.SOUTH);
        
        return panel;
    }
    
    private JPanel createFieldPanel(String label, JComponent component) {
        JPanel panel = new JPanel(new BorderLayout(0, 5));
        panel.setOpaque(false);
        
        JLabel labelComponent = new JLabel(label);
        labelComponent.setFont(new Font("Tahoma", Font.PLAIN, 12));
        labelComponent.setForeground(Colors.CAVIAR);
        
        panel.add(labelComponent, BorderLayout.NORTH);
        panel.add(component, BorderLayout.CENTER);
        
        return panel;
    }
    
    private RoundPanel createTablePanel() {
        RoundPanel panel = new RoundPanel();
        panel.setBackground(Color.WHITE);
        panel.setLayout(new BorderLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        JLabel title = new JLabel("Student List");
        title.setFont(new Font("Tahoma", Font.BOLD, 18));
        title.setForeground(Colors.CAVIAR);
        title.setBorder(BorderFactory.createEmptyBorder(0, 0, 15, 0));
        
        String[] columns = {"ID", "Name", "Email", "Phone", "Course", "Status"};
        tableModel = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        
        table = new CustomTable();
        table.setModel(tableModel);
        
        table.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting() && table.getSelectedRow() != -1) {
                loadStudentToForm(table.getSelectedRow());
            }
        });
        
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBorder(null);
        scrollPane.setVerticalScrollBar(new CustomScrollBar());
        
        panel.add(title, BorderLayout.NORTH);
        panel.add(scrollPane, BorderLayout.CENTER);
        
        loadStudentsFromDatabase();
        
        return panel;
    }
    
    private void loadStudentsFromDatabase() {
        tableModel.setRowCount(0);
        List<Student> students = studentDAO.getAllStudents();
        
        for (Student student : students) {
            Object[] row = {
                student.getId(),
                student.getName(),
                student.getEmail(),
                student.getPhone(),
                student.getCourse(),
                student.getStatus()
            };
            tableModel.addRow(row);
        }
        
        searchField.setText("");
    }
    
    private void loadStudentToForm(int row) {
        idField.setText(tableModel.getValueAt(row, 0).toString());
        nameField.setText(tableModel.getValueAt(row, 1).toString());
        emailField.setText(tableModel.getValueAt(row, 2).toString());
        phoneField.setText(tableModel.getValueAt(row, 3).toString());
        courseField.setText(tableModel.getValueAt(row, 4).toString());
        statusBox.setSelectedItem(tableModel.getValueAt(row, 5).toString());
    }
    
    private void addStudent() {
        if (validateFields()) {
            Student newStudent = new Student(
                idField.getText().trim(),
                nameField.getText().trim(),
                emailField.getText().trim(),
                phoneField.getText().trim(),
                courseField.getText().trim(),
                statusBox.getSelectedItem().toString()
            );
            
            boolean success = studentDAO.addStudent(newStudent);
            
            if (success) {
                loadStudentsFromDatabase();
                clearFields();
                JOptionPane.showMessageDialog(this, 
                    "Student added successfully!", 
                    "Success", 
                    JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, 
                    "Failed to add student. Please check if Student ID already exists.", 
                    "Error", 
                    JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    private void updateStudent() {
        int selectedRow = table.getSelectedRow();
        
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, 
                "Please select a student to update.", 
                "Warning", 
                JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        if (validateFields()) {
            Student updatedStudent = new Student(
                idField.getText().trim(),
                nameField.getText().trim(),
                emailField.getText().trim(),
                phoneField.getText().trim(),
                courseField.getText().trim(),
                statusBox.getSelectedItem().toString()
            );
            
            boolean success = studentDAO.updateStudent(updatedStudent);
            
            if (success) {
                loadStudentsFromDatabase();
                clearFields();
                JOptionPane.showMessageDialog(this, 
                    "Student updated successfully!", 
                    "Success", 
                    JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, 
                    "Failed to update student.", 
                    "Error", 
                    JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    private void deleteStudent() {
        int selectedRow = table.getSelectedRow();
        
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, 
                "Please select a student to delete.", 
                "Warning", 
                JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        String studentId = tableModel.getValueAt(selectedRow, 0).toString();
        String studentName = tableModel.getValueAt(selectedRow, 1).toString();
        
        int confirm = JOptionPane.showConfirmDialog(this,
            "Are you sure you want to delete student: " + studentName + "?",
            "Confirm Delete",
            JOptionPane.YES_NO_OPTION,
            JOptionPane.WARNING_MESSAGE);
        
        if (confirm == JOptionPane.YES_OPTION) {
            boolean success = studentDAO.deleteStudent(studentId);
            
            if (success) {
                loadStudentsFromDatabase();
                clearFields();
                JOptionPane.showMessageDialog(this, 
                    "Student deleted successfully!", 
                    "Success", 
                    JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, 
                    "Failed to delete student.", 
                    "Error", 
                    JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    private void searchStudent() {
        String keyword = searchField.getText().trim();
        
        if (keyword.isEmpty()) {
            loadStudentsFromDatabase();
            return;
        }
        
        tableModel.setRowCount(0);
        
        List<Student> results = studentDAO.searchStudents(keyword);
        
        for (Student student : results) {
            Object[] row = {
                student.getId(),
                student.getName(),
                student.getEmail(),
                student.getPhone(),
                student.getCourse(),
                student.getStatus()
            };
            tableModel.addRow(row);
        }
        
        if (results.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                "No students found matching: " + keyword,
                "Search Result",
                JOptionPane.INFORMATION_MESSAGE);
        }
    }
    
    private void clearFields() {
        idField.setText("");
        nameField.setText("");
        emailField.setText("");
        phoneField.setText("");
        courseField.setText("");
        statusBox.setSelectedIndex(0);
        table.clearSelection();
    }
    
    private boolean validateFields() {
        if (idField.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, 
                "Student ID is required!", 
                "Validation Error", 
                JOptionPane.ERROR_MESSAGE);
            idField.requestFocus();
            return false;
        }
        
        if (nameField.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, 
                "Student name is required!", 
                "Validation Error", 
                JOptionPane.ERROR_MESSAGE);
            nameField.requestFocus();
            return false;
        }
        
        if (emailField.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, 
                "Email is required!", 
                "Validation Error", 
                JOptionPane.ERROR_MESSAGE);
            emailField.requestFocus();
            return false;
        }
        
        if (phoneField.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, 
                "Phone number is required!", 
                "Validation Error", 
                JOptionPane.ERROR_MESSAGE);
            phoneField.requestFocus();
            return false;
        }
        
        if (courseField.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, 
                "Course is required!", 
                "Validation Error", 
                JOptionPane.ERROR_MESSAGE);
            courseField.requestFocus();
            return false;
        }
        
        return true;
    }
}
