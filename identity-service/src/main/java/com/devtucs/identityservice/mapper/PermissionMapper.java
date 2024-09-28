package com.devtucs.identityservice.mapper;

import com.devtucs.identityservice.dto.request.PermissionRequest;
import com.devtucs.identityservice.dto.response.PermissionResponse;
import com.devtucs.identityservice.entity.Permission;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface PermissionMapper {
    Permission toPermission(PermissionRequest request);

    PermissionResponse toPermissionResponse(Permission permission);

    void toUpdatePermission(@MappingTarget Permission permission, PermissionRequest request);
}
