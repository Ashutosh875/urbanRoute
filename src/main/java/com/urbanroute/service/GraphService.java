package com.urbanroute.service;

import com.urbanroute.entity.Route;
import com.urbanroute.graph.RouteEdge;
import com.urbanroute.repository.RouteRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
@RequiredArgsConstructor
public class GraphService {

    private final RouteRepository routeRepository;

    private Map<Long,List<RouteEdge>> graph = new ConcurrentHashMap<>();

    public RouteEdge entityToRouteEdge(Route route){

        return new RouteEdge(
                route.getCostInRupees(),
                route.getDurationInMinutes(),
                route.getFromStop().getName(),
                route.getToStop().getId(),
                route.getToStop().getName(),
                route.getTransportMode()
        );
    }

    @PostConstruct
    public void buildGraph(){

        List<Route> routesList = routeRepository.findAll();

        routesList.forEach(route -> {
            RouteEdge routeEdge = entityToRouteEdge(route);
            graph.computeIfAbsent(route.getFromStop().getId() ,
                    k -> new ArrayList<>()).add(routeEdge);
        });

    }

    public Map<Long,List<RouteEdge>> getGraph(){
        return graph;
    }
}
