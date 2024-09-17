package com.todocodeacademy.sale_service.repositories;

import com.todocodeacademy.sale_service.dtos.CartDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "cart-service")
public interface IApiCart {

    @GetMapping("/cart/get/{cart_id}")
    public CartDTO findCart(@PathVariable("cart_id") Long cart_id);
}
