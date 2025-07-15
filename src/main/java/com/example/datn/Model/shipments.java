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
@Table(name = "shipments")
public class shipments {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne
    @JoinColumn(name = "order_id")
    private orders order;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private users users;
    @Column(name = "shipping_address")
    @NotBlank(message = "Không được để trống")
    private String shippingAddress;
    @Column(name = "shipping_method")
    @NotBlank(message = "Không được để trống")
    private String shippingMethod;
    @Column(name = "tracking_number")
    @NotBlank(message = "Không được để trống")
    private String trackingNumber;
    @Column(name = "shipment_status")
    @NotBlank(message = "Không được để trống")
    private String shipmentStatus;
    @Column(name = "shipped_date")
    @NotBlank(message = "Không được để trống")
    private String shippedDate;
    @Column(name = "delivered_date")
    @NotBlank(message = "Không được để trống")
    private String deliveredDate;
    @Column(name = "created_at")
    private Date createdAt;
    @Column(name = "updated_at")
    private Date updatedAt;
}