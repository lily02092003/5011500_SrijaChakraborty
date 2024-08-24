package com.example.employeemanagementsystem.repository;

import com.example.employeemanagementsystem.dto.EmployeeDto;
import com.example.employeemanagementsystem.model.Employee;
import com.example.employeemanagementsystem.projection.EmployeeProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    @Query("SELECT e FROM Employee e WHERE e.department.name = :departmentName")
    List<Employee> findEmployeesByDepartmentName(@Param("departmentName") String departmentName);

    Page<Employee> findAll(Pageable pageable);

    // Method to search employees by name with case-insensitive partial matching
    Page<Employee> findByNameContainingIgnoreCase(String name, Pageable pageable);
    @Query("SELECT e.name AS name, e.email AS email, d.name AS departmentName " +
            "FROM Employee e JOIN e.department d")
    List<EmployeeProjection> findAllProjectedBy();
    @Query("SELECT new com.example.employeemanagementsystem.dto.EmployeeDto(e.name, e.email, d.name) " +
            "FROM Employee e JOIN e.department d")
    List<EmployeeDto> findAllEmployeeDtos();
}