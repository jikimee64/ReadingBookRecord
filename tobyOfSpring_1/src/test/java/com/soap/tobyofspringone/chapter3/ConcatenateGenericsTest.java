package com.soap.tobyofspringone.chapter3;

import static org.assertj.core.api.Assertions.assertThat;

import com.soap.tobyofspringone.chapter3.sub5.templatecallbackgeneric.Calculator;
import java.io.IOException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ConcatenateGenericsTest {
    Calculator calculator;
    String numFilepath;

    @BeforeEach
    void init(){
        this.calculator = new Calculator();
        this.numFilepath = getClass().getResource("/numbers.txt").getPath();
    }

    @Test
    void concatenateStrings() throws IOException {
        assertThat(calculator.concatenate(this.numFilepath)).isEqualTo("1234");
    }

}