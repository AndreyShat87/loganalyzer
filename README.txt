I used string "<34>1 2003-10-11T22:14:15.003Z mymachine.example.com su - ID47 - BOM'su root' failed for lonvick on /dev/pts/8" as example of entry log

API:

    Add log - PUT /syslog?log=<34>1 2003-10-11T22:14:15.003Z mymachine.example.com su - ID47 - BOM'su root' failed for lonvick on /dev/pts/8

    Get logs for certain day - GET /syslog/day?day=2004-10-11

    Get logs between dates - GET /syslog/days?startDate=2003-10-11T22:14:15.003Z&endDate=2005-10-11T22:14:15.003Z

    Get log for certain date and time - GET /syslog?date=2003-10-11T22:14:15.003Z