package org.durcit.be.upload.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.durcit.be.system.response.ResponseCode;
import org.durcit.be.system.response.ResponseData;
import org.durcit.be.upload.dto.UploadRequest;
import org.durcit.be.upload.dto.UploadUpdateRequest;
import org.durcit.be.upload.service.UploadService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/members/upload")
@RequiredArgsConstructor
@Slf4j
public class UploadController {

    private final UploadService uploadService;

    @PostMapping(path = "/files", consumes = {"multipart/form-data"})
    public ResponseEntity<ResponseData> upload(
            @RequestParam(value = "postId", required = false) Long postId,
            @RequestParam(value = "files", required = false) List<MultipartFile> files) {
        log.info("postId = {}", postId);
        uploadService.upload(new UploadRequest(postId, files));
        return ResponseData.toResponseEntity(ResponseCode.UPLOAD_FILES_SUCCESS);
    }

    @PutMapping(path = "/update", consumes = {"multipart/form-data"})
    public ResponseEntity<ResponseData> updateImages(
            @RequestParam("postId") Long postId,
            @RequestParam(value = "imageIdsToDelete", required = false) List<Long> imagesIdsToDelete,
            @RequestParam(value = "newFiles", required = false) List<MultipartFile> newFiles
    ) {
        UploadUpdateRequest updateRequest = UploadUpdateRequest.builder()
                .postId(postId)
                .imageIdsToDelete(imagesIdsToDelete)
                .newFiles(newFiles)
                .build();
        uploadService.updateImages(updateRequest);
        return ResponseData.toResponseEntity(ResponseCode.UPDATE_FILES_SUCCESS);
    }



}
