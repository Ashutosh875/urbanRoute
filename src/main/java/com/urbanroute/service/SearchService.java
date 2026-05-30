package com.urbanroute.service;

import com.urbanroute.dto.response.RouteSegment;
import com.urbanroute.dto.response.SearchResult;
import com.urbanroute.entity.Stop;
import com.urbanroute.exception.NoPathFoundException;
import com.urbanroute.exception.ResourceNotFoundException;
import com.urbanroute.graph.RouteEdge;
import com.urbanroute.graph.RouteSearchContext;
import com.urbanroute.repository.StopRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SearchService {

    private final StopRepository stopRepository;
    private  final GraphService graphService;

    public SearchResult search(Long fromId , Long toId , String mode){

        Stop fromStop = stopRepository.findById(fromId)
                .orElseThrow(() -> new ResourceNotFoundException("No stop found with id: " + fromId));

        Stop toStop = stopRepository.findById(toId)
                .orElseThrow(() -> new ResourceNotFoundException("No stop found with id: " + toId));

        RouteSearchContext searchContext = new RouteSearchContext();
        searchContext.setStrategy(mode);
        List<RouteEdge> searchResult = searchContext.execute(fromId , toId , graphService.getGraph());


        String fromName = fromStop.getName();
        String toName = toStop.getName();

        if(searchResult.isEmpty()) {
            throw new NoPathFoundException("No Path Found between " + fromName + " and " + toName);
        }

        Integer totalStops = searchResult.size();

        Double totalCost = searchResult
                .stream()
                .mapToDouble(RouteEdge::getCostInRupees)
                .sum();

        Integer totalDuration = searchResult
                .stream()
                .mapToInt(RouteEdge::getDurationInMinutes)
                .sum();

        List<RouteSegment> routeSegments = searchResult
                .stream()
                .map(RouteSegment::routeEdgeToSegment)
                .toList();

        SearchResult result = new SearchResult();
        result.setFrom(fromName);
        result.setTo(toName);
        result.setMode(mode);
        result.setTotalCost(totalCost);
        result.setTotalDuration(totalDuration);
        result.setTotalStops(totalStops);
        result.setRoutes(routeSegments);

        return result;
    }
}
