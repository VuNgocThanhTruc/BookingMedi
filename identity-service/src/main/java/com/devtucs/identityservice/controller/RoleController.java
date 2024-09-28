package com.devtucs.identityservice.controller;

import com.devtucs.identityservice.dto.request.RoleRequest;
import com.devtucs.identityservice.dto.response.ApiResponse;
import com.devtucs.identityservice.dto.response.RoleResponse;
import com.devtucs.identityservice.entity.Role;
import com.devtucs.identityservice.service.RoleService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/roles")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class RoleController {
    RoleService roleService;

    @GetMapping
    public ApiResponse<List<RoleResponse>> getRoles(){

        return ApiResponse.<List<RoleResponse>>builder()
                .result(roleService.getAll())
                .build();
    }

    @PostMapping
    public ApiResponse<RoleResponse> createRole(@RequestBody RoleRequest request){

        return ApiResponse.<RoleResponse>builder()
                .result(roleService.create(request))
                .build();
    }

    @DeleteMapping( "/{role}")
    public ApiResponse<RoleResponse> deleteRole(@PathVariable Role role){

        return ApiResponse.<RoleResponse>builder()
                .result(roleService.delete(role))
                .build();
    }
}
