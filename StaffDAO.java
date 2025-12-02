package sis.database;

import sis.models.Staff;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StaffDAO {
    private Connection connection;
    
    public StaffDAO() {
        this.connection = DatabaseConnection.getConnection();
    }
    
    public boolean addStaff(Staff staff) {
        String query = "INSERT INTO staff (name, email, phone, department, status) VALUES (?, ?, ?, ?, ?)";
        
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setString(1, staff.getName());
            pstmt.setString(2, staff.getEmail());
            pstmt.setString(3, staff.getPhone());
            pstmt.setString(4, staff.getDepartment());
            pstmt.setString(5, staff.getStatus());
            
            return pstmt.executeUpdate() > 0;
            
        } catch (SQLException e) {
            System.err.println("❌ Error adding staff: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }
    
    public List<Staff> getAllStaff() {
        List<Staff> staffList = new ArrayList<>();
        String query = "SELECT * FROM staff ORDER BY name ASC";
        
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            
            while (rs.next()) {
                Staff staff = new Staff(
                    rs.getString("name"),
                    rs.getString("email"),
                    rs.getString("phone"),
                    rs.getString("department"),
                    rs.getString("status")
                );
                staffList.add(staff);
            }
            
        } catch (SQLException e) {
            System.err.println("❌ Error getting all staff: " + e.getMessage());
            e.printStackTrace();
        }
        
        return staffList;
    }
    
    public List<Staff> searchStaff(String keyword) {
        List<Staff> staffList = new ArrayList<>();
        String query = "SELECT * FROM staff WHERE " +
                      "name LIKE ? OR email LIKE ? OR phone LIKE ? OR " +
                      "department LIKE ? OR status LIKE ?";
        
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            String searchPattern = "%" + keyword + "%";
            for (int i = 1; i <= 5; i++) {
                pstmt.setString(i, searchPattern);
            }
            
            ResultSet rs = pstmt.executeQuery();
            
            while (rs.next()) {
                Staff staff = new Staff(
                    rs.getString("name"),
                    rs.getString("email"),
                    rs.getString("phone"),
                    rs.getString("department"),
                    rs.getString("status")
                );
                staffList.add(staff);
            }
            
        } catch (SQLException e) {
            System.err.println("❌ Error searching staff: " + e.getMessage());
            e.printStackTrace();
        }
        
        return staffList;
    }
    
    public int getTotalStaff() {
        String query = "SELECT COUNT(*) as total FROM staff";
        
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            
            if (rs.next()) {
                return rs.getInt("total");
            }
            
        } catch (SQLException e) {
            System.err.println("❌ Error getting staff count: " + e.getMessage());
            e.printStackTrace();
        }
        
        return 0;
    }
}
