package com.example.datn.Model;

import jakarta.validation.constraints.*;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "categories")
public class categories {
    @Id
    @Column(name = "category_id")
    private String id;

    @Column(name = "category_name")
    @NotBlank(message = "Không được để trống")
    private String categoryName;
}