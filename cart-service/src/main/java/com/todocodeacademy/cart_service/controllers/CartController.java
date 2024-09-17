package com.todocodeacademy.cart_service.controllers;

import com.todocodeacademy.cart_service.dtos.CartDTO;
import com.todocodeacademy.cart_service.models.Cart;
import com.todocodeacademy.cart_service.services.ICartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private ICartService cartService;

    @PostMapping("/create")
    public String createCart(@RequestBody Cart cart) {
        cartService.saveCart(cart);
        return "Shopping cart successfully created";
    }

    @GetMapping("/get")
    public List<Cart> getCarts() {
        return cartService.getCarts();
    }

    @GetMapping("/getFull/{cart_id}")
    public CartDTO getCartAndProductList(@PathVariable("cart_id") Long cart_id) {
        return cartService.getCartAndProductList(cart_id);
    }

    @GetMapping("/get/{cart_id}")
    public Cart findCart(@PathVariable("cart_id") Long cart_id) {
        return cartService.findCart(cart_id);
    }

    @GetMapping("/get/products/{cart_id}")
    public List<String> getProductsList(@PathVariable("cart_id") Long cart_id) {
        return cartService.getProductsList(cart_id);
    }

    @PutMapping("/put/{cart_id}/{product_name}")
    public String addProductToCart(@PathVariable("cart_id") Long cart_id,
                                   @PathVariable("product_name") String product_name) {
        return cartService.addProductToCart(cart_id, product_name);
    }

    @PutMapping("/remove/{cart_id}/{product_name}")
    public String removeProductFromCart(@PathVariable("cart_id") Long cart_id,
                                        @PathVariable("product_name") String product_name) {
        return cartService.removeProductFromCart(cart_id, product_name);
    }

    @DeleteMapping("/delete/{cart_id}")
    public String deleteCart(@PathVariable("cart_id") Long cart_id) {
        cartService.deleteCart(cart_id);
        return "Shopping cart successfully deleted";
    }

    //public String addProductToCartFallBack
}
