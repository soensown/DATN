package com.example.datn.Model;

import jakarta.validation.constraints.*;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "material")
public class material {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "material_id")
    @NotNull(message = "Không được bỏ trống")
    private Integer materialId;
    @Column(name = "material_name")
    @NotBlank(message = "Không được để trống")
    private String materialName;
}