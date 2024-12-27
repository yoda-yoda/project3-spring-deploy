package org.durcit.be.log.repository;

import org.durcit.be.log.domain.Log;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface LogRepository extends JpaRepository<Log, Long> {
    @Query("SELECT l FROM Log l ORDER BY l.timestamp DESC")
    List<Log> findRecentLogs(Pageable pageable);
}
