package com.software.development.softwaredevelopment.chapter1.bookcode.coupling;

import com.software.development.softwaredevelopment.chapter1.bookcode.dto.BankTransaction;
import java.util.List;

public interface BankStatementParser {
    BankTransaction parseFrom(String line);
    List<BankTransaction> parseLinesFrom(List<String> list);
}