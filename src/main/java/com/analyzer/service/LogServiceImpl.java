package com.analyzer.service;

import com.analyzer.entity.LogEntity;
import com.analyzer.repository.LogRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class LogServiceImpl implements LogService {

    private Pattern datePattern = Pattern.compile("\\d\\d\\d\\d-\\d\\d-\\d\\dT\\d\\d:\\d\\d:\\d\\d.\\d\\d\\dZ");
    private Pattern hostPattern = Pattern.compile("Z\\s\\S*");
    private Pattern logIdPattern = Pattern.compile("-\\s\\w*\\s");

    @Autowired
    private LogRepository logRepository;

    @Override
    public String parseLog(String log) {

        Matcher logIdMatcher = hostPattern.matcher(log);
        Matcher hostMatcher = logIdPattern.matcher(log);
        Matcher dateMatcher = datePattern.matcher(log);
        dateMatcher.find();
        hostMatcher.find();
        logIdMatcher.find();

        LogEntity logEntity = new LogEntity(
                convertDate(dateMatcher.group()),
                hostMatcher.group().substring(2),
                logIdMatcher.group().substring(2)
                );
        logRepository.save(logEntity);
        return logToJson(logEntity);
    }

    @Override
    public String getLog(String date) {
        LogEntity logEntity = logRepository.findByDate(convertDate(date));
        return logToJson(logEntity);
    }

    @Override
    public String getLogsBetweenDate(String startDate, String endDate) {
        JSONArray ja = new JSONArray();
        List<LogEntity> logEntities = logRepository.findByDateBetween(convertDate(startDate), convertDate(endDate));
        for(LogEntity logEntity:logEntities){
           ja.add(logToJson(logEntity));
        }
        JSONObject jo = new JSONObject();
        jo.put("logs",ja);
        return jo.toJSONString();
    }

    @Override
    public String getLogsForDay(String dayDate){
        return getLogsBetweenDate(
                new StringBuilder(dayDate).append("T00:00:00.000Z").toString(),
                new StringBuilder(dayDate).append("T23:59:59.999Z").toString());
    }


    private Long convertDate(String date){
        DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE_TIME;
        ZonedDateTime zonedDateTime = ZonedDateTime.parse( date, formatter);
        return Long.valueOf(zonedDateTime.toInstant().toEpochMilli());
    }

    private String logToJson(LogEntity logEntity){
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.writeValueAsString(logEntity);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

}
