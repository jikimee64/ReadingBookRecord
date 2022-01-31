package com.software.development.softwaredevelopment.chapter1.mycode;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;
import static org.junit.jupiter.api.Assertions.assertAll;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class TextReaderTest {

    private FileReader fileReader;
    private FileParser fileParser;

    @BeforeEach
    void init(){
        fileReader = new FileReader();
        fileParser = new CsvParser();
    }

    @Test
    @DisplayName("한줄씩 String으로 읽는다")
    void readFile(){
        //given
        String resourcesPath = "chapter1/";
        String fileName = "inout.csv";

        //when
        List<String> content = fileReader.readFile(resourcesPath, fileName);

        //then
        assertAll(
            () -> assertThat(content.get(0)).isEqualTo("30-01-2017,-100,Deliveroo"),
            () -> assertThat(content.get(1)).isEqualTo("30-01-2017,-50,Tesco"),
            () -> assertThat(content.get(2)).isEqualTo("01-02-2017,6000,Salary"),
            () -> assertThat(content.get(3)).isEqualTo("02-02-2017,2000,Royalties"),
            () -> assertThat(content.get(4)).isEqualTo("02-02-2017,-4000,Rent"),
            () -> assertThat(content.get(5)).isEqualTo("03-02-2017,3000,Tesco"),
            () -> assertThat(content.get(6)).isEqualTo("05-02-2017,-30,Cinema")
        );
    }

    @Test
    @DisplayName("String으로 읽은걸 Transaction 객체로 만든다")
    void generateTransactions(){
        //given
        String resourcesPath = "chapter1/";
        String fileName = "inout.csv";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        List<String> content = fileReader.readFile(resourcesPath, fileName);

        //when
        List<Transaction> transactions = fileParser.parseLines(content);

        assertThat(transactions)
            .extracting("localDate", "price", "item")
            .contains(
                tuple(LocalDate.parse("30-01-2017", formatter),-100,"Deliveroo"),
                tuple(LocalDate.parse("30-01-2017", formatter),-50,"Tesco"),
                tuple(LocalDate.parse("01-02-2017", formatter),6000,"Salary"),
                tuple(LocalDate.parse("02-02-2017", formatter),2000,"Royalties"),
                tuple(LocalDate.parse("02-02-2017", formatter),-4000,"Rent"),
                tuple(LocalDate.parse("03-02-2017", formatter),3000,"Tesco"),
                tuple(LocalDate.parse("05-02-2017", formatter),-30,"Cinema")
            );
    }


}