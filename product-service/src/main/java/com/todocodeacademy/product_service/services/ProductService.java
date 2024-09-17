package com.todocodeacademy.product_service.services;

import com.todocodeacademy.product_service.models.Product;
import com.todocodeacademy.product_service.repositories.IProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService implements IProductService {

    @Autowired
    private IProductRepository productRepo;

    @Override
    public Product saveProduct(Product product) {
        productRepo.save(product);
        return product;
    }

    @Override
    public List<Product> getProductList() {
        return productRepo.findAll();
    }

    @Override
    public String editProduct(Long product_id, Product product) {
        Optional<Product> productToEdit = productRepo.findById(product_id);
        if (productToEdit.isPresent()) {
            Product product1 = productToEdit.get();
            product1.setBrand(product.getBrand());
            product1.setCode(product.getCode());
            product1.setName(product.getName());
            product1.setPrice(product.getPrice());

            productRepo.save(product1);
            return "Product successfully updated";
        } else {
            return "Product not found";
        }
    }

    @Override
    public String deleteProduct(Long product_id) {
        Optional<Product> productToDelete = productRepo.findById(product_id);
        if (productToDelete.isPresent()) {
            productRepo.deleteById(product_id);
            return "Product successfully deleted";
        } else {
            return "No product found";
        }

    }

    @Override
    public Product findProduct(Long product_id) {
        return productRepo.findById(product_id).orElse(null);
    }

    @Override
    public Product findProductByName(String name) {
        return productRepo.findProductByName(name);
    }
}
