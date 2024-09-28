package com.devtucs.identityservice.repository;

import com.devtucs.identityservice.entity.Permission;
import com.devtucs.identityservice.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface RoleRepository extends JpaRepository<Role, String> {
    Role deleteByName(String name);
}
