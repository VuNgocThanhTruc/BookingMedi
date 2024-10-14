package com.devtucs.postservice.mapper;

import com.devtucs.postservice.dto.request.PostRequest;
import com.devtucs.postservice.dto.response.PostResponse;
import com.devtucs.postservice.entity.Post;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface PostMapper {
    Post toPost(PostRequest request);

    PostResponse toPostResponse(Post post);

    void toUpdatePost(@MappingTarget Post post, PostRequest request);
}
