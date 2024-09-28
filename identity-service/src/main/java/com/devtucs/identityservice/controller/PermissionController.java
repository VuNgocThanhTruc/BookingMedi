package com.devtucs.identityservice.controller;

import com.devtucs.identityservice.dto.request.PermissionRequest;
import com.devtucs.identityservice.dto.response.ApiResponse;
import com.devtucs.identityservice.dto.response.PermissionResponse;
import com.devtucs.identityservice.service.PermissionService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/permissions")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PermissionController {
    PermissionService permissionService;

    @GetMapping
    public ApiResponse<List<PermissionResponse>> getAll(){

        return ApiResponse.<List<PermissionResponse>>builder()
                .result(permissionService.getAll())
                .build();
    }

    @PostMapping
    public ApiResponse<PermissionResponse> create(@RequestBody PermissionRequest request){

        return ApiResponse.<PermissionResponse>builder()
                .result(permissionService.create(request))
                .build();
    }

    @DeleteMapping( "/{permissionName}")
    public ApiResponse<Void> delete(@PathVariable String permissionName){

        permissionService.delete(permissionName);
        return ApiResponse.<Void>builder().build();
    }
}
