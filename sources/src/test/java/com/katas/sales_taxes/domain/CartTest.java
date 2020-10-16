package com.katas.sales_taxes.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

class CartTest {

    @Test
    void add() {
        Good good = new Good();
        good.setName("bread");
        Cart cart = new Cart();
        cart.add(good, 2);

        assertTrue(cart.getContent().get(good) == 2);
    }

    @Test
    void addAnotherOne() {
        Good good1 = new Good();
        good1.setName("bread");
        Good good2 = new Good();
        good2.setName("bread");
        Cart cart = new Cart();
        cart.add(good1, 1);
        cart.add(good2, 1);

        assertTrue(cart.getContent().get(good1) == 2);
    }
}