package com.katas.sales_taxes.view;

import com.katas.sales_taxes.core.DefaultReceiptCalculator;
import com.katas.sales_taxes.core.DefaultTaxCalculator;
import com.katas.sales_taxes.core.ReceiptCalculator;
import com.katas.sales_taxes.core.TaxCalculator;
import com.katas.sales_taxes.domain.Cart;
import com.katas.sales_taxes.domain.Good;
import com.katas.sales_taxes.domain.Receipt;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ConsoleReceiptPresenterTest {

    @Test
    void present() {
        TaxCalculator taxCalculator = new DefaultTaxCalculator();
        ReceiptCalculator receiptCalculator = new DefaultReceiptCalculator(taxCalculator);
        Cart cart = new Cart();
        Good good = new Good();
        good.setName("soap");
        good.setPrice(1.95);
        good.setTaxable(true);
        Good good1 = new Good();
        good1.setName("coffee");
        good1.setPrice(4.38);
        good1.setTaxable(false);
        good1.setImported(true);
        cart.add(good, 1);
        cart.add(good1, 4);
        Receipt receipt = receiptCalculator.calculate(cart);
        ReceiptPresenter receiptPresenter = new ConsoleReceiptPresenter();
        java.io.ByteArrayOutputStream out = new java.io.ByteArrayOutputStream();
        System.setOut(new java.io.PrintStream(out));
        receiptPresenter.present(receipt);

        assertEquals(out.toString(), "4 imported coffee: 18.40\n1 soap: 2.15\nSales Taxes: 1.07\nTotal: 20.54\n");
    }

}