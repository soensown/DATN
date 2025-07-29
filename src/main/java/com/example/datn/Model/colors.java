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
@Table(name = "colors")
public class colors {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "color_id")
    private Integer id;
    @Column(name = "color_name")
    @NotBlank(message = "Không được để trống")
    private String colorName;
    @Column(name = "color_code")
    @NotBlank(message = "Không được để trống")
    private String colorCode;
}
