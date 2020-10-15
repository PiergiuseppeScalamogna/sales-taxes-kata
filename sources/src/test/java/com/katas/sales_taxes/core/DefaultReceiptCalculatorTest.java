package com.katas.sales_taxes.core;

import com.katas.sales_taxes.domain.Cart;
import com.katas.sales_taxes.domain.Good;
import com.katas.sales_taxes.domain.Receipt;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DefaultReceiptCalculatorTest {

    @Test
    void calculateOnVoidCart() {
        TaxCalculator taxCalculator = new DefaultTaxCalculator();
        ReceiptCalculator receiptCalculator = new DefaultReceiptCalculator(taxCalculator);
        Cart cart = new Cart();
        Receipt receipt = receiptCalculator.calculate(cart);
        assertEquals(0, receipt.getTaxes());
        assertEquals(0, receipt.getTotal());
    }

    @Test
    void calculateOnOneElementCart() {
        TaxCalculator taxCalculator = new DefaultTaxCalculator();
        ReceiptCalculator receiptCalculator = new DefaultReceiptCalculator(taxCalculator);
        Cart cart = new Cart();
        Good good = new Good();
        good.setName("soap");
        good.setPrice(1.95);
        good.setTaxable(true);
        cart.add(good, 1);
        Receipt receipt = receiptCalculator.calculate(cart);
        assertEquals(0.195, receipt.getTaxes(), 0.000001);
        assertEquals(2.145, receipt.getTotal(), 0.000001);
    }

    @Test
    void calculateOnMultipleElementCart() {
        TaxCalculator taxCalculator = new DefaultTaxCalculator();
        ReceiptCalculator receiptCalculator = new DefaultReceiptCalculator(taxCalculator);
        Cart cart = new Cart();
        Good good = new Good();
        good.setName("soap");
        good.setPrice(1.95);
        good.setTaxable(true);
        Good good1 = new Good();
        good1.setName("coffee");
        good1.setPrice(4.38);
        good1.setTaxable(false);
        good1.setImported(true);
        cart.add(good, 1);
        cart.add(good1, 4);
        Receipt receipt = receiptCalculator.calculate(cart);
        assertEquals(1.071, receipt.getTaxes(), 0.000001);
        assertEquals(20.541, receipt.getTotal(), 0.000001);
    }
}