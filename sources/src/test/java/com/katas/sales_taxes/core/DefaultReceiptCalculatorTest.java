package com.katas.sales_taxes.core;

import com.katas.sales_taxes.domain.Cart;
import com.katas.sales_taxes.domain.Good;
import com.katas.sales_taxes.domain.Receipt;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class DefaultReceiptCalculatorTest {

    private TaxCalculator taxCalculator;
    private ReceiptCalculator receiptCalculator;

    @BeforeAll
    private void init() {
        taxCalculator = new DefaultTaxCalculator();
        receiptCalculator = new DefaultReceiptCalculator(taxCalculator);
    }

    @Test
    void calculateOnVoidCart() {
        Cart cart = new Cart();
        Receipt receipt = receiptCalculator.calculate(cart);

        assertEquals(0, receipt.getTaxes());
        assertEquals(0, receipt.getTotal());
    }

    @Test
    void calculateOnOneElementCart() {
        Cart cart = new Cart();
        Good good = new Good("soap", true, false, 1.95);
        cart.add(good, 1);
        Receipt receipt = receiptCalculator.calculate(cart);

        assertEquals(0.2, receipt.getTaxes(), 0.000001);
        assertEquals(2.15, receipt.getTotal(), 0.000001);
    }

    @Test
    void calculateOnMultipleElementCart() {
        TaxCalculator taxCalculator = new DefaultTaxCalculator();
        ReceiptCalculator receiptCalculator = new DefaultReceiptCalculator(taxCalculator);
        Cart cart = new Cart();
        Good good = new Good("soap", true, false, 1.95);
        Good good1 = new Good("coffee", false, true, 4.38);
        cart.add(good, 1);
        cart.add(good1, 4);
        Receipt receipt = receiptCalculator.calculate(cart);

        assertEquals(1.2, receipt.getTaxes(), 0.000001);
        assertEquals(20.67, receipt.getTotal(), 0.000001);
    }
}