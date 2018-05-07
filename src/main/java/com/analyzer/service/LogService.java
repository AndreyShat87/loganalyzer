package com.analyzer.service;

public interface LogService {

    String parseLog(String log);

    String getLog(String date);

    String getLogsBetweenDate(String startDate, String endDate);

    String getLogsForDay(String dayDate);

}
