package com.katas.sales_taxes.core;

import com.katas.sales_taxes.domain.Cart;
import com.katas.sales_taxes.domain.Good;
import com.katas.sales_taxes.domain.Purchase;
import com.katas.sales_taxes.domain.Receipt;

public class DefaultReceiptCalculator implements ReceiptCalculator {

    private TaxCalculator taxCalculator;

    public DefaultReceiptCalculator(TaxCalculator taxCalculator) {
        this.taxCalculator = taxCalculator;
    }

    @Override
    public Receipt calculate(Cart cart) {
        Receipt receipt = new Receipt();
        cart.getContent().forEach((good, quantity) -> {
            Purchase purchase = new Purchase();
            purchase.setGood(good);
            purchase.setQuantity(quantity);
            purchase.setTax(taxCalculator.taxOf(good) * quantity);
            receipt.add(purchase);
        });
        return receipt;
    }
}
