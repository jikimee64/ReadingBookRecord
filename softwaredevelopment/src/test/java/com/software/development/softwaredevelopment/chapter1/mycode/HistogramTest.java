package com.software.development.softwaredevelopment.chapter1.mycode;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class HistogramTest {

    private Histogram histogram;

    @BeforeEach
    void init() {
        //given
        FileReader fileReader = new FileReader();
        FileParser fileParser = new CsvParser();
        String resourcesPath = "chapter1/";
        String fileName = "inout.csv";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        List<String> content = fileReader.readFile(resourcesPath, fileName);
        List<Transaction> transactionList = fileParser.parseLines(content);
        histogram = new Histogram(transactionList);
    }

    @Test
    void groupingByItem(){
        StringBuilder sb = histogram.groupingByItem();
        System.out.println(sb.toString());
    }

    @Test
    void groupingByMonth(){
        Map<Month, List<Transaction>> collect = histogram.groupingByMonth();
        assertThat(collect.get(Month.JANUARY).size()).isEqualTo(2);
        assertThat(collect.get(Month.FEBRUARY).size()).isEqualTo(5);
    }

}