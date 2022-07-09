package com.lideme.vendinha.api;

import com.lideme.vendinha.domain.Product;
import com.lideme.vendinha.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/products")
public class ProductController {

    private static final int PAGE_SIZE = 3;
    @Autowired
    IProductService productService;

    @Autowired
    private ApplicationEventPublisher eventPublisher;

    @GetMapping("/{gtin}")
    public ResponseEntity<Product> getProductByGtin(@PathVariable("gtin") String gtin){

        try {
            return ResponseEntity.ok(this.productService.getProductByGtin(gtin));
        }catch (EntityNotFoundException err){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts(){
        try {
            List<Product> prods = productService.getAllProducts();
            return ResponseEntity.ok(prods);
        }catch (Exception err){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @GetMapping(params = {"page"})
    public ResponseEntity<Map<String, Object>> getAllProductsPageable(@RequestParam("page") int page){
        try {
            Page<Product> resultPage = productService.getAllProductsPaged(page,PAGE_SIZE);
            Map<String, Object> response = new HashMap<>();
            response.put("products", resultPage.getContent());
            response.put("currentPage", resultPage.getNumber());
            response.put("totalItems", resultPage.getTotalElements());
            response.put("totalPages", resultPage.getTotalPages());
            return ResponseEntity.ok(response);
        }catch (Exception err){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @PostMapping
    public ResponseEntity<Product> addProduct(@Valid @RequestBody Product product){
        try {
            Product newProduct = this.productService.saveProduct(product);
            return ResponseEntity.status(HttpStatus.CREATED).body(newProduct);
        }catch (UnsupportedOperationException err){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, err.getMessage(),err);
        }catch (Exception err){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Generic Error");
        }
    }
}
