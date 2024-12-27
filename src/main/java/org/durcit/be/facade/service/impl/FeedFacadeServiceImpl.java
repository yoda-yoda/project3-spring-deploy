package org.durcit.be.facade.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.durcit.be.facade.dto.UserInfoResponse;
import org.durcit.be.facade.service.FeedFacadeService;
import org.durcit.be.follow.service.MemberFollowService;
import org.durcit.be.post.service.PostService;
import org.durcit.be.security.service.ProfileService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true)
public class FeedFacadeServiceImpl implements FeedFacadeService {

    private final ProfileService profileService;
    private final PostService postService;
    private final MemberFollowService memberFollowService;

    public UserInfoResponse getUserInfo(Long userId) {
        return UserInfoResponse.builder()
                .userInfo(profileService.getMemberProfile(userId))
                .postCount((long) postService.getMyPosts(userId).size())
                .followerCount((long) memberFollowService.getFollowers(userId).size())
                .followingCount((long) memberFollowService.getFollowees(userId).size())
                .build();
    }

}
