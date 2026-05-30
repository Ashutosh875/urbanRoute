package com.urbanroute.dto.response;

import com.urbanroute.enums.TransportMode;
import com.urbanroute.graph.RouteEdge;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RouteSegment {

    String from;
    String to;
    Double cost;
    Integer duration;
    TransportMode transportMode;

    public static RouteSegment routeEdgeToSegment(RouteEdge edge){

        RouteSegment segment = new RouteSegment();

        segment.setFrom(edge.getFromStopName());
        segment.setTo(edge.getToStopName());
        segment.setCost(edge.getCostInRupees());
        segment.setDuration(edge.getDurationInMinutes());
        segment.setTransportMode(edge.getTransportMode());

        return segment;

    }

}
