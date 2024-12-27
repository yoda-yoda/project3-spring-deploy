package org.durcit.be.log.service.impl;

import lombok.RequiredArgsConstructor;
import org.durcit.be.log.domain.Statistics;
import org.durcit.be.log.repository.StatisticsRepository;
import org.durcit.be.log.service.StatisticsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class StatisticsServiceImpl implements StatisticsService {

    private final StatisticsRepository statisticsRepository;

    public Optional<Statistics> findTopByCreatedAtOrderByCreatedAtDesc(LocalDate date) {
        return statisticsRepository.findTopByCreatedAtOrderByCreatedAtDesc(date);
    }

    public List<Statistics> getRecentStatistics() {
        return statisticsRepository.findTop4ByOrderByCreatedAtDesc();
    }

}
