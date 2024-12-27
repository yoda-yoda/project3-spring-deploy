package org.durcit.be.facade.dto;

import lombok.Builder;
import lombok.Data;
import org.durcit.be.security.dto.MemberProfileResponse;

@Data
@Builder
public class UserInfoResponse {
    private MemberProfileResponse userInfo;
    private String bio;
    private Long postCount;
    private Long followerCount;
    private Long followingCount;
    private String createdAt;
}