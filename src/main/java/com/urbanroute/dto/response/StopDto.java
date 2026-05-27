package com.urbanroute.dto.response;

import com.urbanroute.entity.Stop;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StopDto {

    private Long id;
    private String name;
    private String city;

    public static StopDto toResponse(Stop stop){

        StopDto stopDto = new StopDto();
        stopDto.setId(stop.getId());
        stopDto.setName(stop.getName());
        stopDto.setCity(stop.getCity());

        return stopDto;
    }
}
