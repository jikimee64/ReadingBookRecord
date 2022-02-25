package com.software.development.softwaredevelopment.chapter5;

@Deprecated
public interface ConditionalAction {
    void perform(Facts facts);
    boolean evaluate(Facts facts);
}