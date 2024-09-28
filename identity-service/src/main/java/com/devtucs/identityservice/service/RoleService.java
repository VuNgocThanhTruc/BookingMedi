package com.devtucs.identityservice.service;

import com.devtucs.identityservice.dto.request.RoleRequest;
import com.devtucs.identityservice.dto.response.RoleResponse;
import com.devtucs.identityservice.entity.Role;
import com.devtucs.identityservice.mapper.RoleMapper;
import com.devtucs.identityservice.repository.PermissionRepository;
import com.devtucs.identityservice.repository.RoleRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class RoleService {
    RoleRepository roleRepository;
    RoleMapper roleMapper;
    PermissionRepository permissionRepository;

    public List<RoleResponse> getAll() {
        return roleRepository.findAll().stream()
                .map(roleMapper::toRoleResponse).toList();
    }

    public RoleResponse delete(Role role){
        return roleMapper.toRoleResponse(
                roleRepository.deleteByName(role.getName()));
    }

    public RoleResponse create(RoleRequest request) {
        Role role = roleMapper.toRole(request);

        var permissions = permissionRepository.findAllById(request.getPermissions());
        role.setPermissions(new HashSet<>(permissions));

        return roleMapper.toRoleResponse(roleRepository.save(role));
    }
}
