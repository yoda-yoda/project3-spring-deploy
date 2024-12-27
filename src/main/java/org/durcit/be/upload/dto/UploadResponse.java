package org.durcit.be.upload.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UploadResponse {

    private Long id;
    private String url;
    private String originalFilename;

    @Builder
    public UploadResponse(Long id, String url, String originalFilename) {
        this.id = id;
        this.url = url;
        this.originalFilename = originalFilename;
    }
}
