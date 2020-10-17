package com.katas.sales_taxes.domain;

public class CartItem {

    private Good good;
    private Integer quantity;

    public CartItem(Good good, Integer quantity) {
        this.good = good;
        this.quantity = quantity;
    }

    public Good getGood() {
        return good;
    }

    public Integer getQuantity() {
        return quantity;
    }
}
