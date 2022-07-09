package com.lideme.vendinha.service;

import com.lideme.vendinha.domain.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IProductService {
    Product saveProduct(Product product);

    List<Product> getAllProducts();
    
    Page<Product> getAllProductsPaged(int page, int size);

    Product getProductByGtin(String gtin);

    Product getProduct(Long id);

    Boolean removeProduct(Product product);

}
