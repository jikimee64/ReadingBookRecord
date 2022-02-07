package com.software.development.softwaredevelopment.chapter3.bookcode3.after;

import com.software.development.softwaredevelopment.chapter3.bookcode3.BankTransactionV3;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

/**
 * 계산 연산 그룹화(응집도 상승)
 */
public class BankStatementProcessorA {
    private final List<BankTransactionV3> bankTransactions;

    public BankStatementProcessorA(List<BankTransactionV3> bankTransactionList) {
        this.bankTransactions = bankTransactionList;
    }

    public List<BankTransactionV3> findTransactions(final BankTransactionFilter bankTransactionFilter) {
        final List<BankTransactionV3> result = new ArrayList<>();
        for (final BankTransactionV3 bankTransactionV3 : bankTransactions) {
            if (bankTransactionFilter.test(bankTransactionV3)) {
                result.add(bankTransactionV3);
            }
        }
        return result;
    }

}
