package com.software.development.softwaredevelopment.chapter3.bookcode3.after;

import com.software.development.softwaredevelopment.chapter3.bookcode3.BankTransactionV3;

@FunctionalInterface
public interface BankTransactionSummarizer {
    double summarize(double accumulator, BankTransactionV3 bankTransaction);
}