package org.durcit.be.facade.service;

import org.durcit.be.facade.dto.UserInfoResponse;

public interface FeedFacadeService {
    public UserInfoResponse getUserInfo(Long userId);
}
