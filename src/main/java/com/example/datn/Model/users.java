package com.example.datn.Model;

import jakarta.validation.constraints.*;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "users")
public class users {
    @Id
    @Column(name = "user_id")
    private String id;

    @Column(name = "username")
    @NotBlank(message = "Không được để trống")
    private String username;

    @Column(name = "password")
    @NotBlank(message = "Không được để trống")
    private String password;

    @Column(name = "full_name")
    @NotBlank(message = "Không được để trống")
    private String fullName;

    @Column(name = "email")
    @NotBlank(message = "Không được để trống")
    private String email;

    @Column(name = "phone_number")
    @NotBlank(message = "Không được để trống")
    private String phoneNumber;

    @Column(name = "address")
    @NotBlank(message = "Không được để trống")
    private String address;

    @Column(name = "status")
    @NotBlank(message = "Không được để trống")
    private String status;

    @Column(name = "is_del")
    private boolean isDel;

    @Column(name = "created_date")
    @NotNull(message = "Không được bỏ trống")
    private LocalDateTime createdDate;

    @Column(name = "created_by")
    @NotBlank(message = "Không được để trống")
    private String createdBy;

    @Column(name = "updated_date")
    @NotNull(message = "Không được bỏ trống")
    private LocalDateTime updatedDate;

    @Column(name = "updated_by")
    @NotBlank(message = "Không được để trống")
    private String updatedBy;

    @ManyToOne
    @JoinColumn(name = "role_id", referencedColumnName = "role_id") // <-- sửa tại đây
    private roles role;
}
