package com.lideme.vendinha.service;

import com.lideme.vendinha.domain.Product;
import com.lideme.vendinha.repo.ProductRepo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ContextConfiguration(classes = {ProductServiceImpl.class})
@ExtendWith(SpringExtension.class)
class MProductServiceImplTest {
   @MockBean
   private ProductRepo productRepo;

   @Autowired
   private ProductServiceImpl productService;


    @Test
    void saveProductOnUpdate() {
        Product productToMock = new Product(123L,
                "name",
                "gtin",
                "size",
                "brand",
                BigDecimal.valueOf(1.2)
                ,null
        );

        Product product = new Product(123L,
                "name",
                "gtin",
                "size",
                "brand",
                BigDecimal.valueOf(1.2),
                null
        );
        when( productRepo.findByGtin( product.getGtin() ) )
                .thenReturn(productToMock);
        when( productRepo.save((Product) any()))
                .thenReturn(productToMock);

        Product savedProduct = productService.saveProduct(product);
        assertSame(productToMock, savedProduct);
        verify(productRepo).findByGtin(product.getGtin());
    }

    @Test
    void getAllProducts() {
        ArrayList<Product> products = new ArrayList<>();
        when(productRepo.findAll()).thenReturn(products);
        List<Product> actualAllProducts = productService.getAllProducts();

        assertSame(products, actualAllProducts);
        assertTrue(actualAllProducts.isEmpty());

        verify(productRepo).findAll();

    }

    @Test
    void getProductByGtin() {
        Product productToMock = new Product(123L,
                "name",
                "gtin",
                "size",
                "brand",
                BigDecimal.valueOf(1.2),
                null
        );
        when(productRepo.findByGtin(productToMock.getGtin()))
                .thenReturn(productToMock);
        Product actualProd = productService.getProductByGtin("gtin");
        assertSame(actualProd, productToMock);
        verify(productRepo).findByGtin((String) any());
    }

    @Test
    void getProduct() {
        Product productToMock = new Product(123L,
                "name",
                "gtin",
                "size",
                "brand",
                BigDecimal.valueOf(1.2),
                null
        );
        Optional<Product> optionalResultProduct = Optional.of(productToMock);
        when(productRepo.findById(productToMock.getId())).thenReturn(optionalResultProduct);

        Product actualProduct = productService.getProduct(productToMock.getId());
        assertSame(actualProduct, productToMock);

        verify(productRepo).findById(productToMock.getId());

    }

    @Test
    void removeProduct() {
        when(productRepo.removeById(123L)).thenReturn(123L);

        Product product = new Product(123L,
                "name",
                "gtin",
                "size",
                "brand",
                BigDecimal.valueOf(1.2),
                null
        );

        assertTrue(productService.removeProduct(product));
        verify(productRepo).removeById(123L);

    }
}