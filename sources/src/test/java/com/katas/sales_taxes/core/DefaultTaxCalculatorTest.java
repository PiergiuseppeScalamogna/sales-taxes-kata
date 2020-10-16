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
        Good good = new Good();
        good.setName("bread");
        good.setImported(false);
        good.setTaxable(false);
        good.setPrice(2.5);
        assertEquals(0.0, taxCalculator.taxOf(good), 0.000001);
    }

    @Test
    public void basicTaxes() {
        Good good = new Good();
        good.setName("soap");
        good.setImported(false);
        good.setTaxable(true);
        good.setPrice(1.1);
        assertEquals(0.1, taxCalculator.taxOf(good), 0.000001);
    }

    @Test
    public void importTaxes() {
        Good good = new Good();
        good.setName("coffee");
        good.setImported(true);
        good.setTaxable(false);
        good.setPrice(3.2);
        assertEquals(0.15, taxCalculator.taxOf(good), 0.000001);
    }

    @Test
    public void basicAndImportTaxes() {
        Good good = new Good();
        good.setName("perfume");
        good.setImported(true);
        good.setTaxable(true);
        good.setPrice(11.75);
        assertEquals(1.75, taxCalculator.taxOf(good), 0.000001);
    }
}