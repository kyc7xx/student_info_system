// Package para sa data models
package sis.models;

// Student model class - represents a student record
public class Student {
    // Private fields - student properties
    private String id;           // Student ID (primary key)
    private String name;         // Full name
    private String email;        // Email address
    private String phone;        // Phone number
    private String course;       // Course enrolled
    private String status;       // Status (Active/Inactive/Graduated)
    
    // Constructor with all parameters
    public Student(String id, String name, String email, String phone, String course, String status) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.course = course;
        this.status = status;
    }
    
    // Default constructor
    public Student() {}
    
    // Getter methods - para makuha yung values
    public String getId() {
        return id;
    }
    
    public String getName() {
        return name;
    }
    
    public String getEmail() {
        return email;
    }
    
    public String getPhone() {
        return phone;
    }
    
    public String getCourse() {
        return course;
    }
    
    public String getStatus() {
        return status;
    }
    
    // Setter methods - para mag-update ng values
    public void setId(String id) {
        this.id = id;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public void setPhone(String phone) {
        this.phone = phone;
    }
    
    public void setCourse(String course) {
        this.course = course;
    }
    
    public void setStatus(String status) {
        this.status = status;
    }
    
    // toString method - para sa debugging
    @Override
    public String toString() {
        return "Student{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", course='" + course + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
