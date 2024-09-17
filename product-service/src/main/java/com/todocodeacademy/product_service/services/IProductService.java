package com.todocodeacademy.product_service.services;

import com.todocodeacademy.product_service.models.Product;

import java.util.List;

public interface IProductService {

    public Product saveProduct(Product product);

    public List<Product> getProductList();

    public String editProduct(Long product_id, Product product);

    public String deleteProduct(Long product_id);

    public Product findProduct(Long product_id);

    public Product findProductByName(String name);
}
