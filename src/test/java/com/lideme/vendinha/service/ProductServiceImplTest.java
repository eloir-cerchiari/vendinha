package com.lideme.vendinha.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.lideme.vendinha.domain.Product;
import com.lideme.vendinha.repo.ProductRepo;

import java.math.BigDecimal;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.persistence.EntityNotFoundException;

import org.junit.jupiter.api.Disabled;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {ProductServiceImpl.class})
@ExtendWith(SpringExtension.class)
class ProductServiceImplTest {
    @MockBean
    private ProductRepo productRepo;

    @Autowired
    private ProductServiceImpl productServiceImpl;

    /**
     * Method under test: {@link ProductServiceImpl#saveProduct(Product)}
     */
    @Test
    void testSaveProduct() {
        Product product = new Product();
        product.setBrand("Brand");
        product.setGtin("Gtin");
        product.setId(123L);
        product.setName("Name");
        product.setPrice(BigDecimal.valueOf(42L));
        product.setSize("Size");

        Product product1 = new Product();
        product1.setBrand("Brand");
        product1.setGtin("Gtin");
        product1.setId(123L);
        product1.setName("Name");
        product1.setPrice(BigDecimal.valueOf(42L));
        product1.setSize("Size");
        when(productRepo.findByGtin((String) any())).thenReturn(product);
        when(productRepo.save((Product) any())).thenReturn(product1);

        Product product2 = new Product();
        product2.setBrand("Brand");
        product2.setGtin("Gtin");
        product2.setId(123L);
        product2.setName("Name");
        product2.setPrice(BigDecimal.valueOf(42L));
        product2.setSize("Size");
        Product actualSaveProductResult = productServiceImpl.saveProduct(product2);
        assertSame(product1, actualSaveProductResult);
        assertEquals("42", actualSaveProductResult.getPrice().toString());
        verify(productRepo).findByGtin((String) any());
        verify(productRepo).save((Product) any());
    }

    /**
     * Method under test: {@link ProductServiceImpl#saveProduct(Product)}
     */
    @Test
    void testSaveProduct2() {
        Product product = mock(Product.class);
        when(product.getId()).thenReturn(1L);
        doNothing().when(product).setBrand((String) any());
        doNothing().when(product).setGtin((String) any());
        doNothing().when(product).setId((Long) any());
        doNothing().when(product).setName((String) any());
        doNothing().when(product).setPrice((BigDecimal) any());
        doNothing().when(product).setSize((String) any());
        product.setBrand("Brand");
        product.setGtin("Gtin");
        product.setId(123L);
        product.setName("Name");
        product.setPrice(BigDecimal.valueOf(42L));
        product.setSize("Size");

        Product product1 = new Product();
        product1.setBrand("Brand");
        product1.setGtin("Gtin");
        product1.setId(123L);
        product1.setName("Name");
        product1.setPrice(BigDecimal.valueOf(42L));
        product1.setSize("Size");
        when(productRepo.findByGtin((String) any())).thenReturn(product);
        when(productRepo.save((Product) any())).thenReturn(product1);

        Product product2 = new Product();
        product2.setBrand("Brand");
        product2.setGtin("Gtin");
        product2.setId(123L);
        product2.setName("Name");
        product2.setPrice(BigDecimal.valueOf(42L));
        product2.setSize("Size");
        assertThrows(UnsupportedOperationException.class, () -> productServiceImpl.saveProduct(product2));
        verify(productRepo).findByGtin((String) any());
        verify(product).getId();
        verify(product).setBrand((String) any());
        verify(product).setGtin((String) any());
        verify(product).setId((Long) any());
        verify(product).setName((String) any());
        verify(product).setPrice((BigDecimal) any());
        verify(product).setSize((String) any());
    }

    /**
     * Method under test: {@link ProductServiceImpl#saveProduct(Product)}
     */
    @Test
    void testSaveProduct3() {
        Product product = mock(Product.class);
        when(product.getId()).thenReturn(123L);
        doNothing().when(product).setBrand((String) any());
        doNothing().when(product).setGtin((String) any());
        doNothing().when(product).setId((Long) any());
        doNothing().when(product).setName((String) any());
        doNothing().when(product).setPrice((BigDecimal) any());
        doNothing().when(product).setSize((String) any());
        product.setBrand("Brand");
        product.setGtin("Gtin");
        product.setId(123L);
        product.setName("Name");
        product.setPrice(BigDecimal.valueOf(42L));
        product.setSize("Size");

        Product product1 = new Product();
        product1.setBrand("Brand");
        product1.setGtin("Gtin");
        product1.setId(123L);
        product1.setName("Name");
        product1.setPrice(BigDecimal.valueOf(42L));
        product1.setSize("Size");
        when(productRepo.findByGtin((String) any())).thenReturn(product);
        when(productRepo.save((Product) any())).thenReturn(product1);
        Product product2 = mock(Product.class);
        when(product2.getId()).thenThrow(new UnsupportedOperationException("An error occurred"));
        when(product2.getGtin()).thenReturn("Gtin");
        doNothing().when(product2).setBrand((String) any());
        doNothing().when(product2).setGtin((String) any());
        doNothing().when(product2).setId((Long) any());
        doNothing().when(product2).setName((String) any());
        doNothing().when(product2).setPrice((BigDecimal) any());
        doNothing().when(product2).setSize((String) any());
        product2.setBrand("Brand");
        product2.setGtin("Gtin");
        product2.setId(123L);
        product2.setName("Name");
        product2.setPrice(BigDecimal.valueOf(42L));
        product2.setSize("Size");
        assertThrows(UnsupportedOperationException.class, () -> productServiceImpl.saveProduct(product2));
        verify(productRepo).findByGtin((String) any());
        verify(product).getId();
        verify(product).setBrand((String) any());
        verify(product).setGtin((String) any());
        verify(product).setId((Long) any());
        verify(product).setName((String) any());
        verify(product).setPrice((BigDecimal) any());
        verify(product).setSize((String) any());
        verify(product2).getId();
        verify(product2, atLeast(1)).getGtin();
        verify(product2).setBrand((String) any());
        verify(product2).setGtin((String) any());
        verify(product2).setId((Long) any());
        verify(product2).setName((String) any());
        verify(product2).setPrice((BigDecimal) any());
        verify(product2).setSize((String) any());
    }

    /**
     * Method under test: {@link ProductServiceImpl#saveProduct(Product)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testSaveProduct4() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException
        //       at com.lideme.vendinha.service.ProductServiceImpl.saveProduct(ProductServiceImpl.java:26)
        //   In order to prevent saveProduct(Product)
        //   from throwing NullPointerException, add constructors or factory
        //   methods that make it easier to construct fully initialized objects used in
        //   saveProduct(Product).
        //   See https://diff.blue/R013 to resolve this issue.

        Product product = mock(Product.class);
        when(product.getId()).thenReturn(123L);
        doNothing().when(product).setBrand((String) any());
        doNothing().when(product).setGtin((String) any());
        doNothing().when(product).setId((Long) any());
        doNothing().when(product).setName((String) any());
        doNothing().when(product).setPrice((BigDecimal) any());
        doNothing().when(product).setSize((String) any());
        product.setBrand("Brand");
        product.setGtin("Gtin");
        product.setId(123L);
        product.setName("Name");
        product.setPrice(BigDecimal.valueOf(42L));
        product.setSize("Size");

        Product product1 = new Product();
        product1.setBrand("Brand");
        product1.setGtin("Gtin");
        product1.setId(123L);
        product1.setName("Name");
        product1.setPrice(BigDecimal.valueOf(42L));
        product1.setSize("Size");
        when(productRepo.findByGtin((String) any())).thenReturn(product);
        when(productRepo.save((Product) any())).thenReturn(product1);
        Product product2 = mock(Product.class);
        when(product2.getId()).thenThrow(new UnsupportedOperationException("An error occurred"));
        when(product2.getGtin()).thenReturn(null);
        doNothing().when(product2).setBrand((String) any());
        doNothing().when(product2).setGtin((String) any());
        doNothing().when(product2).setId((Long) any());
        doNothing().when(product2).setName((String) any());
        doNothing().when(product2).setPrice((BigDecimal) any());
        doNothing().when(product2).setSize((String) any());
        product2.setBrand("Brand");
        product2.setGtin("Gtin");
        product2.setId(123L);
        product2.setName("Name");
        product2.setPrice(BigDecimal.valueOf(42L));
        product2.setSize("Size");
        productServiceImpl.saveProduct(product2);
    }

    /**
     * Method under test: {@link ProductServiceImpl#saveProduct(Product)}
     */
    @Test
    void testSaveProduct5() {
        Product product = mock(Product.class);
        when(product.getId()).thenReturn(123L);
        doNothing().when(product).setBrand((String) any());
        doNothing().when(product).setGtin((String) any());
        doNothing().when(product).setId((Long) any());
        doNothing().when(product).setName((String) any());
        doNothing().when(product).setPrice((BigDecimal) any());
        doNothing().when(product).setSize((String) any());
        product.setBrand("Brand");
        product.setGtin("Gtin");
        product.setId(123L);
        product.setName("Name");
        product.setPrice(BigDecimal.valueOf(42L));
        product.setSize("Size");

        Product product1 = new Product();
        product1.setBrand("Brand");
        product1.setGtin("Gtin");
        product1.setId(123L);
        product1.setName("Name");
        product1.setPrice(BigDecimal.valueOf(42L));
        product1.setSize("Size");
        when(productRepo.findByGtin((String) any())).thenReturn(product);
        when(productRepo.save((Product) any())).thenReturn(product1);
        Product product2 = mock(Product.class);
        when(product2.getId()).thenThrow(new UnsupportedOperationException("An error occurred"));
        when(product2.getGtin()).thenReturn("");
        doNothing().when(product2).setBrand((String) any());
        doNothing().when(product2).setGtin((String) any());
        doNothing().when(product2).setId((Long) any());
        doNothing().when(product2).setName((String) any());
        doNothing().when(product2).setPrice((BigDecimal) any());
        doNothing().when(product2).setSize((String) any());
        product2.setBrand("Brand");
        product2.setGtin("Gtin");
        product2.setId(123L);
        product2.setName("Name");
        product2.setPrice(BigDecimal.valueOf(42L));
        product2.setSize("Size");
        Product actualSaveProductResult = productServiceImpl.saveProduct(product2);
        assertSame(product1, actualSaveProductResult);
        assertEquals("42", actualSaveProductResult.getPrice().toString());
        verify(productRepo).save((Product) any());
        verify(product).setBrand((String) any());
        verify(product).setGtin((String) any());
        verify(product).setId((Long) any());
        verify(product).setName((String) any());
        verify(product).setPrice((BigDecimal) any());
        verify(product).setSize((String) any());
        verify(product2).getGtin();
        verify(product2).setBrand((String) any());
        verify(product2).setGtin((String) any());
        verify(product2).setId((Long) any());
        verify(product2).setName((String) any());
        verify(product2).setPrice((BigDecimal) any());
        verify(product2).setSize((String) any());
    }

    /**
     * Method under test: {@link ProductServiceImpl#getAllProducts()}
     */
    @Test
    void testGetAllProducts() {
        ArrayList<Product> productList = new ArrayList<>();
        when(productRepo.findAll()).thenReturn(productList);
        List<Product> actualAllProducts = productServiceImpl.getAllProducts();
        assertSame(productList, actualAllProducts);
        assertTrue(actualAllProducts.isEmpty());
        verify(productRepo).findAll();
    }

    /**
     * Method under test: {@link ProductServiceImpl#getAllProducts()}
     */
    @Test
    void testGetAllProducts2() {
        when(productRepo.findAll()).thenThrow(new UnsupportedOperationException("An error occurred"));
        assertThrows(UnsupportedOperationException.class, () -> productServiceImpl.getAllProducts());
        verify(productRepo).findAll();
    }

    /**
     * Method under test: {@link ProductServiceImpl#getProductByGtin(String)}
     */
    @Test
    void testGetProductByGtin() {
        Product product = new Product();
        product.setBrand("Brand");
        product.setGtin("Gtin");
        product.setId(123L);
        product.setName("Name");
        product.setPrice(BigDecimal.valueOf(42L));
        product.setSize("Size");
        when(productRepo.findByGtin((String) any())).thenReturn(product);
        Product actualProductByGtin = productServiceImpl.getProductByGtin("Gtin");
        assertSame(product, actualProductByGtin);
        assertEquals("42", actualProductByGtin.getPrice().toString());
        verify(productRepo).findByGtin((String) any());
    }

    /**
     * Method under test: {@link ProductServiceImpl#getProductByGtin(String)}
     */
    @Test
    void testGetProductByGtin2() {
        when(productRepo.findByGtin((String) any())).thenThrow(new UnsupportedOperationException("An error occurred"));
        assertThrows(UnsupportedOperationException.class, () -> productServiceImpl.getProductByGtin("Gtin"));
        verify(productRepo).findByGtin((String) any());
    }

    /**
     * Method under test: {@link ProductServiceImpl#getProduct(Long)}
     */
    @Test
    void testGetProduct() {
        Product product = new Product();
        product.setBrand("Brand");
        product.setGtin("Gtin");
        product.setId(123L);
        product.setName("Name");
        product.setPrice(BigDecimal.valueOf(42L));
        product.setSize("Size");
        Optional<Product> ofResult = Optional.of(product);
        when(productRepo.findById((Long) any())).thenReturn(ofResult);
        Product actualProduct = productServiceImpl.getProduct(123L);
        assertSame(product, actualProduct);
        assertEquals("42", actualProduct.getPrice().toString());
        verify(productRepo).findById((Long) any());
    }

    /**
     * Method under test: {@link ProductServiceImpl#getProduct(Long)}
     */
    @Test
    void testGetProduct2() {
        when(productRepo.findById((Long) any())).thenReturn(Optional.empty());
        assertThrows(EntityNotFoundException.class, () -> productServiceImpl.getProduct(123L));
        verify(productRepo).findById((Long) any());
    }

    /**
     * Method under test: {@link ProductServiceImpl#getProduct(Long)}
     */
    @Test
    void testGetProduct3() {
        when(productRepo.findById((Long) any())).thenThrow(new UnsupportedOperationException("An error occurred"));
        assertThrows(UnsupportedOperationException.class, () -> productServiceImpl.getProduct(123L));
        verify(productRepo).findById((Long) any());
    }

    /**
     * Method under test: {@link ProductServiceImpl#removeProduct(Product)}
     */
    @Test
    void testRemoveProduct() {
        when(productRepo.removeById((Long) any())).thenReturn(123L);

        Product product = new Product();
        product.setBrand("Brand");
        product.setGtin("Gtin");
        product.setId(123L);
        product.setName("Name");
        product.setPrice(BigDecimal.valueOf(42L));
        product.setSize("Size");
        assertTrue(productServiceImpl.removeProduct(product));
        verify(productRepo).removeById((Long) any());
    }

    /**
     * Method under test: {@link ProductServiceImpl#removeProduct(Product)}
     */
    @Test
    void testRemoveProduct2() {
        when(productRepo.removeById((Long) any())).thenReturn(0L);

        Product product = new Product();
        product.setBrand("Brand");
        product.setGtin("Gtin");
        product.setId(123L);
        product.setName("Name");
        product.setPrice(BigDecimal.valueOf(42L));
        product.setSize("Size");
        assertFalse(productServiceImpl.removeProduct(product));
        verify(productRepo).removeById((Long) any());
    }

    /**
     * Method under test: {@link ProductServiceImpl#removeProduct(Product)}
     */
    @Test
    void testRemoveProduct3() {
        when(productRepo.removeById((Long) any())).thenThrow(new UnsupportedOperationException("An error occurred"));

        Product product = new Product();
        product.setBrand("Brand");
        product.setGtin("Gtin");
        product.setId(123L);
        product.setName("Name");
        product.setPrice(BigDecimal.valueOf(42L));
        product.setSize("Size");
        assertThrows(UnsupportedOperationException.class, () -> productServiceImpl.removeProduct(product));
        verify(productRepo).removeById((Long) any());
    }
}

