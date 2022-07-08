package com.lideme.vendinha.repo;

import com.lideme.vendinha.domain.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartItemRepo extends JpaRepository<CartItem, Long> {
}
