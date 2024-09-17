package com.todocodeacademy.sale_service.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class SaleDTO {
    private Long sale_id;
    private LocalDate date;
    private Long cart_id;
    private Double total;
    private List<String> product_list;
}
