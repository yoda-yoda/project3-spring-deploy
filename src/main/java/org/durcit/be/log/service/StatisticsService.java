package org.durcit.be.log.service;

import org.durcit.be.log.domain.Statistics;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface StatisticsService {
    public Optional<Statistics> findTopByCreatedAtOrderByCreatedAtDesc(LocalDate date);

    public List<Statistics> getRecentStatistics();
}
