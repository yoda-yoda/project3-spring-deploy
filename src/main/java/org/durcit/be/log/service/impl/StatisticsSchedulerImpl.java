package org.durcit.be.log.service.impl;

import lombok.RequiredArgsConstructor;
import org.durcit.be.log.domain.Statistics;
import org.durcit.be.log.repository.StatisticsRepository;
import org.durcit.be.log.service.LogService;
import org.durcit.be.log.service.StatisticsScheduler;
import org.durcit.be.post.service.PostService;
import org.durcit.be.security.service.MemberService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
@Transactional
public class StatisticsSchedulerImpl implements StatisticsScheduler {

    private final MemberService memberService;
    private final PostService postService;
    private final LogService logService;
    private final StatisticsRepository statisticsRepository;


    @Scheduled(cron = "0 0 0 * * ?") // 매일 자정에 실행
    public void saveDailyStatistics() {
        Statistics statistics = new Statistics();
        statistics.setTotalUsers(memberService.getMemberCounts());
        statistics.setTotalPosts(postService.getPostsCount());
        statistics.setTotalLogs(logService.getLogsCount());
        statistics.setCreatedAt(LocalDate.now());

        statisticsRepository.save(statistics);
    }

}
