package com.example.datn.Model;

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
    private String roleName;

    @Column(name = "role_code")
    private String roleCode;

    @Column(name = "description")
    private String description;

    @Column(name = "use_yn")
    private Boolean useYn;

    @OneToMany(mappedBy = "role")
    private List<users> users;
}
