package com.urbanroute.controller;

import com.urbanroute.dto.request.StopRequestDto;
import com.urbanroute.dto.response.StopDto;
import com.urbanroute.service.StopService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/stops")
@RequiredArgsConstructor
public class StopController {

    private final StopService stopService;

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<StopDto> postStop(@Valid @RequestBody StopRequestDto stopRequestDto){

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(stopService.saveStop(stopRequestDto));

    }

    @GetMapping
    public ResponseEntity<List<StopDto>> getAllStops(){

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(stopService.fetchAllStops());
    }

    @GetMapping("/{id}")
    public ResponseEntity<StopDto> getStopById(@PathVariable Long id){

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(stopService.fetchStop(id));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteStop(@PathVariable Long id){

        stopService.deleteStop(id);

        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .build();
    }
}
