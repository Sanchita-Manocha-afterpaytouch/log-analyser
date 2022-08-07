package com.au.service;

import com.au.model.ParsedLog;
import com.au.parser.Parser;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileReadingService {
    private final Parser logParser;

    public FileReadingService(Parser logParser) {
        this.logParser = logParser;
    }

    public List<ParsedLog> readFile(String file) throws IOException {
        List<ParsedLog> logList = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                ParsedLog parsedLog = logParser.parseLine(line);
                logList.add(parsedLog);
            }
        }
        return logList;
    }
}
