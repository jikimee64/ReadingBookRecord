package com.software.development.softwaredevelopment.chapter1.mycode;

import java.time.Month;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    public String expendedHighItem() {
        Map<String, List<Transaction>> groupTransactions = groupingByItem();
        return calculateHighItem(groupTransactions);
    }

    private Map<String, List<Transaction>> groupingByItem() {
        return this.transactions.stream()
            .collect(Collectors.groupingBy(Transaction::getItem));
    }

    private String calculateHighItem(Map<String, List<Transaction>> groupTransactions) {
        int consumeMax = Integer.MAX_VALUE;
        String itemMax = "";

        for (String itemName : groupTransactions.keySet()) {
            int itemSum = getSumItem(groupTransactions.get(itemName));
            if (itemSum < consumeMax) {
                consumeMax = itemSum;
                itemMax = itemName;
            }
        }
        return itemMax;
    }

    private int getSumItem(List<Transaction> transactions) {
        return transactions.stream()
            .mapToInt(Transaction::getPrice)
            .sum();
    }

    //지출이 가장 높은 상위 10건
    public List<Transaction> expendedTenItem() {
        return transactions.stream()
            .sorted(Comparator.comparing(Transaction::getPrice))
            .limit(10)
            .collect(Collectors.toList());
    }

    //특정달엔 몇 건의 입출금 내역이 발생했는가
    public int getByMonth(Month month) {
        return (int) transactions.stream()
            .filter(value -> value.getLocalDate().getMonth() == month).count();
    }

    //은행 입출금 내역의 총 수입과 총 지출은 각각 얼마인가? 결과가 양수인가 음수인가?
    public Settlement getSettlement() {
        Settlement settlement = new Settlement();

        int sumIncome = transactions.stream()
            .filter(value -> value.getPrice() > 0)
            .mapToInt(Transaction::getPrice)
            .sum();
        settlement.setSumIncome(sumIncome);

        int sumOutcome = transactions.stream()
            .filter(value -> value.getPrice() < 0)
            .mapToInt(value -> Math.abs(value.getPrice()))
            .sum();
        settlement.setSumOutcome(sumOutcome);

        return settlement;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public class Settlement {
        private int sumIncome;
        private int sumOutcome;
    }

}