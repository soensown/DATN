package com.example.datn.Model;

import jakarta.validation.constraints.*;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDateTime;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Users {
    @Id
    @Column(name = "user_id")
    private String id;

    private String username;

    private String password;

    @NotBlank(message = "Không được để trống")
    private String fullName;


    private String email;

    @NotBlank(message = "Không được để trống")
    private String phoneNumber;

    @NotBlank(message = "Không được để trống")
    private String address;

    @NotNull(message = "Không được để trống")
    private String status;

    private boolean isDel;

    @NotNull(message = "Không được bỏ trống")
    private LocalDateTime createdDate;

    @NotBlank(message = "Không được để trống")
    private String createdBy;

    @NotNull(message = "Không được bỏ trống")
    private LocalDateTime updatedDate;

    @NotBlank(message = "Không được để trống")
    private String updatedBy;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private Roles role;
}
