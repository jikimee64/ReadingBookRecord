package com.software.development.softwaredevelopment.chapter1.mycode;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.core.io.ClassPathResource;

public class TextReader {

    private static final String SPLIT_STANDARD = ",";

    public List<String> readFile(String resouresPath, String fileName) {
        ClassPathResource resource = new ClassPathResource(resouresPath + fileName);
        List<String> content = Collections.emptyList();
        try {
            Path path = Paths.get(resource.getURI());
            content = Files.readAllLines(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return content;
    }

    public List<Transaction> generateTransactions(List<String> content) {
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