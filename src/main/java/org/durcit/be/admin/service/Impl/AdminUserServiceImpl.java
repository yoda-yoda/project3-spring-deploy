package org.durcit.be.admin.service.Impl;

import lombok.RequiredArgsConstructor;
import org.durcit.be.admin.dto.AdminUserResponse;
import org.durcit.be.admin.service.AdminUserService;
import org.durcit.be.facade.dto.UserInfoResponse;
import org.durcit.be.post.service.PostService;
import org.durcit.be.security.domian.Member;
import org.durcit.be.security.repository.MemberRepository;
import org.durcit.be.security.service.MemberService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class AdminUserServiceImpl implements AdminUserService {

    private final MemberService memberService;
    private final MemberRepository memberRepository;

    public List<AdminUserResponse> getAllMembers() {
        List<Member> members = memberRepository.findAll();
        return members.stream()
                .map(AdminUserResponse::fromEntity).toList();
    }

    @Transactional
    public void userBlock(Long memberId) {
        Member member = memberService.getByIdForAdmin(memberId);
        member.setBlocked(true);
        memberRepository.save(member);
    }

    @Transactional
    public void userUnblock(Long memberId) {
        Member member = memberService.getByIdForAdmin(memberId);
        member.setBlocked(false);
        memberRepository.save(member);
    }

    @Transactional
    public void userDeletePermanent(Long memberId) {
        Member member = memberService.getByIdForAdmin(memberId);
        memberRepository.delete(member);
    }





}
