package com.example.datn.Model;
import jakarta.validation.constraints.*;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class productMaterialId implements Serializable {
    @Column(name = "product_id", insertable = false, updatable = false)
    @NotBlank(message = "Không được để trống")
    private String productId;

    @Column(name = "material_id", insertable = false, updatable = false)
    @NotNull(message = "Không được bỏ trống")
    private Integer materialId;
}
