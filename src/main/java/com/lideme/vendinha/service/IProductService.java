package com.lideme.vendinha.service;

import com.lideme.vendinha.domain.Product;

import java.util.List;

public interface IProductService {
    Product saveProduct(Product product);

    List<Product> getAllProducts();

    Product getProductByGtin(String gtin);

    Product getProduct(Long id);

    Boolean removeProduct(Product product);

}
