package com.example.datn.Model;

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
    private String id;
    @Column(name = "discount_value")
    private Integer discountName;
    @Column(name = "discount_type")
    private String discountType;
    @Column(name = "start_date")
    private Date startDate;
    @Column(name = "end_date")
    private Date endDate;
    @Column(name = "status")
    private String status;
    @Column(name = "description")
    private String description;
    @Column(name = "condition")
    private Integer condition;
}