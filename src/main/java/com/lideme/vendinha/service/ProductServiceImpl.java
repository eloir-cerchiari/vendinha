package com.lideme.vendinha.service;

import com.lideme.vendinha.domain.Product;
import com.lideme.vendinha.repo.ProductRepo;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor @NoArgsConstructor @Service @Transactional
public class ProductServiceImpl implements IProductService {


    @Autowired
    private ProductRepo productRepo;

    @Override
    public Product saveProduct(Product product) {

        if(!product.getGtin().isEmpty()){
            Product existsProduct = this.getProductByGtin(product.getGtin());
            if(existsProduct != null && ! existsProduct.getId().equals(product.getId())){
                throw new UnsupportedOperationException("The product gtin has exists");
            }
        }

        return this.productRepo.save(product);
    }

    @Override
    public List<Product> getAllProducts() {
        return this.productRepo.findAll();
    }

    @Override
    public Product getProductByGtin(String gtin) {
        return this.productRepo.findByGtin(gtin);
    }

    @Override
    public Product getProduct(Long id) {
        Optional<Product> optional = this.productRepo.findById(id);
        if(!optional.isPresent()){
            throw new EntityNotFoundException("The product not exists");
        }
        return optional.get();
    }

    @Override
    public Boolean removeProduct(Product product) {
         return this.productRepo.removeById(product.getId()) >0;
    }
}
