package com.urbanroute.dto.response;

import com.urbanroute.entity.Route;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RouteDto {

    private Long id;
    private String fromStop;
    private String toStop;
    private String transportMode;
    private Double costInRupees;
    private Integer durationInMinutes;
    private String contributedBy;

    public static RouteDto toDto(Route route){

        RouteDto routeDto = new RouteDto();

        routeDto.setId(route.getId());
        routeDto.setFromStop(route.getFromStop().getName());
        routeDto.setToStop(route.getToStop().getName());
        routeDto.setTransportMode(route.getTransportMode().name());
        routeDto.setCostInRupees(route.getCostInRupees());
        routeDto.setDurationInMinutes(route.getDurationInMinutes());
        routeDto.setContributedBy(route.getContributedBy().getName());

        return routeDto;
    }
}
