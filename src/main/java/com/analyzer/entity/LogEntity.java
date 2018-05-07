package com.analyzer.entity;

import com.analyzer.serializer.LogSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.springframework.data.annotation.Id;

@JsonSerialize(using = LogSerializer.class)
public class LogEntity {

    @Id
    private String id;

    private Long date;

    private String hostname;

    private String logId;

    public LogEntity(Long date, String hostname, String logId) {
        this.date = date;
        this.hostname = hostname;
        this.logId = logId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Long getDate() {
        return date;
    }

    public void setDate(Long date) {
        this.date = date;
    }

    public String getHostname() {
        return hostname;
    }

    public void setHostname(String hostname) {
        this.hostname = hostname;
    }

    public String getLogId() {
        return logId;
    }

    public void setLogId(String logId) {
        this.logId = logId;
    }
}
