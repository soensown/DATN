package com.example.datn.Model;

import jakarta.validation.constraints.*;
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
@Table(name = "orders")
public class orders {
    @Id
    @Column(name = "order_id")
    private String id;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private users user;
    @ManyToOne
    @JoinColumn(name = "discount_id")
    private discounts discount;
    @Column(name = "total_price")
    @NotNull(message = "Không được bỏ trống")
    private BigDecimal totalPrice;
    @Column(name = "status")
    @NotBlank(message = "Không được để trống")
    private String status;
    @Column(name = "created_date")
    @NotNull(message = "Không được bỏ trống")
    private Date createdDate;
    @Column(name = "created_by")
    @NotBlank(message = "Không được để trống")
    private String createdBy;
    @Column(name = "updated_date")
    @NotNull(message = "Không được bỏ trống")
    private Date updatedDate;
    @Column(name = "updated_by")
    @NotBlank(message = "Không được để trống")
    private String updatedBy;
}
