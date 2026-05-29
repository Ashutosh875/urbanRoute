package com.urbanroute.graph;

import java.util.List;
import java.util.Map;

public interface RouteStrategy {

    List<RouteEdge> findPath(Long fromId , Long toId , Map<Long ,List<RouteEdge>> graph);
}
