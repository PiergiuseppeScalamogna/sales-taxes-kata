package com.katas.sales_taxes.repository;

import com.katas.sales_taxes.exception.UnableToReadRepositoryException;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class FileUntaxedItemRepository implements UntaxedItemRepository {

    private String dictionaryPath;

    public FileUntaxedItemRepository(String dictionaryPath) {
        this.dictionaryPath = dictionaryPath;
    }

    @Override
    public boolean find(String name) throws UnableToReadRepositoryException {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(new File(dictionaryPath)));
            String line;
            while ((line = reader.readLine()) != null) {
                if (name.equals(line)) {
                    return true;
                }
            }
            return false;
        } catch (Exception exception) {
            throw new UnableToReadRepositoryException(exception);
        }
    }
}
