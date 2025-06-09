package com.example.datn.Model;
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
public class product_materials {
    @EmbeddedId
    private productMaterialId id;

    @ManyToOne
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    private products product;

    @ManyToOne
    @JoinColumn(name = "material_id", referencedColumnName = "id")
    private material material;

    @Column(name = "percentage", precision = 5, scale = 2)
    private BigDecimal percentage;

}