package com.dansoft.tripcalculator.output;

import com.dansoft.tripcalculator.domain.Debt;

import java.text.NumberFormat;
import java.util.List;

public class DebtOutput {

    public static void output(List<Debt> debts) {

        NumberFormat currencyFormat = NumberFormat.getCurrencyInstance();

        System.out.println("\n\n");

        for (Debt debt : debts) {
            String formattedDebt = currencyFormat.format(debt.getAmountOwed());

            System.out.println(debt.getDebtorName() + " owes " + debt.getCreditorName() + " " + formattedDebt);
        }
    }
}
