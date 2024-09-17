package com.todocodeacademy.cart_service.repositories;

import com.todocodeacademy.cart_service.models.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICartRepository extends JpaRepository<Cart, Long> {
}
