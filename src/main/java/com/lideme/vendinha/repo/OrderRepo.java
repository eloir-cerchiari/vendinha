package com.lideme.vendinha.repo;

import com.lideme.vendinha.domain.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepo extends JpaRepository<Cart, Long> {
}
