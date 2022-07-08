package com.lideme.vendinha.repo;

import com.lideme.vendinha.domain.Cart;
import com.lideme.vendinha.domain.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CartItemRepo extends JpaRepository<CartItem, Long> {
    @Query("SELECT ct  FROM Cart ct JOIN FETCH ct.items ")
    CartItem find(Long cartId);}
