package com.example.sp25_tutor_sof3061_b1_product_mangament.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Table
@Entity
public class SmartPhoneVariant { // Bien the

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String color;
    private Integer memory; // 64, 128, 256

    private BigDecimal price;

    @ManyToOne
    private SmartPhone smartPhone; // Khong can @JoinColumn, JPA tu hieu
}
