package com.software.development.softwaredevelopment.chapter1.mycode;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Transaction {
    private LocalDate localDate;
    private Integer price;
    private String item;
}