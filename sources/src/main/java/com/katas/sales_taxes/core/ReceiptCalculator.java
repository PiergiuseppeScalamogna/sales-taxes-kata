package com.katas.sales_taxes.core;

import com.katas.sales_taxes.domain.Cart;
import com.katas.sales_taxes.domain.Receipt;

public interface ReceiptCalculator {

    Receipt calculate(Cart cart);
}
