package com.katas.sales_taxes.interpreter;

import com.katas.sales_taxes.domain.CartItem;
import com.katas.sales_taxes.domain.Good;
import com.katas.sales_taxes.exception.UnableToReadRepositoryException;
import com.katas.sales_taxes.repository.UntaxedGoodRepository;

public class DictionaryCartItemInterpreter implements CartItemInterpreter {

    private final UntaxedGoodRepository untaxedItemRepository;

    public DictionaryCartItemInterpreter(UntaxedGoodRepository untaxedItemRepository) {
        this.untaxedItemRepository = untaxedItemRepository;
    }

    @Override
    public CartItem interpret(String cartItem) {
        String[] tokens = tokenize(cartItem);
        int quantity = Integer.parseInt(tokens[0]);
        Good good = createBaseGoodFrom(tokens);
        good.setPrice(Double.parseDouble(tokens[tokens.length - 1]) / quantity);
        if (isTaxable(good.getName())) {
            good.setTaxable(true);
        }

        return new CartItem(good, quantity);
    }

    private String[] tokenize(String cartItem) {
        String cleaned = cartItem.replaceAll("\\s+", " ");
        return cleaned.split(" ");
    }

    private Good createBaseGoodFrom(String[] tokens) {
        Good good = new Good();
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 1; i < tokens.length - 2; i++) {
            if (tokens[i].equalsIgnoreCase("imported")) {
                good.setImported(true);
            } else {
                stringBuilder.append(tokens[i]);
                stringBuilder.append(" ");
            }
        }
        good.setName(stringBuilder.deleteCharAt(stringBuilder.length() - 1).toString());
        return good;
    }

    private boolean isTaxable(String name) {
        try {
            return !untaxedItemRepository.find(name);
        } catch (UnableToReadRepositoryException e) {
            e.printStackTrace();
            return true;
        }
    }
}
