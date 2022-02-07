package com.software.development.softwaredevelopment.chapter3.bookcode3.after;

import com.software.development.softwaredevelopment.chapter3.bookcode3.BankTransactionV3;

/**
 * Predicate 사용 가능
 */
@FunctionalInterface
public interface BankTransactionFilter {
    boolean test(BankTransactionV3 bankTransaction);
}