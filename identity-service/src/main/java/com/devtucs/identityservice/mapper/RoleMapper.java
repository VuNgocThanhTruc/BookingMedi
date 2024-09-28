package com.devtucs.identityservice.mapper;

import com.devtucs.identityservice.dto.request.RoleRequest;
import com.devtucs.identityservice.dto.response.RoleResponse;
import com.devtucs.identityservice.entity.Role;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface RoleMapper {
    @Mapping(target = "permissions", ignore = true)
    Role toRole(RoleRequest request);

    RoleResponse toRoleResponse(Role role);

}
