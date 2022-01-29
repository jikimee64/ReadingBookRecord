package com.software.development.softwaredevelopment.chapter1.mycode;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.format.DateTimeFormatter;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class TransactionsTest {

    private Transactions transactions;

    @BeforeEach
    void init(){
        //given
        TextReader textReader = new TextReader();
        String resourcesPath = "chapter1/";
        String fileName = "inout.txt";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        List<String> content = textReader.readFile(resourcesPath, fileName);
        List<Transaction> transactionList = textReader.generateTransactions(content);
        transactions = new Transactions(transactionList);
    }

    @Test
    @DisplayName("돈을 가장 많이 소비하는 항목")
    void expendedHighItem(){
        //when
        String expendedHighItem = transactions.expendedHighItem();

        //then
        assertThat(expendedHighItem).isEqualTo("Rent");
    }

}