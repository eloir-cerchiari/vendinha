package com.lideme.vendinha.api;

import com.lideme.vendinha.domain.Product;
import com.lideme.vendinha.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    IProductService IProductService;

    @GetMapping("/{gtin}")
    public ResponseEntity<Product> getProductByGtin(@PathVariable("gtin") String gtin){

        try {
            return ResponseEntity.ok(this.IProductService.getProductByGtin(gtin));
        }catch (EntityNotFoundException err){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts(){
        try {
            return ResponseEntity.ok(IProductService.getAllProducts());
        }catch (Exception err){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @PostMapping
    public ResponseEntity<Product> addProduct(@Valid @RequestBody Product product){
        try {
            Product newProduct = this.IProductService.saveProduct(product);
            return ResponseEntity.status(HttpStatus.CREATED).body(newProduct);
        }catch (UnsupportedOperationException err){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, err.getMessage(),err);
        }catch (Exception err){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Generic Error");
        }
    }
}
