package com.todocodeacademy.cart_service.repositories;

import com.todocodeacademy.cart_service.dtos.ProductDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "product-service")
public interface IApiProduct {

    @GetMapping("/product/get")
    List<ProductDTO> getProducts();

    @GetMapping("/product/get/name/{name}")
    ProductDTO findByProductName(@PathVariable("name") String name);
}
