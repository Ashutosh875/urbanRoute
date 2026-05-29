package com.urbanroute.graph;

import com.urbanroute.enums.TransportMode;

public class RouteEdge {

    private Long toStopId;
    private Double costInRupees;
    private Integer durationInMinutes;
    private TransportMode transportMode;
    private String fromStopName;
    private String toStopName;

    public RouteEdge(Double costInRupees, Integer durationInMinutes, String fromStopName, Long toStopId, String toStopName, TransportMode transportMode) {
        this.costInRupees = costInRupees;
        this.durationInMinutes = durationInMinutes;
        this.fromStopName = fromStopName;
        this.toStopId = toStopId;
        this.toStopName = toStopName;
        this.transportMode = transportMode;
    }

    public Double getCostInRupees() {
        return costInRupees;
    }

    public Integer getDurationInMinutes() {
        return durationInMinutes;
    }

    public String getFromStopName() {
        return fromStopName;
    }

    public Long getToStopId() {
        return toStopId;
    }

    public String getToStopName() {
        return toStopName;
    }

    public TransportMode getTransportMode() {
        return transportMode;
    }
}
