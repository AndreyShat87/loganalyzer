package com.analyzer.serializer;

import com.analyzer.entity.LogEntity;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class LogSerializer extends StdSerializer<LogEntity>{

        public LogSerializer() {
            this(null);
        }

        public LogSerializer(Class<LogEntity> t) {
            super(t);
        }

    @Override
    public void serialize(LogEntity logEntity, JsonGenerator jgen, SerializerProvider provider) throws IOException {

        LocalDateTime date = LocalDateTime.ofInstant(Instant.ofEpochMilli(logEntity.getDate()), ZoneId.systemDefault());
        DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE_TIME;
        jgen.writeStartObject();
        jgen.writeStringField("id", logEntity.getId());
        jgen.writeStringField("date", date.format(formatter));
        jgen.writeStringField("hostname", logEntity.getHostname());
        jgen.writeStringField("logId", logEntity.getLogId());
        jgen.writeEndObject();

    }
}
