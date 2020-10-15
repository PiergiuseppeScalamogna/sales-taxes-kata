package com.katas.sales_taxes.domain;

import java.util.LinkedList;
import java.util.List;

public class Receipt {

    private List<Purchase> purchases;

    public Receipt() {
        this.purchases = new LinkedList<>();
    }

    public List<Purchase> getPurchases() {
        return purchases;
    }

    public void add(Purchase purchase) {
        purchases.add(purchase);
    }

    public double getTotal() {
        return purchases.stream()
                .map(purchase -> (purchase.getGood().getPrice() * purchase.getQuantity()) + purchase.getTax())
                .reduce(0.0, Double::sum);
    }

    public double getTaxes() {
        return purchases.stream()
                .map(Purchase::getTax)
                .reduce(0.0, Double::sum);
    }
}
