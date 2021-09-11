package com.soap.tobyofspringone.chapter3.sub5.templatecallbackgeneric;

public interface LineCallback<T> {
    T doSomethingWithLine(String line, T value);
}