package com.urbanroute.graph;

import java.util.List;
import java.util.Map;

public class RouteSearchContext {

    private RouteStrategy strategy;

    public void setStrategy(String mode){

        this.strategy = switch(mode){
            case "SHORTEST" -> new ShortestRouteStrategy();
            case "FASTEST" -> new FastestRouteStrategy();
            case "CHEAPEST" -> new CheapestRouteStrategy();
            default -> throw new IllegalArgumentException("Invalid mode: " + mode);
        };
    }

    public List<RouteEdge> execute(Long fromId , Long toId , Map<Long , List<RouteEdge>> graph){
        return strategy.findPath(fromId , toId ,graph);
    }
}
