package com.software.development.softwaredevelopment.chapter3.bookcode3.after;

import com.software.development.softwaredevelopment.chapter3.bookcode3.BankTransactionV3;
import java.time.Month;
import java.util.ArrayList;
import java.util.DoubleSummaryStatistics;
import java.util.List;

public class BankStatementProcessorAV2 {

    private final List<BankTransactionV3> bankTransactions;

    public BankStatementProcessorAV2(final List<BankTransactionV3> bankTransactions) {
        this.bankTransactions = bankTransactions;
    }

    public SummaryStatistics summarizeTransactions() {

        final DoubleSummaryStatistics doubleSummaryStatistics = bankTransactions.stream()
            .mapToDouble(BankTransactionV3::getAmount)
            .summaryStatistics();

        return new SummaryStatistics(doubleSummaryStatistics.getSum(),
            doubleSummaryStatistics.getMax(),
            doubleSummaryStatistics.getMin(),
            doubleSummaryStatistics.getAverage());
    }

    public double summarizeTransactions(final BankTransactionSummarizer bankTransactionSummarizer) {
        double result = 0;
        for (final BankTransactionV3 bankTransaction : bankTransactions) {
            result = bankTransactionSummarizer.summarize(result, bankTransaction);
        }
        return result;
    }

    public double calculateTotalInMonth(final Month month) {
        return summarizeTransactions((acc, bankTransaction) ->
            bankTransaction.getDate().getMonth() == month ? acc + bankTransaction.getAmount() : acc);
    }

    public List<BankTransactionV3> findTransactionsGreaterThanEqual(final int amount) {
        return findTransactions(bankTransaction -> bankTransaction.getAmount() >= amount);
    }

    public List<BankTransactionV3> findTransactions(final BankTransactionFilter bankTransactionFilter) {
        final List<BankTransactionV3> result = new ArrayList<>();
        for (final BankTransactionV3 bankTransaction : bankTransactions) {
            if (bankTransactionFilter.test(bankTransaction)) {
                result.add(bankTransaction);
            }
        }
        return result;
    }

}
