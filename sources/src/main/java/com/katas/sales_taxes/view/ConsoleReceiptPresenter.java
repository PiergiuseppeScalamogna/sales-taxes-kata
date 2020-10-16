package com.katas.sales_taxes.view;

import com.katas.sales_taxes.domain.Receipt;

import java.util.Locale;

public class ConsoleReceiptPresenter implements ReceiptPresenter {

    @Override
    public void present(Receipt receipt) {
        receipt.getPurchases().forEach(purchase -> {
            System.out.printf(Locale.US, "%d %s%s: %.2f\n",
                    purchase.getQuantity(),
                    purchase.getGood().isImported() ? "imported " : "",
                    purchase.getGood().getName(),
                    (purchase.getGood().getPrice() * purchase.getQuantity()) + purchase.getTax());
        });
        System.out.printf(Locale.US, "Sales Taxes: %.2f\n", receipt.getTaxes());
        System.out.printf(Locale.US, "Total: %.2f\n", receipt.getTotal());
    }
}
