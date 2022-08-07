package com.au.parser;

import com.au.model.ParsedLog;

public interface Parser {
    public ParsedLog parseLine(String log);
}
