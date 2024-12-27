package org.durcit.be.admin.controller;

import lombok.RequiredArgsConstructor;
import org.durcit.be.admin.service.AdminPostService;
import org.durcit.be.post.dto.PostCardResponse;
import org.durcit.be.post.dto.PostResponse;
import org.durcit.be.system.response.ResponseCode;
import org.durcit.be.system.response.ResponseData;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/admins/posts")
public class AdminPostController {

    private final AdminPostService adminPostService;

    // 메서드 기능: PostId를 받아 해당 Post와 거기 담긴 Tag를 전부 delete false 설정한다.
    // 예외: 해당하는 Post가 없으면 예외를 던진다.
    // 반환: 작업이 끝난 Post를 PostCard 타입으로 변환후 반환한다.
    // 수정할것: 댓글 부분을 살리는 로직을 추가해야한다.
    @PutMapping("/{postId}/restore")
    public ResponseEntity<ResponseData> recoverPost(@PathVariable Long postId) {

        adminPostService.recoverPostAndPostsTag(postId);

        return ResponseData.toResponseEntity(ResponseCode.RECOVER_POST_SUCCESS);

    }

    @GetMapping
    public ResponseEntity<ResponseData<List<PostResponse>>> getPostCardsForAdmin() {
        List<PostResponse> allPosts = adminPostService.getAllPosts();
        return ResponseData.toResponseEntity(ResponseCode.GET_POST_SUCCESS, allPosts);
    }

    @GetMapping("/hide")
    public ResponseEntity<ResponseData<List<PostResponse>>> getPostCardsDeleted() {
        List<PostResponse> allDeletedPosts = adminPostService.getPostsDeleted();
        return ResponseData.toResponseEntity(ResponseCode.GET_POST_SUCCESS, allDeletedPosts);
    }

    @DeleteMapping("/{postId}/hide")
    public ResponseEntity<ResponseData> hidePost(@PathVariable Long postId) {
        adminPostService.deletePostsAdmin(postId);
        return ResponseData.toResponseEntity(ResponseCode.DELETE_POST_SUCCESS);
    }

    @DeleteMapping("/{postId}/permanent")
    public ResponseEntity<ResponseData> permanentDeletePost(@PathVariable Long postId) {
        adminPostService.deletePostsPermanently(postId);
        return ResponseData.toResponseEntity(ResponseCode.DELETE_POST_SUCCESS);
    }

}
