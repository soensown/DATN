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
@Table(name = "order_items")
public class Order_items {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne
    @JoinColumn(name = "order_id")
    private Orders order;
    @ManyToOne
    @JoinColumn(name = "product_detail_id")
    private ProductDetails productDetails;
    @Column(name = "quantity")
    @NotNull(message = "Không được bỏ trống số lượng")
    private Integer quantity;
    @Column(name = "unit_price")
    @NotNull(message = "Không được bỏ trống giá gốc")
    private BigDecimal unitPrice;
    @Column(name = "discount_price")
    @NotNull(message = "Không được bỏ trống giảm giá")
    private BigDecimal discountPrice;

    @Column(name = "total_price")
    @NotNull(message = "Không được bỏ trống tổng tiền")
    private BigDecimal totalPrice;
}