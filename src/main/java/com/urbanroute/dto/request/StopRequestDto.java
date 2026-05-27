package com.urbanroute.dto.request;

import com.urbanroute.entity.Stop;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StopRequestDto {

    @NotBlank(message = "name of the stop is required")
    private String name;

    @NotBlank(message = "name of the city is required")
    private String city;

    public static Stop toEntity(StopRequestDto requestDto){
        Stop stop = new Stop();
        stop.setName(requestDto.getName());
        stop.setCity(requestDto.getCity());

        return stop;
    }
}
