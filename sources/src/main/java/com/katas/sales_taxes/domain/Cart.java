package com.katas.sales_taxes.domain;

import java.util.LinkedList;
import java.util.List;

public class Cart {

    private LinkedList<CartItem> content;

    public Cart() {
        this.content = new LinkedList<>();
    }

    public List<CartItem> getContent() {
        return content;
    }

    public void add(Good good, Integer quantity) {
        CartItem toAdd = new CartItem(good, quantity);
        content.addLast(toAdd);
    }
}
