package com.School.BackendSchool.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
@Entity
@Data
@Table(name = "student")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "student_id")
    private Long id;

    @Column(name = "FullName")
    private String fullName;

    @Column(name = "Age")
    private int age;

    @Column(name = "Phone")
    private String phone;

    @Column(name = "Date_of_birth")
    private Date dateOfBirth;

    @Column(name = "Gender")
    private String gender;


    private String className;

    @Column(name = "Password")
    private String password;


}

