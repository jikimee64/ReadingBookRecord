package com.software.development.softwaredevelopment.chapter2.mycode2;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public class Histogram {

    private final List<Transaction> transactions;

    public Histogram(List<Transaction> transactions) {
        this.transactions = new ArrayList<>(transactions);
    }

    public List<Transaction> getTransactions() {
        return Collections.unmodifiableList(new ArrayList<>(transactions));
    }


    public StringBuilder groupingByItem() {
        StringBuilder sb = new StringBuilder();

        Map<String, List<Transaction>> collect = transactions.stream()
            .collect(Collectors.groupingBy(Transaction::getItem));

        for (String itemName : collect.keySet()) {
            List<Transaction> transactionList = collect.get(itemName);
            sb.append(itemName + "의 거래내역\n");

            transactionList.forEach(value -> {
                sb.append(value.getPrice() + ", " + value.getLocalDate() + "\n");
            });
        }

        return sb;
    }

    public Map<Month, List<Transaction>> groupingByMonth() {
        Map<Month, List<Transaction>> collect = new HashMap<>();

        for (Transaction transaction : transactions) {
            LocalDate localDate = transaction.getLocalDate();
            Month month = localDate.getMonth();
            if (Objects.isNull(collect.get(month))) {
                List<Transaction> list = new ArrayList<>();
                list.add(transaction);
                collect.put(month, list);
            } else {
                collect.get(month).add(transaction);
            }
        }
        return collect;
    }

}