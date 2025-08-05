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
@Table(name = "product_images")
public class Product_images {
    @Id
    @Column(name = "image_id")
    private String id;

    @ManyToOne
    @JoinColumn(name = "product_detail_id")
    private Product_details product_details;

    @Column(name = "image_url")
    @NotBlank(message = "Không được để trống")
    private String image_url;
    @Column(name = "is_thumbnail")
    private boolean is_thumbnail;
}

