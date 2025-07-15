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
@Table(name = "discounts")
public class discounts {
    @Id
    @Column(name = "discount_id")
    private String id;
    @Column(name = "discount_value")
    @NotNull(message = "Không được bỏ trống")
    private Integer discountName;
    @Column(name = "discount_type")
    @NotBlank(message = "Không được để trống")
    private String discountType;
    @Column(name = "start_date")
    @NotNull(message = "Không được bỏ trống")
    private Date startDate;
    @Column(name = "end_date")
    @NotNull(message = "Không được bỏ trống")
    private Date endDate;
    @Column(name = "status")
    @NotBlank(message = "Không được để trống")
    private String status;
    @Column(name = "description")
    @NotBlank(message = "Không được để trống")
    private String description;
    @Column(name = "condition")
    @NotNull(message = "Không được bỏ trống")
    private Integer condition;
}