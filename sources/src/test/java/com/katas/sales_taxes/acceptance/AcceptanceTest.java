package com.katas.sales_taxes.acceptance;

import com.katas.sales_taxes.core.DefaultReceiptCalculator;
import com.katas.sales_taxes.core.DefaultTaxCalculator;
import com.katas.sales_taxes.core.ReceiptCalculator;
import com.katas.sales_taxes.core.TaxCalculator;
import com.katas.sales_taxes.domain.Cart;
import com.katas.sales_taxes.domain.CartItem;
import com.katas.sales_taxes.domain.Good;
import com.katas.sales_taxes.domain.Receipt;
import com.katas.sales_taxes.interpreter.CartItemInterpreter;
import com.katas.sales_taxes.interpreter.DictionaryCartItemInterpreter;
import com.katas.sales_taxes.repository.FileUntaxedItemRepository;
import com.katas.sales_taxes.repository.UntaxedItemRepository;
import com.katas.sales_taxes.view.ConsoleReceiptPresenter;
import com.katas.sales_taxes.view.ReceiptPresenter;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.io.ByteArrayOutputStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class AcceptanceTest {

    private TaxCalculator taxCalculator;
    private ReceiptCalculator receiptCalculator;
    private ReceiptPresenter receiptPresenter;
    private ByteArrayOutputStream out;
    private UntaxedItemRepository repository;
    private CartItemInterpreter interpreter;

    @BeforeAll
    public void init() {
        taxCalculator = new DefaultTaxCalculator();
        receiptCalculator = new DefaultReceiptCalculator(taxCalculator);
        receiptPresenter = new ConsoleReceiptPresenter();
        repository = new FileUntaxedItemRepository("src/test/resources/untaxable.txt");
        interpreter = new DictionaryCartItemInterpreter(repository);
    }

    @BeforeEach
    public void resetOutputStream() {
        out = new ByteArrayOutputStream();
        System.setOut(new java.io.PrintStream(out));
    }

    @Test
    public void acceptance1() {
        Cart cart = new Cart();
        Good good = new Good("book", false, false, 12.49);
        Good good1 = new Good("music CD", true, false, 14.99);
        Good good2 = new Good("chocolate bar", false, false, 0.85);
        cart.add(good, 1);
        cart.add(good1, 1);
        cart.add(good2, 1);
        Receipt receipt = receiptCalculator.calculate(cart);
        receiptPresenter.present(receipt);

        assertEquals(out.toString(), "1 book: 12.49\n1 music CD: 16.49\n1 chocolate bar: 0.85\nSales Taxes: 1.50\nTotal: 29.83\n");
    }

    @Test
    public void acceptance2() {
        Cart cart = new Cart();
        Good good = new Good("box of chocolates", false, true, 10.00);
        Good good1 = new Good("bottle of perfume", true, true, 47.5);
        cart.add(good, 1);
        cart.add(good1, 1);
        Receipt receipt = receiptCalculator.calculate(cart);
        receiptPresenter.present(receipt);

        assertEquals(out.toString(), "1 imported box of chocolates: 10.50\n1 imported bottle of perfume: 54.65\nSales Taxes: 7.65\nTotal: 65.15\n");
    }

    @Test
    public void acceptance3() {
        Cart cart = new Cart();
        Good good = new Good("bottle of perfume", true, true, 27.99);
        Good good1 = new Good("bottle of perfume", true, false, 18.99);
        Good good2 = new Good("packet of headache pills", false, false, 9.75);
        Good good3 = new Good("box of chocolates", false, true, 11.25);
        cart.add(good, 1);
        cart.add(good1, 1);
        cart.add(good2, 1);
        cart.add(good3, 1);
        Receipt receipt = receiptCalculator.calculate(cart);
        receiptPresenter.present(receipt);

        assertEquals(out.toString(), "1 imported bottle of perfume: 32.19\n1 bottle of perfume: 20.89\n1 packet of headache pills: 9.75\n1 imported box of chocolates: 11.85\nSales Taxes: 6.70\nTotal: 74.68\n");
    }

    @Test
    public void acceptance1WithInterpreter() {
        Cart cart = new Cart();
        CartItem cartItem1 = interpreter.interpret("1 book at 12.49");
        CartItem cartItem2 = interpreter.interpret("1 music CD at 14.99");
        CartItem cartItem3 = interpreter.interpret("1 chocolate bar at 0.85");
        cart.add(cartItem1.getGood(), cartItem1.getQuantity());
        cart.add(cartItem2.getGood(), cartItem2.getQuantity());
        cart.add(cartItem3.getGood(), cartItem3.getQuantity());
        Receipt receipt = receiptCalculator.calculate(cart);
        receiptPresenter.present(receipt);

        assertEquals(out.toString(), "1 book: 12.49\n1 music CD: 16.49\n1 chocolate bar: 0.85\nSales Taxes: 1.50\nTotal: 29.83\n");
    }

    @Test
    public void acceptance2WithInterpreter() {
        Cart cart = new Cart();
        CartItem cartItem1 = interpreter.interpret("1 imported box of chocolates at 10.00");
        CartItem cartItem2 = interpreter.interpret("1 imported bottle of perfume at 47.50");
        cart.add(cartItem1.getGood(), cartItem1.getQuantity());
        cart.add(cartItem2.getGood(), cartItem2.getQuantity());
        Receipt receipt = receiptCalculator.calculate(cart);
        receiptPresenter.present(receipt);

        assertEquals(out.toString(), "1 imported box of chocolates: 10.50\n1 imported bottle of perfume: 54.65\nSales Taxes: 7.65\nTotal: 65.15\n");
    }

    @Test
    public void acceptance3WithInterpreter() {
        Cart cart = new Cart();
        CartItem cartItem1 = interpreter.interpret("1 imported bottle of perfume at 27.99");
        CartItem cartItem2 = interpreter.interpret("1 bottle of perfume at 18.99");
        CartItem cartItem3 = interpreter.interpret("1 packet of headache pills at 9.75");
        CartItem cartItem4 = interpreter.interpret("1 box of imported chocolates at 11.25");
        cart.add(cartItem1.getGood(), cartItem1.getQuantity());
        cart.add(cartItem2.getGood(), cartItem2.getQuantity());
        cart.add(cartItem3.getGood(), cartItem3.getQuantity());
        cart.add(cartItem4.getGood(), cartItem4.getQuantity());
        Receipt receipt = receiptCalculator.calculate(cart);
        receiptPresenter.present(receipt);

        assertEquals(out.toString(), "1 imported bottle of perfume: 32.19\n1 bottle of perfume: 20.89\n1 packet of headache pills: 9.75\n1 imported box of chocolates: 11.85\nSales Taxes: 6.70\nTotal: 74.68\n");
    }
}
