package com.urbanroute.graph;

import java.util.*;

public class ShortestRouteStrategy implements RouteStrategy{

    @Override
    public List<RouteEdge> findPath(Long fromId, Long toId, Map<Long, List<RouteEdge>> graph) {

        Queue<List<RouteEdge>> queue = new LinkedList<>();
        Set<Long> visited = new HashSet<>();

        queue.offer(new ArrayList<>());
        visited.add(fromId);

        while(!queue.isEmpty()){

            List<RouteEdge> currPath = queue.poll();

            Long currNode = currPath.isEmpty()
                    ? fromId
                    : currPath.getLast().getToStopId();

            if(toId.equals(currNode)){
                return currPath;
            }

            List<RouteEdge> neighbourEdges =
                    graph.getOrDefault(currNode , Collections.emptyList());

            for(RouteEdge edge : neighbourEdges){
                if(!visited.contains(edge.getToStopId())){
                    visited.add(edge.getToStopId());
                    List<RouteEdge> newPath = new ArrayList<RouteEdge>(currPath);
                    newPath.add(edge);
                    queue.offer(newPath);
                }
            }
        }

        return Collections.emptyList();
    }
}
