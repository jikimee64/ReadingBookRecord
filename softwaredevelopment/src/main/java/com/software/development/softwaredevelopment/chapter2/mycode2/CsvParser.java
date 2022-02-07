package com.software.development.softwaredevelopment.chapter2.mycode2;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

public class CsvParser implements FileParser {

    private static final String SPLIT_STANDARD = ",";

    public List<Transaction> parseLines(List<String> content) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        return content.stream()
            .map(value -> value.split(SPLIT_STANDARD))
            .map(value2 -> new Transaction(
                LocalDate.parse(value2[0], formatter),
                Integer.valueOf(value2[1]),
                value2[2]
            ))
            .collect(Collectors.toList());
    }

}