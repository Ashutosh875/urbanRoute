package com.urbanroute.graph;

import java.util.*;

public class FastestRouteStrategy implements RouteStrategy{

    record FastPathEntry(Double totalWeight , List<RouteEdge> path) {}

    @Override
    public List<RouteEdge> findPath(Long fromId, Long toId, Map<Long, List<RouteEdge>> graph) {

        PriorityQueue<FastPathEntry> queue =
                new PriorityQueue<>(Comparator.comparingDouble(FastPathEntry::totalWeight));

        Map<Long , Double> weightMap = new HashMap<>();

        queue.offer(new FastPathEntry(0.0 , new ArrayList<>()));
        weightMap.put(fromId , 0.0);

        while (!queue.isEmpty()){

            FastPathEntry currEntry = queue.poll();

            Long currNode = currEntry.path().isEmpty()
                    ?fromId
                    : currEntry.path().getLast().getToStopId();

            if(currNode.equals(toId)){
                return currEntry.path();
            }
            List<RouteEdge> neighbourEdges = graph.getOrDefault(currNode ,  Collections.emptyList());

            for(RouteEdge edge : neighbourEdges){
                Double totalWeight = currEntry.totalWeight() + edge.getDurationInMinutes();

                if(totalWeight < weightMap.getOrDefault(edge.getToStopId() , Double.MAX_VALUE)){
                    List<RouteEdge> newPath = new ArrayList<>(currEntry.path());
                    newPath.add(edge);
                    weightMap.put(edge.getToStopId(), totalWeight);
                    queue.offer(new FastPathEntry(totalWeight , newPath));
                }
            }
        }
        return Collections.emptyList();
    }
}
