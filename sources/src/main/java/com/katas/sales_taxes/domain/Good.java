package com.katas.sales_taxes.domain;

import java.util.Objects;

public class Good {

    private String name;
    private boolean taxable;
    private boolean imported;
    private double price;

    public Good() {
    }

    public Good(String name, boolean taxable, boolean imported, double price) {
        this.name = name;
        this.taxable = taxable;
        this.imported = imported;
        this.price = price;
    }

    public Good(Good other) {
        this.name = other.name;
        this.taxable = other.taxable;
        this.imported = other.imported;
        this.price = other.price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isTaxable() {
        return taxable;
    }

    public void setTaxable(boolean taxable) {
        this.taxable = taxable;
    }

    public boolean isImported() {
        return imported;
    }

    public void setImported(boolean imported) {
        this.imported = imported;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Good good = (Good) o;
        return taxable == good.taxable &&
                imported == good.imported &&
                Double.compare(good.price, price) == 0 &&
                Objects.equals(name, good.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, taxable, imported, price);
    }
}
