package com.katas.sales_taxes.repository;

import com.katas.sales_taxes.exception.UnableToReadRepositoryException;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class FileUntaxedGoodRepository implements UntaxedGoodRepository {

    private final String dictionaryPath;

    public FileUntaxedGoodRepository(String dictionaryPath) {
        this.dictionaryPath = dictionaryPath;
    }

    @Override
    public boolean find(String goodName) throws UnableToReadRepositoryException {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(new File(dictionaryPath)));
            String line;
            while ((line = reader.readLine()) != null) {
                if (goodName.equals(line)) {
                    return true;
                }
            }
            return false;
        } catch (Exception exception) {
            throw new UnableToReadRepositoryException(exception);
        }
    }
}
