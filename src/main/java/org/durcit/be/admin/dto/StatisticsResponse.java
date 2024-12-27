package org.durcit.be.admin.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StatisticsResponse {

    private Long totalUsers;
    private String userChangePercentage;
    private Long totalPosts;
    private String postChangePercentage;
    private Long totalLogs;
    private String logChangePercentage;

    private LocalDate timestamp;

}
