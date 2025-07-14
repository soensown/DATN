package com.example.datn.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "products")
public class products {
    @Id
    @Column(name = "product_id")
    private String id;
    @Column(name = "product_name")
    private String productName;
    @Column(name = "description")
    private String description;
    @ManyToOne
    @JoinColumn(name = "category_id")
    private categories categories;
    @Column(name = "discount_price")
    private BigDecimal discountPrice;
    @Column(name = "unit_price")
    private BigDecimal unitPrice;
    @Column(name = "is_discount")
    private Boolean isDiscount;
    @Column(name = "is_special")
    private Boolean isSpecial;
    @ManyToOne
    @JoinColumn(name = "brand_id")
    private com.example.datn.Model.brands brands;
    @Column(name = "weight")
    private BigDecimal weight;
    @Column(name = "created_date")
    private Date createdDate;
    @Column(name = "created_by")
    private String createdBy;
    @Column(name = "updated_date")
    private Date updatedDate;
    @Column(name = "updated_by")
    private String updatedBy;
    @Column(name = "thumbnail")
    private String thumbnail;
}
