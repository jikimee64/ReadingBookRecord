package com.software.development.softwaredevelopment.chapter1.mycode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Transactions {
    private final List<Transaction> transactions;

    public Transactions(
        List<Transaction> transactions) {
        this.transactions = new ArrayList<>(transactions);
    }

    public List<Transaction> getTransactions() {
        return Collections.unmodifiableList(transactions);
    }

    //돈을 가장 많이 소비하는 항목
    public String expendedHighItem(){
        Map<String, List<Transaction>> groupTransactions = groupingByItem();
        return test(groupTransactions);
    }

    private Map<String, List<Transaction>> groupingByItem(){
        return this.transactions.stream()
            .collect(Collectors.groupingBy(Transaction::getItem));
    }

    private String test(Map<String, List<Transaction>> groupTransactions) {
        int consumeMax = Integer.MAX_VALUE;
        String itemMax = "";

        for (String itemName : groupTransactions.keySet()) {
            int itemSum = getSumItem(groupTransactions.get(itemName));
            if(itemSum < consumeMax){
                consumeMax = itemSum;
                itemMax = itemName;
            }
        }
        return itemMax;
    }

    private int getSumItem(List<Transaction> transactions){
        return transactions.stream()
            .mapToInt(Transaction::getPrice)
            .sum();
    }

    //지출이 가장 높은 상위 10건

    //특정달엔 몇 건의 입출금 내역이 발생했는가

    //은행 입출금 내역의 총 수입과 총 지출은 각각 얼마인가? 결과가 양수인가 음수인가?


}
