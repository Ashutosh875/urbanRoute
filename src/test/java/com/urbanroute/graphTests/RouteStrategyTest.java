package com.urbanroute.graphTests;

import com.urbanroute.enums.TransportMode;
import com.urbanroute.graph.CheapestRouteStrategy;
import com.urbanroute.graph.FastestRouteStrategy;
import com.urbanroute.graph.RouteEdge;
import com.urbanroute.graph.ShortestRouteStrategy;
import org.junit.jupiter.api.Test;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.*;

public class RouteStrategyTest {

    private final ShortestRouteStrategy shortestRouteStrategy = new ShortestRouteStrategy();
    private final FastestRouteStrategy fastestRouteStrategy = new FastestRouteStrategy();
    private final CheapestRouteStrategy cheapestRouteStrategy = new CheapestRouteStrategy();

    private Map<Long, List<RouteEdge>> buildTestGraph() {
        Map<Long, List<RouteEdge>> graph = new HashMap<>();

        RouteEdge edge1to2 = new RouteEdge(100.0, 50, "Stop1", 2L, "Stop2", TransportMode.BUS);
        RouteEdge edge1to3 = new RouteEdge(10.0, 5, "Stop1", 3L, "Stop3", TransportMode.METRO);
        RouteEdge edge3to2 = new RouteEdge(10.0, 5, "Stop3", 2L, "Stop2", TransportMode.WALK);

        /*
            SHORTEST: 1→2 direct (1 hop , 50 minutes , 100rs)
            FASTEST: 1→3→2 (2 hops , 10 minutes , 20rs)
            CHEAPEST: 1→3→2 (2 hops , 10 minutes , 20rs)
         */

        graph.put(1L, List.of(edge1to2, edge1to3));
        graph.put(3L, List.of(edge3to2));

        return graph;
    }

    @Test
    void findPath_shouldReturnDirectPath_whenShortestMode(){

        Map<Long , List<RouteEdge>> graph = buildTestGraph();

        List<RouteEdge> result = shortestRouteStrategy.findPath(1L , 2L , graph);

        assertEquals(1 , result.size());
        assertEquals(2L , result.getFirst().getToStopId());
    }

    @Test
    void findPath_shouldReturnFastestPath_whenFastestMode(){

        Map<Long , List<RouteEdge>> graph = buildTestGraph();

        List<RouteEdge> result = fastestRouteStrategy.findPath(1L , 2L , graph);

        assertEquals(2 , result.size());
        assertEquals(2L , result.getLast().getToStopId());
    }

    @Test
    void findPath_shouldReturnCheapestPath_whenCheapestMode(){

        Map<Long , List<RouteEdge>> graph = buildTestGraph();

        List<RouteEdge> result = cheapestRouteStrategy.findPath(1L , 2L , graph);

        assertEquals(2 , result.size());
        assertEquals(2L , result.getLast().getToStopId());
    }

    @Test
    void findPath_shouldReturnEmptyList_whenNoPathExists(){
        Map<Long, List<RouteEdge>> graph = buildTestGraph();

        List<RouteEdge> result = shortestRouteStrategy.findPath(1L, 99L, graph);

        assertTrue(result.isEmpty());
    }

}
