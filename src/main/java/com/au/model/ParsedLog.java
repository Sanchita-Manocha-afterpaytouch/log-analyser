package com.au.model;

public interface ParsedLog {
    public String getIpAddress();
    public String getUserName();
    public String getTime();
    public String getRequest();
    public Integer getResponseCode();
    public Long getBytesSent();
}
