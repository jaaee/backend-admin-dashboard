package com.example.admin_dashboard.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.time.LocalDateTime;


@Entity
@Table(name = "users")
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "User First Name cannot be empty")
    @Column(name="FirstName")
    private String firstName;

    @Column(name="LastName")
    private String lastName;

    @Column(name="UserName")
    private String userName;


    @Column(name="Password")
    private String password;

    @Column(name="Email")
    private String email;

    @Column(name="MobileNumber")
    private String mobileNumber;

    @Column(name="Role")
    private String role;

    @Column(name="CreatedDate")
    private LocalDateTime createdDate;

    @Column(name="UpdatedDate")
    private LocalDateTime updatedDate;





}
