package com.katas.sales_taxes.repository;

import com.katas.sales_taxes.exception.UnableToReadRepositoryException;

public interface UntaxedGoodRepository {

    boolean find(String goodName) throws UnableToReadRepositoryException;

}
