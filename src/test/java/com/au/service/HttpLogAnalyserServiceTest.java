package com.au.service;

import com.au.model.HttpLog;
import com.au.model.ParsedLog;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;


@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class HttpLogAnalyserServiceTest {
    private HttpLogAnalyserService httpLogAnalyserService;
    private List<ParsedLog> listLogs;

    @BeforeAll
    void setup() {
        listLogs = List.of(
                getLog("10.111.345.55", "http://google.com/xyz"),
                getLog("10.66.345.55", "https://google.com/xyz"),
                getLog("12.111.345.55", "http://facebook.com/asd"),
                getLog("10.111.345.55", "http://google.com/xyz"),
                getLog("10.66.345.55", "http://google.com/lmn"),
                getLog("10.111.345.55", "http://yahoo.com/lmn"),
                getLog("10.66.345.55", "http://yahoo.com/lmo"),
                getLog("10.111.345.55", "http://xyz.com/ghjk"),
                getLog("121.111.345.55", "http://yahoo.com/gjk"),
                getLog("109.119.345.55", "http://lmn.com/ghjk"),
                getLog("11.111.345.55", "http://facebook.com/ghjk"),
                getLog("111.111.345.55", "http://google.com/abc"),
                getLog("111.111.345.55", "http://google.com/abc")
        );
        httpLogAnalyserService = new HttpLogAnalyserService(listLogs);
    }

    @Test
    void should_return_unique_ips() {
        Set<String> expectedIps = listLogs.stream().map(ParsedLog::getIpAddress).collect(Collectors.toSet());

        Set<String> actualIps = httpLogAnalyserService.uniqueIps();

        assertEquals(expectedIps.size(), actualIps.size());
        assertEquals(expectedIps, actualIps);

    }

    @Test
    void should_return_unique_urls() {
        Set<String> expectedUrls = Set.of(
                "http://facebook.com",
                "http://yahoo.com",
                "https://google.com",
                "http://google.com",
                "http://lmn.com",
                "http://xyz.com"
        );

        Set<String> actualUrls = httpLogAnalyserService.uniqueURLs();

        assertEquals(expectedUrls.size(), actualUrls.size());
        assertEquals(expectedUrls, actualUrls);
    }

    @Test
    void should_return_top_urls() {
        Map<String, Integer> expectedResults = new LinkedHashMap<>();
        expectedResults.put( "http://google.com", 5);
        expectedResults.put("http://yahoo.com", 3);
        expectedResults.put("http://facebook.com", 2);

        Map<String, Integer> actualResults = httpLogAnalyserService.mostVisitedURLs(3);

        assertEquals(expectedResults, actualResults);
    }

    @Test
    void should_return_top_ips() {
        Map<String, Integer> expectedResults = new LinkedHashMap<>();
        expectedResults.put("10.111.345.55", 4);
        expectedResults.put("10.66.345.55", 3);
        expectedResults.put("111.111.345.55", 2);

        Map<String, Integer> actualResults = httpLogAnalyserService.mostActiveIps(3);

        assertEquals(expectedResults, actualResults);
    }

    private ParsedLog getLog(String ip, String url) {
        String request = "GET " + url + " HTTP/1.1";
        return new HttpLog.HttpLogBuilder(ip, request)
                .response(200)
                .time("10/Jul/2018:22:21:28 +0200")
                .useName("-")
                .bytesSent(3574L)
                .build();

    }


}