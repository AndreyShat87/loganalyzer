package com.analyzer.controller;


import com.analyzer.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
public class LogController {

    @Autowired
    private LogService logService;

    @RequestMapping(value = "/syslog", method = RequestMethod.PUT, produces = "application/json")
    public String syslog(@RequestParam (value = "log") String log) {
        return logService.parseLog(log);
    }

    @RequestMapping(value = "/syslog", method = RequestMethod.GET, produces = "application/json")
    public String getLog(@RequestParam (value = "date") String date) {
        return logService.getLog(date);
    }

    @RequestMapping(value = "/syslog/days", method = RequestMethod.GET, produces = "application/json")
    public String getLogsBetweenDate(@RequestParam (value = "startDate") String startDate,
                                     @RequestParam (value = "endDate") String endDate) {
        return logService.getLogsBetweenDate(startDate,endDate);
    }

    @RequestMapping(value = "/syslog/day", method = RequestMethod.GET, produces = "application/json")
    public String getLogsBetweenDate(@RequestParam (value = "day") String day) {
        return logService.getLogsForDay(day);
    }

}
