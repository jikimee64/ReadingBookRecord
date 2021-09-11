package com.soap.tobyofspringone.chapter2;

import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;

import java.util.HashSet;
import java.util.Set;
import org.junit.jupiter.api.Test;

public class JunitTestV2 {
    static Set<JunitTestV2> testObjects = new HashSet<>();

    @Test
    void test1(){
        assertThat(testObjects, not(hasItem(this)));
        testObjects.add(this);
    }

    @Test
    void test2(){
        assertThat(testObjects, not(hasItem(this)));
        testObjects.add(this);
    }

    @Test
    void test3(){
        assertThat(testObjects, not(hasItem(this)));
        testObjects.add(this);
    }


}
