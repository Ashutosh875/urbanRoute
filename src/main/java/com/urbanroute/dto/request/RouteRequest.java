package com.urbanroute.dto.request;

import com.urbanroute.entity.Route;
import com.urbanroute.enums.TransportMode;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RouteRequest {

    @NotNull(message = "Stop id is required field")
    @Min(value = 0 , message = "stop id can not be negative")
    private Long fromStopId;

    @NotNull(message = "Stop id is required field")
    @Min(value = 0 , message = "stop id can not be negative")
    private Long toStopId;

    @NotNull(message = "transportation mode is required field")
    private TransportMode transportMode;

    @NotNull(message = "cost is required field")
    @Min(value = 0 , message = "Cost can't be less than 0")
    private Double costInRupees;

    @NotNull(message = "duration is required field")
    @Min(value = 0 , message = "Duration can't be less than 0")
    private Integer durationInMinutes;

}
