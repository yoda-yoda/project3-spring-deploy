package org.durcit.be.log.service;

import org.durcit.be.admin.dto.AdminLogResponse;
import org.durcit.be.log.domain.Log;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.nio.channels.FileChannel;
import java.util.List;

public interface LogService {
    public Long getLogsCount();

    List<Log> findRecentLogs(Pageable pageable);

    public Page<AdminLogResponse> findAll(PageRequest pageRequest);
}
