package com.analyzer.repository;

import com.analyzer.entity.LogEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface LogRepository extends MongoRepository<LogEntity, String>{

    LogEntity findByDate(Long date);

    List<LogEntity>findByDateBetween(Long startDate, Long endDate);

    List<LogEntity> findAll();

}
