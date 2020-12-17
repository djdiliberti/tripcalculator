package com.dansoft.tripcalculator.calculator;

import com.dansoft.tripcalculator.domain.Debt;
import com.dansoft.tripcalculator.domain.Person;

import java.math.BigDecimal;
import java.util.*;

public class ExpenseCalculator {

    public static List<Debt> calculateExpenses(List<Person> people) {

        BigDecimal totalDebts = new BigDecimal(0);

        // total up entire spent by group, get the average
        for (Person person : people) {
            totalDebts = totalDebts.add(person.getTotalSpent());
        }
        BigDecimal perPersonSpend = totalDebts.divide(new BigDecimal(people.size()));

        List<Person> netBalancesList = new ArrayList<>();

        for (Person person : people) {
            // get the new for each person, add to their object and put them in our sorted set
            person.setNetBalance(person.getTotalSpent().subtract(perPersonSpend));
            if (person.getNetBalance().compareTo(BigDecimal.ZERO) != 0) {
                // only add to the list of net balances if the person has a non zero balance
                netBalancesList.add(person);
            }
        }

        Collections.sort(netBalancesList, new ExpenseComparator());

        List<Debt> debts = new ArrayList<>();
        // now we go through the entire set comparing the highest and lowest remaining net balances and match them up
        while (netBalancesList.size() > 0) {
            Person debtor = netBalancesList.get(0);
            Person creditor = netBalancesList.get(netBalancesList.size() - 1);

            BigDecimal total = debtor.getNetBalance().add(creditor.getNetBalance());
            int comparedToZero = total.compareTo(BigDecimal.ZERO);

            Debt debt = new Debt();
            debt.setDebtorName(debtor.getName());
            debt.setCreditorName(creditor.getName());

            if(comparedToZero > 0) {
                // creditor is still owed money after this debtor pays them
                // create a debt for the size of debtor net balance
                debt.setAmountOwed(debtor.getNetBalance().abs());
                netBalancesList.remove(debtor);

                creditor.setNetBalance(creditor.getNetBalance().add(debtor.getNetBalance()));
            } else if ( comparedToZero < 0) {
                // debtor still owes money to a different creditor after this creditor is paid
                // create a debt for the size of the creditor net balance
                debt.setAmountOwed(creditor.getNetBalance());
                netBalancesList.remove(creditor);

                debtor.setNetBalance(debtor.getNetBalance().add(creditor.getNetBalance()));
            } else {
                // perfect match, remove both
                debt.setAmountOwed(creditor.getNetBalance());
                netBalancesList.remove(creditor);
                netBalancesList.remove(debtor);
            }
            // add the completed debt info to our list
            debts.add(debt);
        }

        return debts;
    }
}
