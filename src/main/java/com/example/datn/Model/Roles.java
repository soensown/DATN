package com.example.datn.Model;

import jakarta.validation.constraints.*;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.*;
import java.util.List;

@Entity
@Table(name = "roles")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Roles {
    @Id
    @Column(name = "role_id")
    private String id;

    @NotBlank(message = "Không được để trống")
    private String roleName;

    @NotBlank(message = "Không được để trống")
    private String roleCode;

    @NotBlank(message = "Không được để trống")
    private String description;

    private Boolean useYn;

    @OneToMany(mappedBy = "role")
    private List<Users> users;
}

