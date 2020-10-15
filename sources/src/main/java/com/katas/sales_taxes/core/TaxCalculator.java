package com.katas.sales_taxes.core;

import com.katas.sales_taxes.domain.Good;

public interface TaxCalculator {

    double taxOf(Good good);

}
