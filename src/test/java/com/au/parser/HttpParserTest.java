package com.au.parser;

import com.au.model.ParsedLog;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class HttpParserTest {
    private  Parser httpParser;

    @BeforeAll
    void setup(){
        httpParser = new HttpParser();
    }

    @Test
    void should_parse_http_log(){
        String line = "50.112.00.11 - admin [11/Jul/2018:17:33:01 +0200] \"" +
                "GET /asset.css HTTP/1.1\" 200 3574 \"-\" " +
                "\"Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/536.6 (KHTML, like Gecko) " +
                "Chrome/20.0.1092.0 Safari/536.6\"\n";

        ParsedLog parsedLog = httpParser.parseLine(line);

        assertEquals(parsedLog.getIpAddress(), "50.112.00.11");
        assertEquals(parsedLog.getUserName(), "admin");
        assertEquals(parsedLog.getTime(), "11/Jul/2018:17:33:01 +0200");
        assertEquals(parsedLog.getRequest(), "GET /asset.css HTTP/1.1");
        assertEquals(parsedLog.getResponseCode(), 200);
        assertEquals(parsedLog.getBytesSent(), 3574L);
    }

    @Test
    void should_return_null_for_invalid_log(){
        String line = "50.112.00.11 - admin [11/Jul/2018:17:33:01 +0200] \"" +
                "GET /asset.css HTTP/1.1\" xyz 3574 \"-\" " +
                "\"Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/536.6 (KHTML, like Gecko) " +
                "Chrome/20.0.1092.0 Safari/536.6\"\n";

        assertNull(httpParser.parseLine(line));
    }

}