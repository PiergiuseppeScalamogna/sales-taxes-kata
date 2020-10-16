package com.katas.sales_taxes.acceptance;

import com.katas.sales_taxes.core.DefaultReceiptCalculator;
import com.katas.sales_taxes.core.DefaultTaxCalculator;
import com.katas.sales_taxes.core.ReceiptCalculator;
import com.katas.sales_taxes.core.TaxCalculator;
import com.katas.sales_taxes.domain.Cart;
import com.katas.sales_taxes.domain.Good;
import com.katas.sales_taxes.domain.Receipt;
import com.katas.sales_taxes.view.ConsoleReceiptPresenter;
import com.katas.sales_taxes.view.ReceiptPresenter;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AcceptanceTest {

    @Test
    public void acceptance1() {
        TaxCalculator taxCalculator = new DefaultTaxCalculator();
        ReceiptCalculator receiptCalculator = new DefaultReceiptCalculator(taxCalculator);
        Cart cart = new Cart();
        Good good = new Good();
        good.setName("book");
        good.setPrice(12.49);
        Good good1 = new Good();
        good1.setName("music CD");
        good1.setPrice(14.99);
        good1.setTaxable(true);
        Good good2 = new Good();
        good2.setName("chocolate bar");
        good2.setPrice(0.85);
        cart.add(good, 1);
        cart.add(good1, 1);
        cart.add(good2, 1);
        Receipt receipt = receiptCalculator.calculate(cart);
        ReceiptPresenter receiptPresenter = new ConsoleReceiptPresenter();
        java.io.ByteArrayOutputStream out = new java.io.ByteArrayOutputStream();
        System.setOut(new java.io.PrintStream(out));
        receiptPresenter.present(receipt);

        assertEquals(out.toString(), "1 book: 12.49\n1 music CD: 16.49\n1 chocolate bar: 0.85\nSales Taxes: 1.50\nTotal: 29.83\n");
    }

    @Test
    public void acceptance2() {
        TaxCalculator taxCalculator = new DefaultTaxCalculator();
        ReceiptCalculator receiptCalculator = new DefaultReceiptCalculator(taxCalculator);
        Cart cart = new Cart();
        Good good = new Good();
        good.setName("box of chocolates");
        good.setPrice(10.00);
        good.setImported(true);
        Good good1 = new Good();
        good1.setName("bottle of perfume");
        good1.setPrice(47.5);
        good1.setTaxable(true);
        good1.setImported(true);
        cart.add(good, 1);
        cart.add(good1, 1);
        Receipt receipt = receiptCalculator.calculate(cart);
        ReceiptPresenter receiptPresenter = new ConsoleReceiptPresenter();
        java.io.ByteArrayOutputStream out = new java.io.ByteArrayOutputStream();
        System.setOut(new java.io.PrintStream(out));
        receiptPresenter.present(receipt);

        assertEquals(out.toString(), "1 imported box of chocolates: 10.50\n1 imported bottle of perfume: 54.65\nSales Taxes: 7.65\nTotal: 65.15\n");
    }

    @Test
    public void acceptance3() {
        TaxCalculator taxCalculator = new DefaultTaxCalculator();
        ReceiptCalculator receiptCalculator = new DefaultReceiptCalculator(taxCalculator);
        Cart cart = new Cart();
        Good good = new Good();
        good.setName("bottle of perfume");
        good.setPrice(27.99);
        good.setImported(true);
        good.setTaxable(true);
        Good good1 = new Good();
        good1.setName("bottle of perfume");
        good1.setPrice(18.99);
        good1.setTaxable(true);
        good.setImported(true);
        Good good2 = new Good();
        good2.setName("packet of headache pills");
        good2.setPrice(9.75);
        Good good3 = new Good();
        good3.setName("box of chocolates");
        good3.setPrice(11.25);
        good3.setImported(true);
        cart.add(good, 1);
        cart.add(good1, 1);
        cart.add(good2, 1);
        cart.add(good3, 1);
        Receipt receipt = receiptCalculator.calculate(cart);
        ReceiptPresenter receiptPresenter = new ConsoleReceiptPresenter();
        java.io.ByteArrayOutputStream out = new java.io.ByteArrayOutputStream();
        System.setOut(new java.io.PrintStream(out));
        receiptPresenter.present(receipt);

        assertEquals(out.toString(), "1 imported bottle of perfume: 32.19\n1 bottle of perfume: 20.89\n1 packet of headache pills: 9.75\n1 imported box of chocolates: 11.85\nSales Taxes: 6.70\nTotal: 74.68\n");
    }
}
