package com.urbanroute.service;

import com.urbanroute.dto.request.StopRequestDto;
import com.urbanroute.dto.response.StopDto;
import com.urbanroute.entity.Stop;
import com.urbanroute.exception.ResourceNotFoundException;
import com.urbanroute.repository.StopRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StopService {

    private final StopRepository stopRepository;

    @Transactional
    public StopDto saveStop(StopRequestDto stopRequestDto){

        Stop stop = StopRequestDto.toEntity(stopRequestDto);
        Stop savedStop = stopRepository.save(stop);

        return StopDto.toResponse(savedStop);
    }

    public List<StopDto> fetchAllStops(){

        return stopRepository.findAll()
                .stream()
                .map(StopDto::toResponse)
                .collect(Collectors.toList());
    }


    public StopDto fetchStop(Long id){

        Stop stop = stopRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No stop found with Id: " + id));

        return StopDto.toResponse(stop);
    }

    @Transactional
    public void deleteStop(Long id){

        Stop stop = stopRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No stop found with Id: " + id));

        stopRepository.delete(stop);
    }
}
