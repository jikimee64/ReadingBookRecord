package com.software.development.softwaredevelopment.chapter5;

@FunctionalInterface
public interface Rule {
    void perform(Facts facts);
}