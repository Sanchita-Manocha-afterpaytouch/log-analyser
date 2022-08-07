package com.au.main;

import com.au.parser.HttpParser;
import com.au.service.FileReadingService;
import com.au.service.HttpLogAnalyserService;
import com.au.service.LogAnalyserService;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public class LogAnalyserApp {
    public static void main(String[] args) throws FileNotFoundException {
        if (args.length != 1)
            throw new IllegalArgumentException("one arg(log file name) required");

        FileReadingService fileReadingService = new FileReadingService(new HttpParser());

        String file = args[0];
        try
        {
            List parsedLogsList = fileReadingService.readFile(file);
            LogAnalyserService service = new HttpLogAnalyserService(parsedLogsList);
            System.out.println("most active ips="+service.mostActiveIps(4));
            System.out.println("most visited urls="+service.mostVisitedURLs(3));
            System.out.println("unique ips="+service.uniqueIps().size());
            System.out.println("unique urls="+service.uniqueURLs().size());
        }
        catch (IOException exception) {
            throw new FileNotFoundException("File Not Found" + exception.getMessage());
        }

    }

}