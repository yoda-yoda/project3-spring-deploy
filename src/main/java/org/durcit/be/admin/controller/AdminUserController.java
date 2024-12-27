package org.durcit.be.admin.controller;

import lombok.RequiredArgsConstructor;
import org.durcit.be.admin.dto.AdminUserResponse;
import org.durcit.be.admin.service.AdminUserService;
import org.durcit.be.system.response.ResponseCode;
import org.durcit.be.system.response.ResponseData;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/admins")
@RestController
@RequiredArgsConstructor
public class AdminUserController {

    private final AdminUserService adminUserService;

    @GetMapping("/users")
    public ResponseEntity<ResponseData<List<AdminUserResponse>>> getUsers() {
        List<AdminUserResponse> allMembers = adminUserService.getAllMembers();
        return ResponseData.toResponseEntity(ResponseCode.GET_USER_PROFILE_SUCCESS, allMembers);
    }

    @PutMapping("/block/{memberId}")
    public ResponseEntity<ResponseData> userBlock(@PathVariable("memberId") Long memberId) {
        adminUserService.userBlock(memberId);
        return ResponseData.toResponseEntity(ResponseCode.UPDATE_USER_BLOCK_STATUS);
    }

    @PutMapping("/unblock/{memberId}")
    public ResponseEntity<ResponseData> userUnblock(@PathVariable("memberId") Long memberId) {
        adminUserService.userUnblock(memberId);
        return ResponseData.toResponseEntity(ResponseCode.UPDATE_USER_BLOCK_STATUS);
    }

    @DeleteMapping("/delete/{memberId}")
    public ResponseEntity<ResponseData> userDelete(@PathVariable("memberId") Long memberId) {
        adminUserService.userDeletePermanent(memberId);
        return ResponseData.toResponseEntity(ResponseCode.DELETE_USER_PERMANENT_SUCCESS);
    }

}
