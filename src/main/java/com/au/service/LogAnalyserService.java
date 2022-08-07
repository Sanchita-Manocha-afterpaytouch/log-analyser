package com.au.service;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface LogAnalyserService {
    public Set<String> uniqueIps();
    public Set<String> uniqueURLs();
    public Map<String, Integer> mostVisitedURLs(int limit);
    public Map<String, Integer> mostActiveIps(int limit);
}
