package com.todocodeacademy.product_service.controllers;

import com.todocodeacademy.product_service.models.Product;
import com.todocodeacademy.product_service.services.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private IProductService productService;

    @PostMapping("/save")
    public Product saveProduct(@RequestBody Product product) {
        return productService.saveProduct(product);
    }

    @GetMapping("/get")
    public List<Product> getProducts() {
        return productService.getProductList();
    }

    @GetMapping("/get/{product_id}")
    public Product findProduct(@PathVariable Long product_id) {
        return productService.findProduct(product_id);
    }

    @PutMapping("/edit/{product_id}")
    public String editProduct(@PathVariable Long product_id, @RequestBody Product product) {
        return productService.editProduct(product_id, product);
    }

    @DeleteMapping("/delete/{product_id}")
    public String deleteProduct(@PathVariable Long product_id) {
        return productService.deleteProduct(product_id);
    }

    @GetMapping("/get/name/{name}")
    public Product findByProductName(@PathVariable String name) {
        return productService.findProductByName(name);
    }
}
