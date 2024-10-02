package com.devtucs.identityservice.repository;

import com.devtucs.identityservice.entity.Profile;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfileRepository extends Neo4jRepository<Profile, String> {
    Profile findByUserId(String userId);
}
