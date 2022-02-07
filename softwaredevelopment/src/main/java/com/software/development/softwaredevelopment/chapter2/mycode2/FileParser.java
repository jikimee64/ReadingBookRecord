package com.software.development.softwaredevelopment.chapter2.mycode2;

import java.util.List;

public interface FileParser {
    List<Transaction> parseLines(List<String> content);
}