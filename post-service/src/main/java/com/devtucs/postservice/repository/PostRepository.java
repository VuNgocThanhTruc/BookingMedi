package com.devtucs.postservice.repository;

import com.devtucs.postservice.dto.response.PostResponse;
import com.devtucs.postservice.entity.Post;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends MongoRepository<Post, String> {
    Post findByUserId(String userId);
    List<Post> findAllByUserId(String userId);
}
