package com.au.parser;

import com.au.model.HttpLog;
import com.au.model.ParsedLog;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HttpParser implements Parser{
    private final Pattern pattern;

    public HttpParser() {
        String regex = "^([\\d.]+) (\\S+) (\\S+) \\[([\\w:/]+\\s[+-]\\d{4})\\] \"(.+?)\" (\\d{3}) (\\d+) \"([^\"]+)\" \"(.+?)\"";
        pattern = Pattern.compile(regex);
    }

    @Override
    public ParsedLog parseLine(String log) {
        Matcher matcher = pattern.matcher(log);
        HttpLog parsedLog = null;
        if (matcher.find()) {
            String ipAddress = matcher.group(1);
            String userName = matcher.group(3);
            String time = matcher.group(4);
            String request = matcher.group(5);
            Integer response = Integer.valueOf(matcher.group(6));
            Long bytesSent = Long.valueOf(matcher.group(7));
            parsedLog = new HttpLog.HttpLogBuilder(ipAddress, request)
                    .useName(userName)
                    .time(time)
                    .response(response)
                    .bytesSent(bytesSent)
                    .build();
        }
        return parsedLog;
    }
}
