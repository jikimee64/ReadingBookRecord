package com.software.development.softwaredevelopment.chapter1.mycode;

import java.util.List;

public interface FileParser {
    List<Transaction> parseLines(List<String> content);
}