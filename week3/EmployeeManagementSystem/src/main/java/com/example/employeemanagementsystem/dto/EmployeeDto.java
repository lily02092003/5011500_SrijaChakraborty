package com.example.employeemanagementsystem.dto;

public class EmployeeDto {
    private String name;
    private String email;
    private String departmentName;

    public EmployeeDto(String name, String email, String departmentName) {
        this.name = name;
        this.email = email;
        this.departmentName = departmentName;
    }

    // Getters
    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    // Setters (optional, if needed)
    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }
}
