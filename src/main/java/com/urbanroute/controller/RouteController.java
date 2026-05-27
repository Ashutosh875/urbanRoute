package com.urbanroute.controller;

import com.urbanroute.dto.request.RouteRequest;
import com.urbanroute.dto.response.RouteDto;
import com.urbanroute.service.RouteService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/routes")
@RequiredArgsConstructor
public class RouteController {

    private final RouteService routeService;

    @PostMapping
    public ResponseEntity<RouteDto> postRoute(@Valid @RequestBody RouteRequest routeRequest){

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(routeService.postRoute(routeRequest));
    }

    @GetMapping
    public ResponseEntity<List<RouteDto>> getAllRoutes(){

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(routeService.fetchAllRoutes());
    }

    @GetMapping("/{id}")
    public ResponseEntity<RouteDto> getRouteById(@PathVariable Long id){

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(routeService.fetchRoute(id));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<Void> deleteRouteById(@PathVariable Long id){

        routeService.deleteRoute(id);

        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .build();
    }
}
