package org.durcit.be.upload.service.Impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.durcit.be.security.domian.Member;
import org.durcit.be.security.repository.MemberRepository;
import org.durcit.be.security.service.MemberService;
import org.durcit.be.security.util.SecurityUtil;
import org.durcit.be.system.exception.upload.S3UploadException;
import org.durcit.be.upload.dto.ProfileImageRequest;
import org.durcit.be.upload.service.ProfileUploadService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

import java.io.IOException;
import java.util.UUID;

import static org.durcit.be.system.exception.ExceptionMessage.S3_UPLOAD_ERROR;
import static org.durcit.be.upload.util.UploadUtil.validateFileSize;

@Service
@Transactional
@Slf4j
@RequiredArgsConstructor
public class S3ProfileUploadServiceImpl implements ProfileUploadService {

    private final S3Client s3Client;
    private final MemberService memberService;
    private final MemberRepository memberRepository;

    @Value("${custom.s3.bucket-name:burcit}")
    private String bucketName;

    private static final String PROFILE_IMAGE_FOLDER = "profile-images/";

    public String updateProfileImage(ProfileImageRequest profileImageRequest) {
        if (profileImageRequest == null || profileImageRequest.getProfileImage() == null) {
            log.warn("No profile image provided, generating a random profile image.");
            return null;
        }

        MultipartFile profileImage = profileImageRequest.getProfileImage();
        validateFileSize(profileImage);

        try {
            String uniqueFileName = PROFILE_IMAGE_FOLDER + UUID.randomUUID() + "_" + profileImage.getOriginalFilename();

            s3Client.putObject(
                    PutObjectRequest.builder()
                            .bucket(bucketName)
                            .key(uniqueFileName)
                            .contentType(profileImage.getContentType())
                            .build(),
                    RequestBody.fromInputStream(profileImage.getInputStream(), profileImage.getSize())
            );


            String s3Url = String.format("https://%s.s3.%s.amazonaws.com/%s",
                    bucketName, Region.of("ap-northeast-2").id(), uniqueFileName);

            Member member = memberService.getById(SecurityUtil.getCurrentMemberId());
            member.setProfileImage(s3Url);

            return s3Url;

        } catch (IOException e) {
            log.error("Failed to upload profile image", e);
            throw new S3UploadException(S3_UPLOAD_ERROR);
        }
    }

    @Override
    public String uploadProfileReturnUrl(ProfileImageRequest profileImageRequest, String email) {
        if (profileImageRequest == null || profileImageRequest.getProfileImage() == null) {
            log.warn("No profile image provided, generating a random profile image.");
            return null;
        }

        MultipartFile profileImage = profileImageRequest.getProfileImage();
        validateFileSize(profileImage);

        try {
            String uniqueFileName = PROFILE_IMAGE_FOLDER + UUID.randomUUID() + "_" + profileImage.getOriginalFilename();

            s3Client.putObject(
                    PutObjectRequest.builder()
                            .bucket(bucketName)
                            .key(uniqueFileName)
                            .contentType(profileImage.getContentType())
                            .build(),
                    RequestBody.fromInputStream(profileImage.getInputStream(), profileImage.getSize())
            );


            String s3Url = String.format("https://%s.s3.%s.amazonaws.com/%s",
                    bucketName, Region.of("ap-northeast-2").id(), uniqueFileName);

            return s3Url;

        } catch (IOException e) {
            log.error("Failed to upload profile image", e);
            throw new S3UploadException(S3_UPLOAD_ERROR);
        }
    }
}
