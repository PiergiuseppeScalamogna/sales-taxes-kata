package com.katas.sales_taxes.domain;

import javafx.util.Pair;

import java.util.LinkedList;
import java.util.List;

public class Cart {

    private LinkedList<Pair<Good, Integer>> content;

    public Cart() {
        this.content = new LinkedList<>();
    }

    public List<Pair<Good, Integer>> getContent() {
        return content;
    }

    public void add(Good good, Integer quantity) {
        Pair<Good, Integer> toAdd = new Pair<>(good, quantity);
        content.addLast(toAdd);
    }
}
