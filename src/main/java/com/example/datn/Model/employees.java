package com.example.datn.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "employees")
public class employees {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private users user;
    @Column(name = "full_name")
    private String fullName;
    @Column(name = "gender")
    private String gender;
    @Column(name = "birth_date")
    private Date birthDate;
    @Column(name = "hire_date")
    private Date hireDate;
    @Column(name = "department")
    private String department;
    @Column(name = "position")
    private String position;
    @Column(name = "status")
    private String status;
    @Column(name = "created_at")
    private Date createdAt;
    @Column(name = "updated_at")
    private Date updatedAt;

}

