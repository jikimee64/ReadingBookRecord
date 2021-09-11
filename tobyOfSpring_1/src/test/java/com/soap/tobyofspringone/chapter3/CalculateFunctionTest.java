package com.soap.tobyofspringone.chapter3;

import static org.assertj.core.api.Assertions.assertThat;

import com.soap.tobyofspringone.chapter3.sub5.templatecallbackfunction.Calculator;
import java.io.IOException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CalculateFunctionTest {
    Calculator calculator;
    String numFilepath;

    @BeforeEach
    void init(){
        this.calculator = new Calculator();
        this.numFilepath = getClass().getResource("/numbers.txt").getPath();
    }

    @Test
    void sumOfNumbersV3() throws IOException {
        assertThat(calculator.calcSumV3(this.numFilepath)).isEqualTo(10);
    }

    @Test
    void multiplyOfNumbersV3() throws IOException {
        assertThat(calculator.calcMultiplyV3(this.numFilepath)).isEqualTo(24);
    }

    @Test
    void concatenateStringsV3() throws IOException {
        assertThat(calculator.concatenateV3(this.numFilepath)).isEqualTo("1234");
    }

}