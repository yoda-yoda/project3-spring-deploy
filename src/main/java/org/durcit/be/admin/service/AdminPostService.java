package org.durcit.be.admin.service;

import org.durcit.be.post.dto.PostResponse;

import java.util.List;

public interface AdminPostService {

    public List<PostResponse> getAllPosts();
    public List<PostResponse> getPostsDeleted();
    public void deletePostsAdmin(Long postId);
    public void recoverPostAndPostsTag(Long postId);
    public void deletePostsPermanently(Long postId);


}
