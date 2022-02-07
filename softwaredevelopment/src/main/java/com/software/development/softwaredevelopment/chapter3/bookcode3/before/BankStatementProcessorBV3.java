package com.software.development.softwaredevelopment.chapter3.bookcode3.before;

import com.software.development.softwaredevelopment.chapter3.bookcode3.BankTransactionV3;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

/**
 * 계산 연산 그룹화(응집도 상승)
 */
public class BankStatementProcessorBV3 {

    private final List<BankTransactionV3> bankTransactions;

    public BankStatementProcessorBV3(List<BankTransactionV3> bankTransactionList) {
        this.bankTransactions = bankTransactionList;
    }

    public double calculateTotalAmount() {
        double total = 0;
        for (final BankTransactionV3 bankTransaction : bankTransactions) {
            total += bankTransaction.getAmount();
        }
        return total;
    }

    public double calculateTotalInMonth(final Month month) {
        double total = 0;
        for (final BankTransactionV3 bankTransaction : bankTransactions) {
            if (bankTransaction.getDate().getMonth() == month) {
                total += bankTransaction.getAmount();
            }
        }
        return total;
    }

    public double calculateTotalForCategory(final String category) {
        double total = 0;
        for (final BankTransactionV3 bankTransaction : bankTransactions) {
            if (bankTransaction.getDescription().equals(category)) {
                total += bankTransaction.getAmount();
            }
        }
        return total;
    }
    
    public List<BankTransactionV3> findTransactionsGreaterThanEqual(final int amount){
        final List<BankTransactionV3> result = new ArrayList<>();
        for(final BankTransactionV3 bankTransactionV3: bankTransactions){
            if(bankTransactionV3.getAmount() >= amount){
                result.add(bankTransactionV3);
            }
        }
        return result;
    }

    public List<BankTransactionV3> findTransactionsInMonth(final Month month){
        final List<BankTransactionV3> result = new ArrayList<>();
        for(final BankTransactionV3 bankTransactionV3: bankTransactions){
            if(bankTransactionV3.getDate().getMonth() == month){
                result.add(bankTransactionV3);
            }
        }
        return result;
    }

    public List<BankTransactionV3> findTransactionsInMonthAndGreater(final Month month, final int amount){
        final List<BankTransactionV3> result = new ArrayList<>();
        for(final BankTransactionV3 bankTransactionV3: bankTransactions){
            if(bankTransactionV3.getDate().getMonth() == month &&
                    bankTransactionV3.getAmount() >= amount){
                result.add(bankTransactionV3);
            }
        }
        return result;
    }

}