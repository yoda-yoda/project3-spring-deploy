package org.durcit.be.facade.controller;

import lombok.RequiredArgsConstructor;
import org.durcit.be.facade.dto.UserInfoResponse;
import org.durcit.be.facade.service.FeedFacadeService;
import org.durcit.be.system.response.ResponseCode;
import org.durcit.be.system.response.ResponseData;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/feeds")
@RequiredArgsConstructor
public class FeedFacadeController {

    private final FeedFacadeService feedFacadeService;

    @GetMapping("/users/{userId}")
    public ResponseEntity<ResponseData<UserInfoResponse>> userInfo(@PathVariable Long userId) {
        UserInfoResponse userInfo = feedFacadeService.getUserInfo(userId);
        return ResponseData.toResponseEntity(ResponseCode.GET_USER_PROFILE_SUCCESS, userInfo);
    }

}
