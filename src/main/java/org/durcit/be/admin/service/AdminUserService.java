package org.durcit.be.admin.service;

import org.durcit.be.admin.dto.AdminUserResponse;

import java.util.List;

public interface AdminUserService {

    public void userBlock(Long memberId);
    public void userUnblock(Long memberId);
    public void userDeletePermanent(Long memberId);
    public List<AdminUserResponse> getAllMembers();
}
