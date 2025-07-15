package com.example.datn.Model;

import jakarta.validation.constraints.*;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.*;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "roles")
public class roles {
    @Id
    @Column(name = "role_id")
    private String id;

    @Column(name = "role_name")
    @NotBlank(message = "Không được để trống")
    private String roleName;

    @Column(name = "role_code")
    @NotBlank(message = "Không được để trống")
    private String roleCode;

    @Column(name = "description")
    @NotBlank(message = "Không được để trống")
    private String description;

    @Column(name = "use_yn")
    private Boolean useYn;

    @OneToMany(mappedBy = "role")
    private List<users> users;
}
