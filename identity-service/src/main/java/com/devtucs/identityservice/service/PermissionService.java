package com.devtucs.identityservice.service;

import com.devtucs.identityservice.dto.request.PermissionRequest;
import com.devtucs.identityservice.dto.response.PermissionResponse;
import com.devtucs.identityservice.entity.Permission;
import com.devtucs.identityservice.mapper.PermissionMapper;
import com.devtucs.identityservice.repository.PermissionRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PermissionService {
    PermissionRepository permissionRepository;
    PermissionMapper permissionMapper;

    public List<PermissionResponse> getAll() {
        return permissionRepository.findAll().stream()
                .map(permissionMapper::toPermissionResponse).toList();
    }

    public void delete(String request) {
        permissionRepository.deleteById(request);
    }

    public PermissionResponse create(PermissionRequest request) {
        Permission permission = permissionMapper.toPermission(request);

        return permissionMapper.toPermissionResponse(permissionRepository.save(permission));
    }
}
