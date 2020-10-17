package com.katas.sales_taxes.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ReceiptTest {

    @Test
    void getTotalNoTaxes() {
        Receipt receipt = new Receipt();
        Purchase purchase = new Purchase();
        purchase.setGood(new Good("bread", false, false, 2.5));
        purchase.setQuantity(2);
        Purchase purchase1 = new Purchase();
        purchase1.setGood(new Good("chocolate", false, false, 2.5));
        purchase1.setQuantity(1);
        receipt.add(purchase);
        receipt.add(purchase1);

        assertEquals(7.5, receipt.getTotal(), 0.000001);
    }

    @Test
    void getTotalWithTaxes() {
        Receipt receipt = new Receipt();
        Purchase purchase = new Purchase();
        purchase.setGood(new Good("bread", false, false, 2.5));
        purchase.setQuantity(2);
        Purchase purchase1 = new Purchase();
        purchase1.setGood(new Good("soap", true, false, 2.5));
        purchase1.setQuantity(1);
        purchase1.setTax(0.25);
        receipt.add(purchase);
        receipt.add(purchase1);

        assertEquals(7.75, receipt.getTotal(), 0.000001);
    }

    @Test
    void getTaxes() {
        Receipt receipt = new Receipt();
        Purchase purchase = new Purchase();
        purchase.setGood(new Good("perfume", true, false, 32.5));
        purchase.setQuantity(1);
        purchase.setTax(3.25);
        Purchase purchase1 = new Purchase();
        purchase1.setGood(new Good("soap", true, false, 2.5));
        purchase1.setQuantity(1);
        purchase1.setTax(0.25);
        receipt.add(purchase);
        receipt.add(purchase1);

        assertEquals(3.5, receipt.getTaxes(), 0.000001);
    }
}