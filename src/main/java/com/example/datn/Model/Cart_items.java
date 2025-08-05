package com.example.datn.Model;

import jakarta.validation.constraints.*;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "cart_items")
public class Cart_items {
    @Id
    private String id;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private Users user;
    @ManyToOne
    @JoinColumn(name = "product_detail_id")
    private Product_details product_details;
    @Column(name = "quantity")
    private int quantity;
    @Column(name = "created_date")
    @NotNull(message = "Không được bỏ trống")
    private Date created_date;
    @Column(name = "created_by")
    @NotBlank(message = "Không được để trống")
    private String created_by;
    @Column(name = "updated_date")
    @NotNull(message = "Không được bỏ trống")
    private Date updated_date;
    @Column(name = "updated_by")
    @NotBlank(message = "Không được để trống")
    private String updated_by;
}