package com.soap.tobyofspringone;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.sameInstance;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.jupiter.api.Test;

public class JunitTest {
    static JunitTest testObject;

    @Test
    void test1(){
        assertThat(this, is(not(sameInstance(testObject))));
        testObject = this;
    }

    @Test
    void test2(){
        assertThat(this, is(not(sameInstance(testObject))));
        testObject = this;
    }

    @Test
    void test3(){
        assertThat(this, is(not(sameInstance(testObject))));
        testObject = this;
    }

}