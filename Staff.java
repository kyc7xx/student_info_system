// Package para sa data models
package sis.models;

// Staff model class - represents a staff member record
public class Staff {
    // Private fields - staff properties
    private String name;         // Full name
    private String email;        // Email address
    private String phone;        // Phone number
    private String department;   // Department
    private String status;       // Status (Active/On Leave/Retired)
    
    // Constructor with all parameters
    public Staff(String name, String email, String phone, String department, String status) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.department = department;
        this.status = status;
    }
    
    // Default constructor
    public Staff() {}
    
    // Getter methods
    public String getName() {
        return name;
    }
    
    public String getEmail() {
        return email;
    }
    
    public String getPhone() {
        return phone;
    }
    
    public String getDepartment() {
        return department;
    }
    
    public String getStatus() {
        return status;
    }
    
    // Setter methods
    public void setName(String name) {
        this.name = name;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public void setPhone(String phone) {
        this.phone = phone;
    }
    
    public void setDepartment(String department) {
        this.department = department;
    }
    
    public void setStatus(String status) {
        this.status = status;
    }
    
    // toString method
    @Override
    public String toString() {
        return "Staff{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", department='" + department + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
