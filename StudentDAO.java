package sis.database;

import sis.models.Student;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentDAO {
    private Connection connection;
    
    public StudentDAO() {
        this.connection = DatabaseConnection.getConnection();
    }
    
    public boolean addStudent(Student student) {
        String query = "INSERT INTO students (id, name, email, phone, course, status) VALUES (?, ?, ?, ?, ?, ?)";
        
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setString(1, student.getId());
            pstmt.setString(2, student.getName());
            pstmt.setString(3, student.getEmail());
            pstmt.setString(4, student.getPhone());
            pstmt.setString(5, student.getCourse());
            pstmt.setString(6, student.getStatus());
            
            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;
            
        } catch (SQLException e) {
            System.err.println("❌ Error adding student: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }
    
    public boolean updateStudent(Student student) {
        String query = "UPDATE students SET name=?, email=?, phone=?, course=?, status=? WHERE id=?";
        
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setString(1, student.getName());
            pstmt.setString(2, student.getEmail());
            pstmt.setString(3, student.getPhone());
            pstmt.setString(4, student.getCourse());
            pstmt.setString(5, student.getStatus());
            pstmt.setString(6, student.getId());
            
            return pstmt.executeUpdate() > 0;
            
        } catch (SQLException e) {
            System.err.println("❌ Error updating student: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }
    
    public boolean deleteStudent(String studentId) {
        String query = "DELETE FROM students WHERE id=?";
        
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setString(1, studentId);
            return pstmt.executeUpdate() > 0;
            
        } catch (SQLException e) {
            System.err.println("❌ Error deleting student: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }
    
    public Student getStudentById(String studentId) {
        String query = "SELECT * FROM students WHERE id=?";
        
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setString(1, studentId);
            ResultSet rs = pstmt.executeQuery();
            
            if (rs.next()) {
                return new Student(
                    rs.getString("id"),
                    rs.getString("name"),
                    rs.getString("email"),
                    rs.getString("phone"),
                    rs.getString("course"),
                    rs.getString("status")
                );
            }
            
        } catch (SQLException e) {
            System.err.println("❌ Error getting student: " + e.getMessage());
            e.printStackTrace();
        }
        
        return null;
    }
    
    public List<Student> getAllStudents() {
        List<Student> students = new ArrayList<>();
        String query = "SELECT * FROM students ORDER BY name ASC";
        
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            
            while (rs.next()) {
                Student student = new Student(
                    rs.getString("id"),
                    rs.getString("name"),
                    rs.getString("email"),
                    rs.getString("phone"),
                    rs.getString("course"),
                    rs.getString("status")
                );
                
                students.add(student);
            }
            
        } catch (SQLException e) {
            System.err.println("❌ Error getting all students: " + e.getMessage());
            e.printStackTrace();
        }
        
        return students;
    }
    
    public List<Student> searchStudents(String keyword) {
        List<Student> students = new ArrayList<>();
        String query = "SELECT * FROM students WHERE " +
                      "id LIKE ? OR name LIKE ? OR email LIKE ? OR " +
                      "phone LIKE ? OR course LIKE ? OR status LIKE ?";
        
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            String searchPattern = "%" + keyword + "%";
            for (int i = 1; i <= 6; i++) {
                pstmt.setString(i, searchPattern);
            }
            
            ResultSet rs = pstmt.executeQuery();
            
            while (rs.next()) {
                Student student = new Student(
                    rs.getString("id"),
                    rs.getString("name"),
                    rs.getString("email"),
                    rs.getString("phone"),
                    rs.getString("course"),
                    rs.getString("status")
                );
                students.add(student);
            }
            
        } catch (SQLException e) {
            System.err.println("❌ Error searching students: " + e.getMessage());
            e.printStackTrace();
        }
        
        return students;
    }
    
    public int getTotalStudents() {
        String query = "SELECT COUNT(*) as total FROM students";
        
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            
            if (rs.next()) {
                return rs.getInt("total");
            }
            
        } catch (SQLException e) {
            System.err.println("❌ Error getting student count: " + e.getMessage());
            e.printStackTrace();
        }
        
        return 0;
    }
    
    public int getStudentCountByStatus(String status) {
        String query = "SELECT COUNT(*) as total FROM students WHERE status=?";
        
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setString(1, status);
            ResultSet rs = pstmt.executeQuery();
            
            if (rs.next()) {
                return rs.getInt("total");
            }
            
        } catch (SQLException e) {
            System.err.println("❌ Error getting student count by status: " + e.getMessage());
            e.printStackTrace();
        }
        
        return 0;
    }
}
