package com.urbanroute.graph;

import java.util.*;

public class CheapestRouteStrategy implements RouteStrategy{

    record CheapPathEntry(Double totalWeight , List<RouteEdge> path) {}

    @Override
    public List<RouteEdge> findPath(Long fromId, Long toId, Map<Long, List<RouteEdge>> graph) {

        PriorityQueue<CheapPathEntry> queue =
                new PriorityQueue<>(Comparator.comparingDouble(CheapPathEntry::totalWeight));

        Map<Long , Double> weightMap = new HashMap<>();

        queue.offer(new CheapPathEntry(0.0 , new ArrayList<>()));
        weightMap.put(fromId , 0.0);

        while (!queue.isEmpty()){

            CheapPathEntry currEntry = queue.poll();

            Long currNode = currEntry.path().isEmpty()
                    ?fromId
                    : currEntry.path().getLast().getToStopId();

            if(currNode.equals(toId)){
                return currEntry.path();
            }
            List<RouteEdge> neighbourEdges = graph.getOrDefault(currNode ,  Collections.emptyList());

            for(RouteEdge edge : neighbourEdges){
                Double totalWeight = currEntry.totalWeight() + edge.getCostInRupees();

                if(totalWeight < weightMap.getOrDefault(edge.getToStopId() , Double.MAX_VALUE)){
                    List<RouteEdge> newPath = new ArrayList<>(currEntry.path());
                    newPath.add(edge);
                    weightMap.put(edge.getToStopId(), totalWeight);
                    queue.offer(new CheapPathEntry(totalWeight , newPath));
                }
            }
        }
        return Collections.emptyList();
    }
}
