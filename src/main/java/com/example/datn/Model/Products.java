package com.example.datn.Model;

import jakarta.validation.constraints.*;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "products")
public class Products {
    @Id
    @Column(name = "product_id")
    private String id;
    @Column(name = "product_name")
    @NotBlank(message = "Không được để trống")
    private String productName;
    @Column(name = "description")
    @NotBlank(message = "Không được để trống")
    private String description;
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Categories categories;
    @Column(name = "discount_price")
    @NotNull(message = "giá khuyến mãi Không được bỏ trống")
    private BigDecimal discountPrice;
    @Column(name = "unit_price")
    @NotNull(message = "giá gốc không được bỏ trống")
    private BigDecimal unitPrice;
    @Column(name = "is_discount")
    private Boolean isDiscount;
    @Column(name = "is_special")
    private Boolean isSpecial;
    @ManyToOne
    @JoinColumn(name = "brand_id")
    private Brands brands;
    @Column(name = "weight")
    @NotNull(message = "Không được bỏ trống")
    private BigDecimal weight;
    @Column(name = "created_date")
    @NotNull(message = "Không được bỏ trống")
    private Date createdDate;
    @Column(name = "created_by")
    @NotBlank(message = "Không được để trống")
    private String createdBy;
    @Column(name = "updated_date")
    @NotNull(message = "Không được bỏ trống")
    private LocalDateTime updatedDate;
    @Column(name = "updated_by")
    @NotBlank(message = "Không được để trống")
    private String updatedBy;
    @Column(name = "thumbnail")
    @NotBlank(message = "Không được để trống")
    private String thumbnail;
}
