package com.dansoft.tripcalculator.domain;

import com.google.common.base.Objects;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Person {

    private String name;
    private List<BigDecimal> tripExpenses = new ArrayList<>();
    private BigDecimal totalSpent = new BigDecimal(0);
    private BigDecimal netBalance;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<BigDecimal> getTripExpenses() {
        return tripExpenses;
    }

    public BigDecimal getTotalSpent() {
        return totalSpent;
    }

    public void setTotalSpent(BigDecimal totalSpent) {
        this.totalSpent = totalSpent;
    }

    public BigDecimal getNetBalance() {
        return netBalance;
    }

    public void setNetBalance(BigDecimal netBalance) {
        this.netBalance = netBalance;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return Objects.equal(name, person.name);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(name);
    }
}
