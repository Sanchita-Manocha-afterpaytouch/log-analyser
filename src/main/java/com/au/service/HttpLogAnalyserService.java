package com.au.service;

import com.au.model.ParsedLog;

import java.util.*;
import java.util.stream.Collectors;

public class HttpLogAnalyserService implements LogAnalyserService {
    private final Map<String, Integer> ipAddresses;
    private final Map<String, Integer> urls;

    public HttpLogAnalyserService(List<ParsedLog> logList) {
        ipAddresses = new HashMap<>();
        urls = new HashMap<>();

        logList.forEach(parsedLog -> {
            ipAddresses.compute(parsedLog.getIpAddress(), (k, v) -> (v == null) ? 1 : v + 1);
            String url = getUrl(parsedLog.getRequest());
            if (url != null)
                urls.compute(url, (k, v) -> (v == null) ? 1 : v + 1);
        });
    }

    @Override
    public Set<String> uniqueIps() {
        return ipAddresses.keySet();
    }

    @Override
    public Set<String> uniqueURLs() {
        return urls.keySet();
    }

    @Override
    public Map<String, Integer> mostVisitedURLs(int limit) {
        return urls.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .limit(limit)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
                        (e1, e2) -> e1, LinkedHashMap::new));
    }

    @Override
    public Map<String, Integer> mostActiveIps(int limit) {
        return ipAddresses.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .limit(limit)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
                        (e1, e2) -> e1, LinkedHashMap::new));
    }

    private String getUrl(String request) {
        String url = request.split(" ")[1];
        if (url.startsWith("http://") || url.startsWith("https://")) {
            return url.substring(0, url.indexOf("/", 8));
        }
        return null;
    }
}
