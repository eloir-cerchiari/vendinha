package com.lideme.vendinha.domain;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class ProductTest {

    @Test
    void validateEmptyConstructor() {
        Product product = new Product();
        assertEquals(product.getId(),null);
        assertEquals(product.getGtin(),null);
        assertEquals(product.getBrand(),null);
        assertEquals(product.getName(),null);
        assertEquals(product.getSize(),null);
        assertEquals(product.getPrice(),null);
    }

    @Test
    void validateAllArgsConstructor() {
        Product product = new Product(123L,"name","gtin","size","brand", BigDecimal.valueOf(1.2));
        assertEquals(product.getId(),123L);
        assertEquals(product.getGtin(),"gtin");
        assertEquals(product.getBrand(),"brand");
        assertEquals(product.getName(),"name");
        assertEquals(product.getSize(),"size");
        assertEquals(product.getPrice(),BigDecimal.valueOf(1.2));
    }

    @Test
    void validateSetters() {
        Product product = new Product();
        product.setId(123l);
        product.setGtin("gtin");
        product.setBrand("brand");
        product.setName("name");
        product.setSize("size");
        product.setPrice(new BigDecimal("1.2"));

        assertEquals(product.getId(),123L);
        assertEquals(product.getGtin(),"gtin");
        assertEquals(product.getBrand(),"brand");
        assertEquals(product.getName(),"name");
        assertEquals(product.getSize(),"size");
        assertEquals(product.getPrice(),BigDecimal.valueOf(1.2));
        System.out.println(product.toString());
        product.setName(null);
        assertNull(product.getName());
    }

}