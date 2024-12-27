package org.durcit.be.admin.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.durcit.be.security.domian.Member;
import org.durcit.be.system.util.TimeAgoUtil;

@Data
@NoArgsConstructor
public class AdminUserResponse {

    private Long id;
    private String username;
    private String password;
    private String provider;
    private String email;
    private String role;
    private String nickname;
    private String profileImage;
    private String bio;
    private String signedAt;
    private boolean isVerified;
    private boolean isBlocked;

    @Builder
    public AdminUserResponse(Long id, String username, String password, String provider, String email, String role, String nickname, String profileImage, String bio, String signedAt, boolean isVerified, boolean isBlocked) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.provider = provider;
        this.email = email;
        this.role = role;
        this.nickname = nickname;
        this.profileImage = profileImage;
        this.bio = bio;
        this.signedAt = signedAt;
        this.isVerified = isVerified;
        this.isBlocked = isBlocked;
    }

    public static AdminUserResponse fromEntity(Member member) {
        return AdminUserResponse.builder()
                .id(member.getId())
                .username(member.getUsername())
                .password(member.getPassword())
                .provider(member.getProvider())
                .email(member.getEmail())
                .role(member.getRole())
                .nickname(member.getNickname())
                .profileImage(member.getProfileImage())
                .bio(member.getBio())
                .signedAt(TimeAgoUtil.formatElapsedTime(member.getSignedAt()))
                .isVerified(member.isVerified())
                .isBlocked(member.isBlocked())
                .build();
    }


}
