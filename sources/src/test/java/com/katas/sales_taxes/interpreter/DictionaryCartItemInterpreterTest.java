package com.katas.sales_taxes.interpreter;

import com.katas.sales_taxes.domain.Good;
import com.katas.sales_taxes.repository.FileUntaxedItemRepository;
import com.katas.sales_taxes.repository.UntaxedItemRepository;
import javafx.util.Pair;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DictionaryCartItemInterpreterTest {

    @Test
    void interpretTaxableGood() {
        UntaxedItemRepository untaxedItemRepository = new FileUntaxedItemRepository("src/test/resources/untaxable.txt");
        CartItemInterpreter cartItemInterpreter = new DictionaryCartItemInterpreter(untaxedItemRepository);
        Pair<Good, Integer> cartItem = cartItemInterpreter.interpret("1 music CD at 14.99");
        Good good = new Good();
        good.setName("music CD");
        good.setTaxable(true);
        good.setPrice(14.99);

        assertEquals(1, cartItem.getValue());
        assertEquals(good, cartItem.getKey());
    }

    @Test
    void interpretUntaxableGood() {
        UntaxedItemRepository untaxedItemRepository = new FileUntaxedItemRepository("src/test/resources/untaxable.txt");
        CartItemInterpreter cartItemInterpreter = new DictionaryCartItemInterpreter(untaxedItemRepository);
        Pair<Good, Integer> cartItem = cartItemInterpreter.interpret("2 chocolate bar at 1.50");
        Good good = new Good();
        good.setName("chocolate bar");
        good.setPrice(0.75);

        assertEquals(2, cartItem.getValue());
        assertEquals(good, cartItem.getKey());
    }

    @Test
    void interpretImportedUntaxableGood() {
        UntaxedItemRepository untaxedItemRepository = new FileUntaxedItemRepository("src/test/resources/untaxable.txt");
        CartItemInterpreter cartItemInterpreter = new DictionaryCartItemInterpreter(untaxedItemRepository);
        Pair<Good, Integer> cartItem = cartItemInterpreter.interpret("2 imported chocolate bar at 1.50");
        Good good = new Good();
        good.setName("chocolate bar");
        good.setImported(true);
        good.setPrice(0.75);

        assertEquals(2, cartItem.getValue());
        assertEquals(good, cartItem.getKey());
    }

    @Test
    void interpretImportedTaxableGood() {
        UntaxedItemRepository untaxedItemRepository = new FileUntaxedItemRepository("src/test/resources/untaxable.txt");
        CartItemInterpreter cartItemInterpreter = new DictionaryCartItemInterpreter(untaxedItemRepository);
        Pair<Good, Integer> cartItem = cartItemInterpreter.interpret("1 imported perfume at 32.25");
        Good good = new Good();
        good.setName("perfume");
        good.setImported(true);
        good.setTaxable(true);
        good.setPrice(32.25);

        assertEquals(1, cartItem.getValue());
        assertEquals(good, cartItem.getKey());
    }
}