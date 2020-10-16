package com.katas.sales_taxes.repository;

import com.katas.sales_taxes.exception.UnableToReadRepositoryException;

public interface UntaxedItemRepository {

    boolean find(String name) throws UnableToReadRepositoryException;

}
