package com.lideme.vendinha.repository;

import com.lideme.vendinha.domain.Cart;
import com.lideme.vendinha.domain.CartItem;
import com.lideme.vendinha.domain.Product;
import com.lideme.vendinha.repo.CartItemRepo;
import com.lideme.vendinha.repo.CartRepo;
import com.lideme.vendinha.repo.OrderRepo;
import com.lideme.vendinha.repo.ProductRepo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;


import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;


@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Transactional
public class CartTest {
    @Autowired
    OrderRepo orderRepo;

    @Autowired
    CartItemRepo cartItemRepo;

    @Autowired
    ProductRepo productRepo;

    @Autowired
    CartRepo cartRepo;
    @Test
    @Rollback(value = false)
    public void createCartTest(){
        Product product = productRepo.findByGtin("8787878787878");
        Cart cart = new Cart();
        cartRepo.save(cart);

        CartItem cartItem = new CartItem();
        cartItem.setCart(cart);
        cartItem.setProduct(product);
        cartItem.setQuantity(BigDecimal.valueOf(10));

        cartItemRepo.save(cartItem);

//        cartRepo.save(cart);


        Cart cart2 = cartRepo.findCart(cart.getId());

        System.out.println(cart2.toString());



    }
}
