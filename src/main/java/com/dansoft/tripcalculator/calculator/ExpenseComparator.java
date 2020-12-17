package com.dansoft.tripcalculator.calculator;

import com.dansoft.tripcalculator.domain.Person;

import java.util.Comparator;

public class ExpenseComparator implements Comparator<Person> {
    @Override
    public int compare(Person o1, Person o2) {
        return o1.getNetBalance().compareTo(o2.getNetBalance());
    }
}
