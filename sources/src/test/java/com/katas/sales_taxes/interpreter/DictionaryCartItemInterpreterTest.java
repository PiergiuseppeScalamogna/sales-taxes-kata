package com.katas.sales_taxes.interpreter;

import com.katas.sales_taxes.domain.CartItem;
import com.katas.sales_taxes.domain.Good;
import com.katas.sales_taxes.repository.FileUntaxedGoodRepository;
import com.katas.sales_taxes.repository.UntaxedGoodRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class DictionaryCartItemInterpreterTest {

    private UntaxedGoodRepository untaxedItemRepository;
    private CartItemInterpreter cartItemInterpreter;

    @BeforeAll
    public void init() {
        untaxedItemRepository = new FileUntaxedGoodRepository("src/test/resources/untaxable.txt");
        cartItemInterpreter = new DictionaryCartItemInterpreter(untaxedItemRepository);
    }

    @Test
    void interpretTaxableGood() {
        CartItem cartItem = cartItemInterpreter.interpret("1 music CD at 14.99");
        Good good = new Good("music CD", true, false, 14.99);

        assertEquals(1, cartItem.getQuantity());
        assertEquals(good, cartItem.getGood());
    }

    @Test
    void interpretUntaxableGood() {
        CartItem cartItem = cartItemInterpreter.interpret("2 chocolate bar at 1.50");
        Good good = new Good("chocolate bar", false, false, 0.75);

        assertEquals(2, cartItem.getQuantity());
        assertEquals(good, cartItem.getGood());
    }

    @Test
    void interpretImportedUntaxableGood() {
        CartItem cartItem = cartItemInterpreter.interpret("2 imported chocolate bar at 1.50");
        Good good = new Good("chocolate bar", false, true, 0.75);

        assertEquals(2, cartItem.getQuantity());
        assertEquals(good, cartItem.getGood());
    }

    @Test
    void interpretImportedTaxableGood() {
        CartItem cartItem = cartItemInterpreter.interpret("1 imported perfume at 32.25");
        Good good = new Good("perfume", true, true, 32.25);

        assertEquals(1, cartItem.getQuantity());
        assertEquals(good, cartItem.getGood());
    }
}