package com.katas.sales_taxes.interpreter;

import com.katas.sales_taxes.domain.Good;
import javafx.util.Pair;

public interface CartItemInterpreter {

    Pair<Good, Integer> interpret(String good);

}
