package com.katas.sales_taxes.repository;

import com.katas.sales_taxes.exception.UnableToReadRepositoryException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class FileUntaxedItemRepositoryTest {

    @Test
    public void found() throws UnableToReadRepositoryException {
        UntaxedItemRepository untaxedItemRepository = new FileUntaxedItemRepository("src/test/resources/untaxable.txt");
        assertTrue(untaxedItemRepository.find("book"));
    }

    @Test
    public void Notfound() throws UnableToReadRepositoryException {
        UntaxedItemRepository untaxedItemRepository = new FileUntaxedItemRepository("src/test/resources/untaxable.txt");
        assertFalse(untaxedItemRepository.find("music CD"));
    }


}