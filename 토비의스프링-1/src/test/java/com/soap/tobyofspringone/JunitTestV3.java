package com.soap.tobyofspringone;

import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.either;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashSet;
import java.util.Set;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;


@ExtendWith({SpringExtension.class})
@ContextConfiguration
public class JunitTestV3 {
    @Autowired
    ApplicationContext context;

    static Set<JunitTestV3> testObjectsV2 = new HashSet<>();
    static ApplicationContext contextObject = null;

    @Test
    void test1(){
        assertThat(testObjectsV2, not(hasItem(this)));
        testObjectsV2.add(this);

        assertThat(contextObject == null || contextObject == this.context, is(true));
        contextObject = this.context;

    }


    @Test
    void test2(){
        assertThat(testObjectsV2, not(hasItem(this)));
        testObjectsV2.add(this);

        assertTrue(contextObject == null || contextObject == this.context);
        contextObject = this.context;
    }

    @Test
    void test3(){
        assertThat(testObjectsV2, not(hasItem(this)));
        testObjectsV2.add(this);

        assertThat(contextObject, either(is(nullValue())).or(is(this.contextObject)));
        contextObject = this.context;
    }


}
