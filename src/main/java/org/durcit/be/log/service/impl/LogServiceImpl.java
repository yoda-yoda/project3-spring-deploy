package org.durcit.be.log.service.impl;

import lombok.RequiredArgsConstructor;
import org.durcit.be.admin.dto.AdminLogResponse;
import org.durcit.be.log.domain.Log;
import org.durcit.be.log.domain.Statistics;
import org.durcit.be.log.repository.LogRepository;
import org.durcit.be.log.service.LogService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.swing.text.html.Option;
import java.nio.channels.FileChannel;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class LogServiceImpl implements LogService {

    private final LogRepository logRepository;

    public Page<AdminLogResponse> findAll(PageRequest pageRequest) {
        return logRepository.findAll(pageRequest)
                .map(AdminLogResponse::fromEntity);
    }

    @Override
    public List<Log> findRecentLogs(Pageable pageable) {
        return logRepository.findRecentLogs(pageable);
    }

    public Long getLogsCount() {
        return logRepository.count();
    }

}
