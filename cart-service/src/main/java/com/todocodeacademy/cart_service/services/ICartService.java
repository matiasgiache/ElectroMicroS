package com.todocodeacademy.cart_service.services;

import com.todocodeacademy.cart_service.dtos.CartDTO;
import com.todocodeacademy.cart_service.models.Cart;

import java.util.List;

public interface ICartService {

    void saveCart(Cart cart);

    List<Cart> getCarts();

    List<String> getProductsList(Long cart_id);

    void deleteCart(Long cart_id);

    Cart findCart(Long cart_id);

    String addProductToCart(Long cart_id, String product_name);

    CartDTO getCartAndProductList(Long cart_id);

    String removeProductFromCart(Long cart_id, String product_name);
}
