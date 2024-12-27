package org.durcit.be.log.repository;

import org.durcit.be.log.domain.Statistics;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface StatisticsRepository extends JpaRepository<Statistics, Long> {
    Optional<Statistics> findTopByCreatedAtOrderByCreatedAtDesc(LocalDate date);

    @Query("SELECT s FROM Statistics s ORDER BY s.createdAt DESC")
    List<Statistics> findTop4ByOrderByCreatedAtDesc();
}
