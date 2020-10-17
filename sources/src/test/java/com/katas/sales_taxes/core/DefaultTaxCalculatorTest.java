package com.katas.sales_taxes.core;

import com.katas.sales_taxes.domain.Good;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class DefaultTaxCalculatorTest {

    private TaxCalculator taxCalculator;

    @BeforeAll
    public void init() {
        taxCalculator = new DefaultTaxCalculator();
    }

    @Test
    public void noTaxes() {
        Good good = new Good("bread", false, false, 2.5);

        assertEquals(0.0, taxCalculator.taxOf(good), 0.000001);
    }

    @Test
    public void basicTaxes() {
        Good good = new Good("soap", true, false, 1.1);

        assertEquals(0.15, taxCalculator.taxOf(good), 0.000001);
    }

    @Test
    public void importTaxes() {
        Good good = new Good("coffee", false, true, 3.2);

        assertEquals(0.2, taxCalculator.taxOf(good), 0.000001);
    }

    @Test
    public void basicAndImportTaxes() {
        Good good = new Good("perfume", true, true, 11.75);

        assertEquals(1.8, taxCalculator.taxOf(good), 0.000001);
    }
}