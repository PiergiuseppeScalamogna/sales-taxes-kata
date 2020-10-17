package com.katas.sales_taxes.interpreter;

import com.katas.sales_taxes.domain.CartItem;
import com.katas.sales_taxes.domain.Good;
import com.katas.sales_taxes.exception.UnableToReadRepositoryException;
import com.katas.sales_taxes.repository.UntaxedGoodRepository;

public class DictionaryCartItemInterpreter implements CartItemInterpreter {

    private UntaxedGoodRepository untaxedItemRepository;

    public DictionaryCartItemInterpreter(UntaxedGoodRepository untaxedItemRepository) {
        this.untaxedItemRepository = untaxedItemRepository;
    }

    @Override
    public CartItem interpret(String cartItem) {
        Good good = new Good();
        String cleaned = cartItem.replaceAll("\\s+", " ");
        String[] tokens = cleaned.split(" ");
        Integer quantity = Integer.parseInt(tokens[0]);
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 1; i < tokens.length - 2; i++) {
            if (tokens[i].equalsIgnoreCase("imported")) {
                good.setImported(true);
            } else {
                stringBuilder.append(tokens[i]);
                stringBuilder.append(" ");
            }
        }
        good.setPrice(Double.parseDouble(tokens[tokens.length - 1]) / quantity);
        good.setName(stringBuilder.deleteCharAt(stringBuilder.length() - 1).toString());
        if (isTaxable(good.getName())) {
            good.setTaxable(true);
        }

        return new CartItem(good, quantity);
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
