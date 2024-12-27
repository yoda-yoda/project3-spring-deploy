package org.durcit.be.admin.service;

import org.durcit.be.admin.dto.AdminLogResponse;
import org.durcit.be.admin.dto.StatisticsResponse;
import org.durcit.be.post.dto.PostCardResponse;
import org.springframework.data.domain.Page;

import java.util.List;

public interface AdminService {

    public List<AdminLogResponse> getAllLogs();
    public void recoverPostAndPostsTag(Long postId);
    public void roleUpdateToAdmin(Long memberId);
    public void roleUpdateToManager(Long memberId);
    public void roleUpdateToMember(Long memberId);


    Page<AdminLogResponse> getPagedLogs(int page, int size);

    public List<AdminLogResponse> getRecentLogs(int limit);


    StatisticsResponse getStatistics();

    List<StatisticsResponse> getRecentStatistics();
}
