package com.dansoft.tripcalculator.calculator;

import com.dansoft.tripcalculator.domain.Debt;
import com.dansoft.tripcalculator.domain.Person;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ExpenseCalculatorTests {

    List<Person> people = new ArrayList<>();

    ExpenseCalculator subject = new ExpenseCalculator();

    @BeforeAll
    public void setup() {
        Person p = new Person();
        p.setName("Dan");
        p.getTripExpenses().add(new BigDecimal("1"));
        p.setTotalSpent(new BigDecimal("1"));
        people.add(p);

        p = new Person();
        p.setName("John");
        p.getTripExpenses().add(new BigDecimal("5"));
        p.setTotalSpent(new BigDecimal("5"));
        people.add(p);

        p = new Person();
        p.setName("Joe");
        p.getTripExpenses().add(new BigDecimal("10"));
        p.setTotalSpent(new BigDecimal("10"));
        people.add(p);

        p = new Person();
        p.setName("Frank");
        p.getTripExpenses().add(new BigDecimal("15"));
        p.setTotalSpent(new BigDecimal("15"));
        people.add(p);

        p = new Person();
        p.setName("Bob");
        p.getTripExpenses().add(new BigDecimal("20"));
        p.setTotalSpent(new BigDecimal("20"));
        people.add(p);
    }

    @Test
    public void test() {
        List<Debt> debts = subject.calculateExpenses(people);

        // validate debts
        assertTrue(debts.size() == 4);
        for (Debt d : debts) {
            if (d.getCreditorName().equals("Bob") && d.getDebtorName().equals("Dan")) {
                assertTrue(d.getAmountOwed().compareTo(new BigDecimal("9.2")) == 0);
            } else if (d.getCreditorName().equals("Bob") && d.getDebtorName().equals("John")) {
                assertTrue(d.getAmountOwed().compareTo(new BigDecimal("0.6")) == 0);
            } else if (d.getCreditorName().equals("Frank") && d.getDebtorName().equals("John")) {
                assertTrue(d.getAmountOwed().compareTo(new BigDecimal("4.6")) == 0);
            } else if (d.getCreditorName().equals("Frank") && d.getDebtorName().equals("Joe")) {
                assertTrue(d.getAmountOwed().compareTo(new BigDecimal("0.2")) == 0);
            }
        }

    }
}
