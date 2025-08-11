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
@Table(name = "product_details")
public class ProductDetails {

    @Id
    @Column(name = "product_detail_id")
    private String id;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Products product;

    @ManyToOne
    @JoinColumn(name = "color_id")
    private Colors color;

    @ManyToOne
    @JoinColumn(name = "size_id")
    private Sizes size;

    @Column(name = "quantity")
    @NotNull(message = "Không được bỏ trống")
    private Integer quantity;

    @Column(name = "description")
    @NotBlank(message = "Không được để trống")
    private String description;
}