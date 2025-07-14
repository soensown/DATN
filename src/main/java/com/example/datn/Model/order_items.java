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
public class order_items {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne
    @JoinColumn(name = "order_id")
    private orders order;
    @ManyToOne
    @JoinColumn(name = "product_detail_id")
    private product_details productDetails;
    @Column(name = "quantity")
    @NotNull(message = "Không được bỏ trống")
    private Integer quantity;
    @Column(name = "unit_price")
    @NotNull(message = "Không được bỏ trống")
    private BigDecimal unitPrice;
    @Column(name = "discount_price")
    @NotNull(message = "Không được bỏ trống")
    private BigDecimal discountPrice;

    @Column(name = "total_price")
    @NotNull(message = "Không được bỏ trống")
    private BigDecimal totalPrice;
}