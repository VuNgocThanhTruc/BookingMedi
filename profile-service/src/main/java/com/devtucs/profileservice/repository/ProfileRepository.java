package com.devtucs.profileservice.repository;

import com.devtucs.profileservice.dto.request.ProfileRequest;
import com.devtucs.profileservice.entity.Profile;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfileRepository extends Neo4jRepository<Profile, String> {
    Profile findByUserId(String userId);
}
