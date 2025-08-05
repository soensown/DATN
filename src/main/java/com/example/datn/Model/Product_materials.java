package com.example.datn.Model;
import jakarta.validation.constraints.*;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "product_materials")
public class Product_materials {
    @EmbeddedId
    private ProductMaterialId id;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Products product;

    @ManyToOne
    @JoinColumn(name = "material_id")
    private Material material;

    @Column(name = "percentage", precision = 5, scale = 2)
    @NotNull(message = "Không được bỏ trống")
    private BigDecimal percentage;

}