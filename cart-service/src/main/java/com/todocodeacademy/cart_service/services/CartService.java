package com.todocodeacademy.cart_service.services;

import com.todocodeacademy.cart_service.dtos.CartDTO;
import com.todocodeacademy.cart_service.dtos.ProductDTO;
import com.todocodeacademy.cart_service.models.Cart;
import com.todocodeacademy.cart_service.repositories.IApiProduct;
import com.todocodeacademy.cart_service.repositories.ICartRepository;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CartService implements ICartService {

    @Autowired
    private ICartRepository cartRepo;

    @Autowired
    private IApiProduct apiProduct;

    @Override
    public void saveCart(Cart cart) {
        cartRepo.save(cart);
    }

    @Override
    public List<Cart> getCarts() {
        return cartRepo.findAll();
    }

    @Override
    public List<String> getProductsList(Long cart_id) {

        return cartRepo.findById(cart_id).get().getProduct_list();
    }

    @Override
    public void deleteCart(Long cart_id) {
        cartRepo.deleteById(cart_id);
    }

    @Override
    public Cart findCart(Long cart_id) {
        return cartRepo.findById(cart_id).orElse(null);
    }

    @Override
    @CircuitBreaker(name = "product-service", fallbackMethod = "addProductToCartFallBack")
    @Retry(name = "product-service")
    public String addProductToCart(Long cart_id, String product_name) {
        Optional<Cart> cart = cartRepo.findById(cart_id);
        List<ProductDTO> productDTOList = apiProduct.getProducts();
        List<String> allProducts = new ArrayList<>();
        Double price = 0.0;
        for (ProductDTO productDTO : productDTOList) {
            allProducts.add(productDTO.getName());
            if (productDTO.getName().equals(product_name)) {
                price = productDTO.getPrice();
            }
        }
        if (cart.isPresent() && allProducts.contains(product_name)) {
            Cart cart1 = cart.get();
            List<String> product_list = cart1.getProduct_list();
            product_list.add(product_name);
            cart1.setProduct_list(product_list);
            cart1.setTotal(cart1.getTotal() + price);
            cartRepo.save(cart1);
            return "Product added to list successfully" + cart1;
        } else {
            return "Cart or product not found";
        }
    }

    public String addProductToCartFallBack(Exception exception) {
        return "Failed to add new product to the shopping cart";
    }

    public void exception(){
        throw new IllegalArgumentException("Fail");
    }

    @Override
    @CircuitBreaker(name = "product-service", fallbackMethod = "getCartAndProductListFallBack")
    @Retry(name = "product-service")
    public CartDTO getCartAndProductList(Long cart_id) {

        Cart cart = this.findCart(cart_id);

        CartDTO cartDTO = new CartDTO();
        cartDTO.setCart_id(cart.getCart_id());
        cartDTO.setTotal(cart.getTotal());
        List<ProductDTO> productDTOList = new ArrayList<>();
        for (String product : cart.getProduct_list()) {
            productDTOList.add(apiProduct.findByProductName(product));
        }
        cartDTO.setProduct_list(productDTOList);
        return cartDTO;
    }

    @Override
    @CircuitBreaker(name = "product-service",fallbackMethod = "deleteProductFromCartFallBack")
    @Retry(name = "product-service")
    public String removeProductFromCart(Long cart_id, String product_name) {

        Optional<Cart> cart = cartRepo.findById(cart_id);
        Double product_price = apiProduct.findByProductName(product_name).getPrice();
        if (cart.isPresent()){
            Cart cart1 = cart.get();
            if (cart1.getProduct_list().contains(product_name)){
                List<String> product_list = cart1.getProduct_list();
                product_list.remove(product_name);
                cart1.setProduct_list(product_list);
                cart1.setTotal(cart1.getTotal() - product_price);
                cartRepo.save(cart1);
                return "Product successfully deleted from shopping cart";
            }
        }

        return "Shopping cart or product not found";
    }

    public CartDTO getCartAndProductListFallBack(Exception exception) {
        return new CartDTO(999999L,9999999.99,null);
    }

    public String deleteProductFromCartFallBack(Exception exception){
        return "Failed to delete product from the shopping cart";
    }


}
