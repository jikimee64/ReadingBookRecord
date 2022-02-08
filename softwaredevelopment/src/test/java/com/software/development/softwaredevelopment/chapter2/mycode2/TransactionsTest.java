package com.software.development.softwaredevelopment.chapter2.mycode2;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

import com.software.development.softwaredevelopment.chapter2.mycode2.Transactions.Settlement;
import java.time.LocalDate;
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
        FileReader fileReader = new FileReader();
        FileParser fileParser = new CsvParser();
        String fileName = "inout.csv";
        String resourcesPath = "chapter2/";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        List<String> content = fileReader.readFile(resourcesPath, fileName);
        List<Transaction> transactionList = fileParser.parseLines(content);
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

    @Test
    @DisplayName("특정 날짜 범위에서 최대 거래 내역")
    void maxTransacitonBetweenDate() {
        //given
        LocalDate startDate = LocalDate.of(2017,1,30);
        LocalDate endDate = LocalDate.of(2017,1,30);

        //when
        Transaction transaction = transactions.maxTransacitonBetweenDate(startDate, endDate);

        //then
        assertThat(transaction.getLocalDate()).isEqualTo(LocalDate.of(2017,1,30));
        assertThat(transaction.getPrice()).isEqualTo(-50);
        assertThat(transaction.getItem()).isEqualTo("Tesco");
    }

    @Test
    @DisplayName("특정 날짜 범위에서 최소 거래 내역")
    void minTransacitonBetweenDate() {
        //given
        LocalDate startDate = LocalDate.of(2017,2,1);
        LocalDate endDate = LocalDate.of(2017,2,5);

        //when
        Transaction transaction = transactions.minTransacitonBetweenDate(startDate, endDate);

        //then
        assertThat(transaction.getLocalDate()).isEqualTo(LocalDate.of(2017,2,2));
        assertThat(transaction.getPrice()).isEqualTo(-4000);
        assertThat(transaction.getItem()).isEqualTo("Rent");
    }

    @Test
    @DisplayName("특정 날짜 범위에 거래내역이 존재하지 않을 경우")
    void notExistTransactionBetweenDate() {
        //given
        LocalDate startDate = LocalDate.of(2017,3,1);
        LocalDate endDate = LocalDate.of(2017,4,5);

        //when && then
        assertThatThrownBy(() -> transactions.minTransacitonBetweenDate(startDate, endDate))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("해당 날짜범위에 해당하는 거래내역이 없습니다.")
            .hasStackTraceContaining("IllegalArgumentException");
    }

}