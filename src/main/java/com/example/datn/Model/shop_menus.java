package com.example.datn.Model;

import jakarta.validation.constraints.*;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "shop_menus")
public class shop_menus {
    @Id
    private String id;
    @Column(name = "upper_menu_id")
    @NotBlank(message = "Không được để trống")
    private String upperMenuId;
    @Column(name = "menu_name")
    @NotBlank(message = "Không được để trống")
    private String menuName;
    @Column(name = "link_uri")
    @NotBlank(message = "Không được để trống")
    private String linkUri;
    @Column(name = "display_order")
    private int displayOrder;
    @Column(name = "use_yn")
    @NotBlank(message = "Không được để trống")
    private String useYn;
    @Column(name = "lev")
    @NotNull(message = "Không được bỏ trống")
    private Integer lev;
    @Column(name = "description")
    @NotBlank(message = "Không được để trống")
    private String description;
}