package com.example.datn.Model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;

@Getter
@Setter
@MappedSuperclass

public abstract class abstractEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "user_id")
    private String id;

    @Column(name = "created_date")
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date createDate;

    @Column(name = "created_by")
    private String createBy;

    @Column(name = "updated_date")
    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateDate;

    @Column(name = "updated_by")
    private String updateBy;



}
