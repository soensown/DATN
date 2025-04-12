package com.example.sp25_tutor_sof3061_b1_product_mangament.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@Table
public class SmartPhone {

    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String brand;

    private String os;

    @OneToMany
    @JoinColumn(name = "smart_phone_id") // Khong can @JoinTable
    private List<SmartPhoneVariant> variants;
}
