package com.urbanroute.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class SearchResult {

    String from;
    String to;
    String mode;
    Double totalCost;
    Integer totalDuration;
    Integer totalStops;
    List<RouteSegment> routes;
}
