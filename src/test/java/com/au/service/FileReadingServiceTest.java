package com.au.service;

import com.au.model.HttpLog;
import com.au.parser.HttpParser;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class FileReadingServiceTest {
    FileReadingService fileReadingService;

    @BeforeAll
    void setup(){
        fileReadingService = new FileReadingService(new HttpParser());
    }

    @Test
    void should_throw_exception_when_file_not_found(){
        assertThrows(
                FileNotFoundException.class,
                () -> fileReadingService.readFile("non-existing-file.txt")
        );
    }

    @Test
    void should_return_log_list_from_file() throws IOException {
        String file = getClass().getResource("http1.log").getFile();
        List expectedResults = List.of(
                new HttpLog.HttpLogBuilder("177.71.128.21", "GET /intranet-analytics/ HTTP/1.1")
                        .response(200)
                        .time("10/Jul/2018:22:21:28 +0200")
                        .useName("-")
                        .bytesSent(3574L)
                        .build(),
                new HttpLog.HttpLogBuilder("168.41.191.40", "GET http://example.net/faq/ HTTP/1.1")
                        .response(200)
                        .time("09/Jul/2018:10:11:30 +0200")
                        .useName("-")
                        .bytesSent(3574L)
                        .build()
        );

        assertEquals(expectedResults, fileReadingService.readFile(file));
    }
}

