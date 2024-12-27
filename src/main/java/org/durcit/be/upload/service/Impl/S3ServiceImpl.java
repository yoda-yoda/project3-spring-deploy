package org.durcit.be.upload.service.Impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.durcit.be.post.service.PostService;
import org.durcit.be.system.exception.upload.ImageNotFoundException;
import org.durcit.be.system.exception.upload.S3UploadException;
import org.durcit.be.upload.domain.Images;
import org.durcit.be.upload.dto.UploadRequest;
import org.durcit.be.upload.dto.UploadResponse;
import org.durcit.be.upload.dto.UploadUpdateRequest;
import org.durcit.be.upload.repository.ImagesRepository;
import org.durcit.be.upload.service.UploadService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.core.async.AsyncRequestBody;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.*;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static org.durcit.be.system.exception.ExceptionMessage.*;
import static org.durcit.be.upload.util.UploadUtil.*;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Slf4j
public class S3ServiceImpl implements UploadService {

    private final S3Client s3Client;
    private final PostService postService;
    private final ImagesRepository imagesRepository;

    @Value("${custom.s3.bucket-name:burcit}")
    private String bucketName;


    public List<UploadResponse> getImagesByPostId(Long postId) {
        List<Images> images = imagesRepository.findAllByPostId(postId);

        return images.stream()
                .filter(image -> !image.isDeleted())
                .map(image -> UploadResponse.builder()
                        .url(image.getUrl())
                        .originalFilename(image.getOriginalFilename())
                        .id(image.getId())
                        .build())
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void upload(UploadRequest request) {
        uploadFiles(request.getPostId(), request.getFiles());
    }

    @Transactional
    public void deleteImages(List<Long> imageIds) {
        if (imageIds != null && !imageIds.isEmpty()) {
            for (Long imageId : imageIds) {
                Images existingImage = imagesRepository.findById(imageId)
                        .orElseThrow(() -> new ImageNotFoundException(IMAGE_NOT_FOUND_ERROR));
                existingImage.setDeleted(true);
                imagesRepository.save(existingImage);
            }
        }
    }

    @Transactional
    public void updateImages(UploadUpdateRequest request) {
        deleteImages(request.getImageIdsToDelete());
        if (request.getNewFiles() != null) {
            uploadFiles(request.getPostId(), request.getNewFiles());
        }
    }

    @Transactional
    protected void uploadFiles(Long postId, List<MultipartFile> files) {
        if (files != null && !files.isEmpty()) {
            for (MultipartFile file : files) {
                if (!file.isEmpty()) {
                    validateFileSize(file);
                    try {
                        String uniqueFileName = generateUniqueFileName(file.getOriginalFilename());

                        s3Client.putObject(PutObjectRequest.builder()
                                        .bucket(bucketName)
                                        .key(uniqueFileName)
                                        .contentType(file.getContentType())
                                        .build(),
                                RequestBody.fromInputStream(file.getInputStream(), file.getSize()));

                        String s3Url = String.format("https://%s.s3.%s.amazonaws.com/%s",
                                bucketName, Region.of("ap-northeast-2").id(), uniqueFileName);

                        Images image = Images.builder()
                                .post(postService.getById(postId))
                                .url(s3Url)
                                .originalFilename(file.getOriginalFilename())
                                .build();

                        imagesRepository.save(image);

                    } catch (IOException e) {
                        log.error("Failed to upload file: {}", file.getOriginalFilename(), e);
                        throw new S3UploadException(S3_UPLOAD_ERROR);
                    }
                }
            }
        }
    }

    private String generateUniqueFileName(String originalFilename) {
        String fileExtension = originalFilename.substring(originalFilename.lastIndexOf('.'));
        return UUID.randomUUID().toString() + fileExtension;
    }
}
