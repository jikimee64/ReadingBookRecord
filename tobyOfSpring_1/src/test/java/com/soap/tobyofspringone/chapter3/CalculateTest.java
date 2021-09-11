package com.soap.tobyofspringone.chapter3;

import static org.assertj.core.api.Assertions.assertThat;

import com.soap.tobyofspringone.chapter3.sub5.templatecallback.Calculator;
import java.io.IOException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CalculateTest {
    Calculator calculator;
    String numFilepath;

    @BeforeEach
    void init(){
        this.calculator = new Calculator();
        this.numFilepath = getClass().getResource("/numbers.txt").getPath();
    }

    @Test
    void sumOfNumbers() throws IOException {
        assertThat(calculator.calcSum(this.numFilepath)).isEqualTo(10);
    }

    @Test
    void multiplyOfNumbers() throws IOException {
        assertThat(calculator.calcMultiply(this.numFilepath)).isEqualTo(24);
    }

    @Test
    void sumOfNumbersV2() throws IOException {
        assertThat(calculator.calcSumV2(this.numFilepath)).isEqualTo(10);
    }

    @Test
    void multiplyOfNumbersV2() throws IOException {
        assertThat(calculator.calcMultiplyV2(this.numFilepath)).isEqualTo(24);
    }

}