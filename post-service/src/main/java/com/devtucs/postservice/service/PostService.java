package com.devtucs.postservice.service;

import com.devtucs.postservice.dto.request.PostRequest;
import com.devtucs.postservice.dto.response.PostResponse;
import com.devtucs.postservice.entity.Post;
import com.devtucs.postservice.exception.AppException;
import com.devtucs.postservice.exception.ErrorCodeConstant;
import com.devtucs.postservice.mapper.PostMapper;
import com.devtucs.postservice.repository.PostRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PostService {
    PostRepository postRepository;
    PostMapper postMapper;

    public PostResponse getPost(String request) {
        Post post = postRepository.findById(request)
                .orElseThrow(() -> new AppException(ErrorCodeConstant.POST_NOT_FOUND));

        return postMapper.toPostResponse(post);
    }

    public PostResponse create(PostRequest request) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        Post post = Post.builder()
                .userId(authentication.getName())
                .price(request.getPrice())
                .title(request.getTitle())
                .content(request.getContent())
                .briefContent(request.getBriefContent())
                .image(request.getImage())
                .createdAt(Instant.now())
                .modifiedAt(Instant.now())
                .build();

        postMapper.toPost(request);

        post = postRepository.save(post);

        return postMapper.toPostResponse(post);
    }

    public PostResponse update(String id, PostRequest request) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCodeConstant.POST_NOT_FOUND));
        postMapper.toUpdatePost(post, request);

        return postMapper.toPostResponse(postRepository.save(post));
    }

    public void delete(String request) {
        Post post = postRepository.findById(request)
                .orElseThrow(() -> new AppException(ErrorCodeConstant.POST_NOT_FOUND));
        postRepository.delete(post);
    }

    public List<PostResponse> getPosts() {
        return postRepository.findAll().stream()
                .map(postMapper::toPostResponse).toList();
    }

    public List<PostResponse> getPostsByUserId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        return postRepository.findAllByUserId(authentication.getName())
                .stream()
                .map(postMapper::toPostResponse)
                .toList();
    }
}
