package com.au.model;

import java.util.Objects;

public class HttpLog implements ParsedLog{
    private final String ipAddress;
    private final String userName;
    private final String time;
    private final String request;
    private final Integer responseCode;
    private final Long bytesSent;

    private HttpLog(HttpLogBuilder builder) {
        this.ipAddress = builder.ipAddress;
        this.userName = builder.userName;
        this.time = builder.time;
        this.request = builder.request;
        this.responseCode = builder.responseCode;
        this.bytesSent = builder.bytesSent;
    }

    @Override
    public String getIpAddress() {
        return ipAddress;
    }

    @Override
    public String getUserName() {
        return userName;
    }

    @Override
    public String getTime() {
        return time;
    }

    @Override
    public String getRequest() {
        return request;
    }

    @Override
    public Integer getResponseCode() {
        return responseCode;
    }

    @Override
    public Long getBytesSent() {
        return bytesSent;
    }

    @Override
    public String toString() {
        return "HttpLog{" +
                "ipAddress='" + ipAddress + '\'' +
                ", userName='" + userName + '\'' +
                ", time='" + time + '\'' +
                ", request='" + request + '\'' +
                ", response='" + responseCode + '\'' +
                ", bytesSent=" + bytesSent +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HttpLog httpLog = (HttpLog) o;
        return ipAddress.equals(httpLog.ipAddress) && userName.equals(httpLog.userName) && time.equals(httpLog.time) && request.equals(httpLog.request) && responseCode.equals(httpLog.responseCode) && bytesSent.equals(httpLog.bytesSent);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ipAddress, userName, time, request, responseCode, bytesSent);
    }

    public static class HttpLogBuilder {
        private final String ipAddress;
        private final String request;
        private String userName;
        private String time;
        private Integer responseCode;
        private Long bytesSent;

        public HttpLogBuilder(String ipAddress, String request) {
            this.ipAddress = ipAddress;
            this.request = request;
        }

        public HttpLogBuilder useName(String userName) {
            this.userName = userName;
            return this;
        }

        public HttpLogBuilder time(String time) {
            this.time = time;
            return this;
        }

        public HttpLogBuilder response(Integer responseCode) {
            this.responseCode = responseCode;
            return this;
        }

        public HttpLogBuilder bytesSent(Long bytesSent) {
            this.bytesSent = bytesSent;
            return this;
        }

        public HttpLog build() {
            HttpLog httpLog = new HttpLog(this);
            return httpLog;
        }
    }
}
