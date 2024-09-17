package com.todocodeacademy.sale_service.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class CartDTO {

    private Long cart_id;
    private Double total;
    private List<String> product_list;
}
