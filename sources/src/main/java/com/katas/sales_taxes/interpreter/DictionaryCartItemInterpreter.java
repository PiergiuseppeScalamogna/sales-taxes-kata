package com.katas.sales_taxes.interpreter;

import com.katas.sales_taxes.domain.Good;
import com.katas.sales_taxes.exception.UnableToReadRepositoryException;
import com.katas.sales_taxes.repository.UntaxedItemRepository;
import javafx.util.Pair;

public class DictionaryCartItemInterpreter implements CartItemInterpreter {

    private UntaxedItemRepository untaxedItemRepository;

    public DictionaryCartItemInterpreter(UntaxedItemRepository untaxedItemRepository){
        this.untaxedItemRepository = untaxedItemRepository;
    }

    @Override
    public Pair<Good, Integer> interpret(String cartItem) {
        Good good = new Good();
        String cleaned = cartItem.replaceAll("\\s+", " ");
        String[] tokens = cleaned.split(" ");
        Integer quantity = Integer.parseInt(tokens[0]);
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 1; i < tokens.length - 2; i++) {
            if(tokens[i].equalsIgnoreCase("imported")){
                good.setImported(true);
            } else {
                stringBuilder.append(tokens[i]);
                stringBuilder.append(" ");
            }
        }
        good.setPrice(Double.parseDouble(tokens[tokens.length - 1]) / quantity);
        good.setName(stringBuilder.deleteCharAt(stringBuilder.length() - 1).toString());
        if(isTaxable(good.getName())){
            good.setTaxable(true);
        }

        return new Pair<>(good, quantity);
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