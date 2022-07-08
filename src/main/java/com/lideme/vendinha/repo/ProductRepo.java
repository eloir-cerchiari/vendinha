package com.lideme.vendinha.repo;

import com.lideme.vendinha.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepo extends JpaRepository<Product, Long> {
    Product findByGtin(String gtin);


    Long removeById(Long id);

}
