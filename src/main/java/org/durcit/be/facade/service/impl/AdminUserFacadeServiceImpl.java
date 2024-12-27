package org.durcit.be.facade.service.impl;

import lombok.RequiredArgsConstructor;
import org.durcit.be.chat.service.ChatService;
import org.durcit.be.comment.service.CommentService;
import org.durcit.be.facade.service.AdminUserFacadeService;
import org.durcit.be.facade.service.PostFacadeService;
import org.durcit.be.follow.service.MemberFollowService;
import org.durcit.be.postsTag.service.PostsTagService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AdminUserFacadeServiceImpl implements AdminUserFacadeService {

    public void deleteUser(Long memberId) {

    }

}
