package org.durcit.be.admin.service.Impl;

import lombok.RequiredArgsConstructor;
import org.durcit.be.admin.dto.AdminLogResponse;
import org.durcit.be.admin.dto.StatisticsResponse;
import org.durcit.be.admin.repository.AdminRepository;
import org.durcit.be.admin.service.AdminService;
import org.durcit.be.log.domain.Log;
import org.durcit.be.log.domain.Statistics;
import org.durcit.be.log.repository.LogRepository;
import org.durcit.be.log.service.LogService;
import org.durcit.be.log.service.StatisticsService;
import org.durcit.be.post.domain.Post;
import org.durcit.be.post.dto.PostCardResponse;
import org.durcit.be.post.service.PostService;
import org.durcit.be.postsTag.service.PostsTagService;
import org.durcit.be.security.domian.Member;
import org.durcit.be.security.repository.MemberRepository;
import org.durcit.be.security.service.MemberService;
import org.durcit.be.system.exception.adminLog.AdminLogNotFoundException;
import org.durcit.be.system.exception.auth.MemberNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.durcit.be.system.exception.ExceptionMessage.ADMIN_LOG_NOT_FOUND_ERROR;
import static org.durcit.be.system.exception.ExceptionMessage.MEMBER_NOT_FOUND_ERROR;


@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {


    private final AdminRepository adminRepository;
    private final PostService postService;
    private final PostsTagService postsTagService;
    private final MemberRepository memberRepository;
    private final LogService logService;
    private final MemberService memberService;
    private final StatisticsService statisticsService;

    @Override
    public List<StatisticsResponse> getRecentStatistics() {
        return statisticsService.getRecentStatistics()
                .stream()
                .map(statistics -> StatisticsResponse.builder()
                        .totalUsers(statistics.getTotalUsers())
                        .totalLogs(statistics.getTotalLogs())
                        .totalPosts(statistics.getTotalPosts())
                        .timestamp(statistics.getCreatedAt())
                        .build()
                )
                .collect(Collectors.toList());
    }

    public StatisticsResponse getStatistics() {
        Long currentUsers = memberService.getMemberCounts();
        Long currentPosts = postService.getPostsCount();
        Long currentLogs = logService.getLogsCount();

        Optional<Statistics> yesterdayStatsOpt = statisticsService.findTopByCreatedAtOrderByCreatedAtDesc(LocalDate.now().minusDays(1));

        String userChangePercentage = yesterdayStatsOpt.map(stats -> calculatePercentageChange(stats.getTotalUsers(), currentUsers))
                .orElse("정보 없음");
        String postChangePercentage = yesterdayStatsOpt.map(stats -> calculatePercentageChange(stats.getTotalPosts(), currentPosts))
                .orElse("정보 없음");
        String logChangePercentage = yesterdayStatsOpt.map(stats -> calculatePercentageChange(stats.getTotalLogs(), currentLogs))
                .orElse("정보 없음");

        return StatisticsResponse.builder()
                .totalUsers(currentUsers)
                .userChangePercentage(userChangePercentage)
                .totalPosts(currentPosts)
                .postChangePercentage(postChangePercentage)
                .totalLogs(currentLogs)
                .logChangePercentage(logChangePercentage)
                .build();
    }


    private String calculatePercentageChange(Long previous, Long current) {
        if (previous == 0) {
            return "100% 증가";
        }
        double change = ((double) (current - previous) / previous) * 100;
        return String.format("%.2f%% %s", Math.abs(change), change >= 0 ? "증가" : "감소");
    }

    public List<AdminLogResponse> getRecentLogs(int limit) {
        Pageable pageable = PageRequest.of(0, limit);
        List<Log> recentLogs = logService.findRecentLogs(pageable);
        return recentLogs.stream()
                .map(AdminLogResponse::fromEntity)
                .collect(Collectors.toList());
    }

    public Page<AdminLogResponse> getPagedLogs(int page, int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        return logService.findAll(pageRequest);
    }

    // 메서드 기능: Log(api_log) 테이블을 전부 조회하여 응답 Dto로 변환후 반환한다.
    // 예외: Log(api_log) 테이블이 비어있으면 예외를 던진다.
    // 반환: 각각의 엔티티를 응답 Dto로 변환후 List로 반환한다.
    public List<AdminLogResponse> getAllLogs() {

        List<Log> findAllLogs = adminRepository.findAllLogs();

        if (findAllLogs.isEmpty()) {
            throw new AdminLogNotFoundException(ADMIN_LOG_NOT_FOUND_ERROR);
        }

        List<AdminLogResponse> adminLogResponses = new ArrayList<>();

        for (Log log : findAllLogs) {
            adminLogResponses.add(AdminLogResponse.fromEntity(log));
        }

        return adminLogResponses;
    }






    @Transactional
    // 메서드 기능: PostId를 받아 해당 Post와 거기 담긴 Tag를 전부 delete false 설정한다.
    // 예외: 해당하는 Post가 없으면 예외를 던진다.
    // 반환: 작업이 끝난 Post를 PostCard 타입으로 변환후 반환한다.
    // 수정할것: 댓글 부분을 살리는 로직을 추가해야한다.
    public void recoverPostAndPostsTag(Long postId) {


        // 해당 Post를 delete false 처리한다.
        postService.recoverPost(postId);


        // 해당 Post의 Tag를 delete false 처리한다.
        // 태그가 아예 없으면 예외가 아닌 빈 List를 반환하는 메서드다.
        postsTagService.recoverPostsTag(postId);


        // PostCard 타입으로 변환하기위해, 해당 post를 반환받는다.
        // postService의 메서드를 이용했다.


    }




    @Transactional
    // 메서드 기능: 해당 멤버의 Role을 "ADMIN" 으로 바꿔준다.
    // 예외: 해당 멤버가 없다면 예외를 던진다.
    // 반환: X
    public void roleUpdateToAdmin(Long memberId) {

        Member findMember = memberRepository.findById(memberId)
                .orElseThrow(() -> new MemberNotFoundException(MEMBER_NOT_FOUND_ERROR));

        findMember.setRole("ADMIN");
        memberRepository.save(findMember);


    }


    @Transactional
    // 메서드 기능: 해당 멤버의 Role을 "MANAGER" 로 바꿔준다.
    // 예외: 해당 멤버가 없다면 예외를 던진다.
    // 반환: X
    public void roleUpdateToManager(Long memberId) {

        Member findMember = memberRepository.findById(memberId)
                .orElseThrow(() -> new MemberNotFoundException(MEMBER_NOT_FOUND_ERROR));

        findMember.setRole("MANAGER");
        memberRepository.save(findMember);


    }


    @Transactional
    // 메서드 기능: 해당 멤버의 Role을 "MEMBER"로 바꿔준다.
    // 예외: 해당 멤버가 없다면 예외를 던진다.
    // 반환: X
    public void roleUpdateToMember(Long memberId) {

        Member findMember = memberRepository.findById(memberId)
                .orElseThrow(() -> new MemberNotFoundException(MEMBER_NOT_FOUND_ERROR));

        findMember.setRole("MEMBER");
        memberRepository.save(findMember);

    }






}
