package com.katas.sales_taxes.domain;

import java.util.HashMap;
import java.util.Map;

public class Cart {

    private Map<Good, Integer> content;

    public Cart() {
        this.content = new HashMap<>();
    }

    public Map<Good, Integer> getContent() {
        return content;
    }

    public void add(Good good, Integer quantity) {
        if (content.containsKey(good)) {
            content.put(good, content.get(good) + quantity);
        } else {
            content.put(good, quantity);
        }
    }
}
