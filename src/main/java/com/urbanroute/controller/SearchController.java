package com.urbanroute.controller;

import com.urbanroute.dto.response.SearchResult;
import com.urbanroute.service.SearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class SearchController {

    private final SearchService searchService;

    @GetMapping("/api/search")
    public ResponseEntity<SearchResult> search(@RequestParam Long from,
                                               @RequestParam Long to,
                                               @RequestParam String mode){

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(searchService.search(from , to , mode));
    }
}
