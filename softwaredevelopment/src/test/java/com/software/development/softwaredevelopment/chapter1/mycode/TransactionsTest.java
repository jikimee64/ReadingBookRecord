package com.software.development.softwaredevelopment.chapter1.mycode;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import com.software.development.softwaredevelopment.chapter1.mycode.Transactions.Settlement;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class TransactionsTest {

    private Transactions transactions;

    @BeforeEach
    void init() {
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
    void expendedHighItem() {
        //when
        String expendedHighItem = transactions.expendedHighItem();

        //then
        assertThat(expendedHighItem).isEqualTo("Rent");
    }

    @Test
    @DisplayName("지출이 가장 높은 상위 10건")
    void expendedTenItem() {
        //when
        List<Transaction> content = transactions.expendedTenItem();

        //then
        assertAll(
            () -> assertThat(content.get(0).getPrice()).isEqualTo(-4000),
            () -> assertThat(content.get(1).getPrice()).isEqualTo(-100),
            () -> assertThat(content.get(2).getPrice()).isEqualTo(-50),
            () -> assertThat(content.get(3).getPrice()).isEqualTo(-30),
            () -> assertThat(content.get(4).getPrice()).isEqualTo(2000),
            () -> assertThat(content.get(5).getPrice()).isEqualTo(3000),
            () -> assertThat(content.get(6).getPrice()).isEqualTo(6000)
        );

    }

    @Test
    @DisplayName("특정달 발생한 입출금 내역 수")
    void getByMonth() {
        //when
        int byMonth = transactions.getByMonth(Month.FEBRUARY);

        //then
        assertThat(byMonth).isEqualTo(5);

    }

    @Test
    @DisplayName("은행 입출금 내역의 총 수입과 총 지출")
    void getSettlement() {
        //when
        Settlement settlement = transactions.getSettlement();

        //then
        assertThat(settlement.getSumIncome()).isEqualTo(11000);
        assertThat(settlement.getSumOutcome()).isEqualTo(4180);

    }

}