package com.lideme.vendinha.repository;

import com.lideme.vendinha.domain.Product;
import com.lideme.vendinha.repo.ProductRepo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.awt.print.Pageable;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class ProductRepoTest {
    @Autowired
    ProductRepo productRepo;

    @Test
    public void listProductsPaginated(){
        Page<Product> products = productRepo.findAll(PageRequest.of(0,3));
        assertTrue(products.getTotalElements() >0);
    }
}