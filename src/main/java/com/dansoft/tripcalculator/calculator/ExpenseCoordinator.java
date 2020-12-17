package com.dansoft.tripcalculator.calculator;

import com.dansoft.tripcalculator.domain.Debt;
import com.dansoft.tripcalculator.domain.Person;
import com.dansoft.tripcalculator.input.FileReader;
import com.dansoft.tripcalculator.output.DebtOutput;
import com.google.common.base.Strings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileNotFoundException;
import java.util.List;

public class ExpenseCoordinator {

    Logger logger = LoggerFactory.getLogger(ExpenseCoordinator.class);

    private String inputFile;

    public ExpenseCoordinator(String inputFile) {
        this.inputFile = inputFile;
    }

    public void run() throws FileNotFoundException {
        if (Strings.isNullOrEmpty(inputFile)) {
            logger.error("Missing target file to calculate expenses from!");
            throw new RuntimeException("Missing target file to calculate expenses from!");
        }
        // initialize file reader
        FileReader fileReader = new FileReader(inputFile);
        List<Person> fileResults = fileReader.readFile();

        List<Debt> calculatorResults = ExpenseCalculator.calculateExpenses(fileResults);

        DebtOutput.output(calculatorResults);
    }
}
