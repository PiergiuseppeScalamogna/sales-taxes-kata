package com.katas.sales_taxes.interpreter;

import com.katas.sales_taxes.domain.CartItem;

public interface CartItemInterpreter {

    CartItem interpret(String good);

}
