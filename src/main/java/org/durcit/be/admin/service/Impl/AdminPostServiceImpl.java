package org.durcit.be.admin.service.Impl;

import lombok.RequiredArgsConstructor;
import org.durcit.be.admin.service.AdminPostService;
import org.durcit.be.post.dto.PostCardResponse;
import org.durcit.be.post.dto.PostResponse;
import org.durcit.be.post.service.PostService;
import org.durcit.be.postsTag.service.PostsTagService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AdminPostServiceImpl implements AdminPostService {

    private final PostService postService;
    private final PostsTagService postsTagService;


    public List<PostResponse> getAllPosts() {
        return postService.getAllPosts();
    }

    public List<PostResponse> getPostsDeleted() {
        return postService.getAllPostsDeleted();
    }

    @Transactional
    public void deletePostsAdmin(Long postId) {
        postService.deletePost(postId);
    }

    @Transactional
    public void deletePostsPermanently(Long postId) {
        postService.deletePostPermanently(postId);
    }


    @Transactional
    // 메서드 기능: PostId를 받아 해당 Post와 거기 담긴 Tag를 전부 delete false 설정한다.
    // 예외: 해당하는 Post가 없으면 예외를 던진다.
    // 반환: 작업이 끝난 Post를 PostCard 타입으로 변환후 반환한다.
    // 수정할것: 댓글 부분을 살리는 로직을 추가해야한다.
    public void recoverPostAndPostsTag(Long postId) {


        // 해당 Post를 delete false 처리한다.
        postService.recoverPost(postId);


        // 해당 Post의 Tag를 delete false 처리한다.
        // 태그가 아예 없으면 예외가 아닌 빈 List를 반환하는 메서드다.


        // PostCard 타입으로 변환하기위해, 해당 post를 반환받는다.
        // postService의 메서드를 이용했다.


    }

}
