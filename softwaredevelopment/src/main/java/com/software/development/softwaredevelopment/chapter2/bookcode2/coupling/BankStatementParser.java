package com.software.development.softwaredevelopment.chapter2.bookcode2.coupling;

import com.software.development.softwaredevelopment.chapter2.bookcode2.dto.BankTransaction;
import java.util.List;

public interface BankStatementParser {
    BankTransaction parseFrom(String line);
    List<BankTransaction> parseLinesFrom(List<String> list);
}