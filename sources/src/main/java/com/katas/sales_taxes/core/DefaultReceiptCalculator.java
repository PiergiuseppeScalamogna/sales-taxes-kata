package com.katas.sales_taxes.core;

import com.katas.sales_taxes.domain.Cart;
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
        cart.getContent().forEach(cartItem -> {
            Purchase purchase = new Purchase();
            purchase.setGood(cartItem.getGood());
            purchase.setQuantity(cartItem.getQuantity());
            purchase.setTax(taxCalculator.taxOf(cartItem.getGood()) * cartItem.getQuantity());
            receipt.add(purchase);
        });
        return receipt;
    }
}
