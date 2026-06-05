package com.urbanroute.controller;

import com.urbanroute.dto.response.UserDto;
import com.urbanroute.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/users")
@RequiredArgsConstructor
public class AdminController {

    private final AdminService adminService;

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<UserDto>> getAllUsers(){

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(adminService.fetchAllUsers());
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id){

        adminService.deleteUser(id);

        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .build();
    }
}
