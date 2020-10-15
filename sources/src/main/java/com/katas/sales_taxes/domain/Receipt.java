package com.katas.sales_taxes.domain;

import java.util.Map;

public class Receipt {

    private Map<Good, Integer> purchases;

    public void addPurchase(Good good, Integer quantity) {
        purchases.put(good, quantity);
    }
}
