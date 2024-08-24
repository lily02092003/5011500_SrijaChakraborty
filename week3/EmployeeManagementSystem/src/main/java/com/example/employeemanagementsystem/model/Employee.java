package com.example.employeemanagementsystem.model;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.BatchSize;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;


import java.time.LocalDateTime;

@BatchSize(size = 10)
@EntityListeners(AuditingEntityListener.class)
@Entity
@Table(name = "employees")
@Data
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @CreatedDate
    private LocalDateTime createdDate;
    private String name;
    private String email;
    @LastModifiedDate
    private LocalDateTime lastModifiedDate;
    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;
}
