package com.katas.sales_taxes.core;

import com.katas.sales_taxes.domain.Good;

public class DefaultTaxCalculator implements TaxCalculator {

    @Override
    public double taxOf(Good good) {
        double tax = 0.0;
        if(good.isTaxable()){
            tax += 0.1;
        }
        if(good.isImported()){
            tax += 0.05;
        }
        return good.getPrice() * tax;
    }

}
