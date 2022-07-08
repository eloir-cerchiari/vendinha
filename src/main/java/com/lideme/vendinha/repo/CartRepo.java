package com.lideme.vendinha.repo;

import com.lideme.vendinha.domain.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CartRepo extends JpaRepository<Cart, Long> {
    @Query("SELECT ct  FROM Cart ct JOIN FETCH ct.items it where ct.id = ?1")
    Cart findCart(Long id);
}
