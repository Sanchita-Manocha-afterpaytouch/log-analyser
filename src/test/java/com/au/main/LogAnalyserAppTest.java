package com.au.main;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

class LogAnalyserAppTest {
    @Test
    void should_throw_exception_when_no_arg() {
        assertThrows(
                IllegalArgumentException.class,
                () -> LogAnalyserApp.main(new String[]{})
        );
    }

    @Test
    void test() {
        System.out.println("hello");
    }

}