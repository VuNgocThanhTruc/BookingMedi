package com.devtucs.postservice.controller;

import com.devtucs.postservice.dto.request.PostRequest;
import com.devtucs.postservice.dto.response.ApiResponse;
import com.devtucs.postservice.dto.response.PostResponse;
import com.devtucs.postservice.service.PostService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PostController {
    PostService postService;

    @GetMapping
    public ApiResponse<List<PostResponse>> getPosts(){
        return ApiResponse.<List<PostResponse>>builder()
                .result(postService.getPosts())
                .build();
    }

    @GetMapping("{idPost}")
    public ApiResponse<PostResponse> getPost(@PathVariable String idPost){
        return ApiResponse.<PostResponse>builder()
                .result(postService.getPost(idPost))
                .build();
    }

    @GetMapping("/my-post")
    public ApiResponse<List<PostResponse>> getPostsByUserId(){
        return ApiResponse.<List<PostResponse>>builder()
                .result(postService.getPostsByUserId())
                .build();
    }

    @PostMapping("/create")
    public ApiResponse<PostResponse> create(@RequestBody PostRequest request){
        return ApiResponse.<PostResponse>builder()
                .result(postService.create(request))
                .build();
    }

    @PutMapping("/{idPost}")
    public ApiResponse<PostResponse> update(@PathVariable String idPost, @RequestBody PostRequest request){

        return ApiResponse.<PostResponse>builder()
                .result(postService.update(idPost, request))
                .build();
    }

    @DeleteMapping("/{idPost}")
    public ApiResponse<String> delete(@PathVariable String idPost){
        postService.delete(idPost);
        return ApiResponse.<String>builder()
                .result("Delete successfully!")
                .build();
    }
}
