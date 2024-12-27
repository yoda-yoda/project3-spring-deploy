package org.durcit.be.admin.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.durcit.be.security.dto.KeyPair;
import org.durcit.be.security.dto.LoginRequest;
import org.durcit.be.security.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admins")
@Slf4j
public class AdminAuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<KeyPair> login(@RequestBody LoginRequest loginRequest) {
        KeyPair keyPair = authService.adminLogin(loginRequest);
        log.info("keyPair.getAccessToken() = {}", keyPair.getAccessToken());
        return ResponseEntity.ok(keyPair);
    }

}
