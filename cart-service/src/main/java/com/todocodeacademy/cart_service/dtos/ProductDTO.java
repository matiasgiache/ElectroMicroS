package com.todocodeacademy.cart_service.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO {

    private Long product_id;
    private String name;
    private Double price;
    private String code;
    private String brand;
}
