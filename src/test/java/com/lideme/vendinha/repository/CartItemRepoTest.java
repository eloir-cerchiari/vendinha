package com.lideme.vendinha.repository;

import com.lideme.vendinha.domain.Cart;
import com.lideme.vendinha.domain.CartItem;
import com.lideme.vendinha.domain.Product;
import com.lideme.vendinha.repo.CartItemRepo;
import com.lideme.vendinha.repo.CartRepo;
import com.lideme.vendinha.repo.OrderRepo;
import com.lideme.vendinha.repo.ProductRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.annotation.Rollback;


import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;


@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class CartItemRepoTest {
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
    public void createCart2Test(){
        Product product = productRepo.findByGtin("8787878787878");
        Cart cart = new Cart();
        cartRepo.save(cart);

        cart.getItems().add(new CartItem(null,cart,product,BigDecimal.ONE, LocalDateTime.now()));

        Assertions.assertNotNull(cart.getItems());
        Assertions.assertTrue(cart.getItems().stream().findFirst().isPresent());
        Assertions.assertNotNull(cart.getItems().stream().findFirst().get().getProduct().getId());
    }

    @Test
    public void listItensFromCartTest(){
        //Not perfect, because need create fake data on setup
        Cart firstCart = cartRepo.findAll(PageRequest.of(0,1)).stream().findFirst().get();
        Assertions.assertNotNull(firstCart.getItems());
        Assertions.assertTrue(firstCart.getItems().stream().findFirst().isPresent());
        Assertions.assertNotNull(firstCart.getItems().stream().findFirst().get().getProduct().getId());
    }
}
