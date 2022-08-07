package com.au.main;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class LogAnalyserAppTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeAll
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }

    @AfterAll
    public void restoreStreams() {
        System.setOut(originalOut);
    }

    @Test
    void should_return_correct_results_for_input_file() throws FileNotFoundException {
        String file = getClass().getResource("testLog.log").getFile();
        LogAnalyserApp.main(new String[]{file});
        String expectedOutput= "most active ips=" +
                "{168.41.191.40=4, 50.112.00.11=3, 177.71.128.21=3, 72.44.32.10=3}\n" +
                "most visited urls={http://example.net=2}\n" +
                "unique ips=11\n" +
                "unique urls=1\n";
        assertEquals(expectedOutput, outContent.toString());
    }
}