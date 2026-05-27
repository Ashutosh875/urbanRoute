package com.urbanroute.service;

import com.urbanroute.dto.request.RouteRequest;
import com.urbanroute.dto.response.RouteDto;
import com.urbanroute.entity.Route;
import com.urbanroute.entity.Stop;
import com.urbanroute.entity.User;
import com.urbanroute.enums.Role;
import com.urbanroute.exception.ResourceNotFoundException;
import com.urbanroute.repository.RouteRepository;
import com.urbanroute.repository.StopRepository;
import com.urbanroute.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RouteService {

    private final RouteRepository routeRepository;
    private final StopRepository stopRepository;
    private final UserRepository userRepository;

    public Stop findStopById(Long id){
        return stopRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No stop found with id: " + id));
    }

    public User getCurrentUser(){

        String email = SecurityContextHolder
                        .getContext()
                        .getAuthentication()
                        .getName();

        return userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("No user found with email: " + email));
    }

    public RouteDto postRoute(RouteRequest routeRequest){

        Route route = new Route();
        route.setFromStop(findStopById(routeRequest.getFromStopId()));
        route.setToStop(findStopById(routeRequest.getToStopId()));
        route.setTransportMode(routeRequest.getTransportMode());
        route.setCostInRupees(routeRequest.getCostInRupees());
        route.setDurationInMinutes(routeRequest.getDurationInMinutes());
        route.setContributedBy(getCurrentUser());

        Route savedRoute = routeRepository.save(route);

        return RouteDto.toDto(savedRoute);
    }

    public List<RouteDto> fetchAllRoutes(){

        return routeRepository.findAll()
                .stream()
                .map(RouteDto::toDto)
                .collect(Collectors.toList());
    }

    public RouteDto fetchRoute(Long id){

        Route route = routeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No Route found"));

        return RouteDto.toDto(route);
    }

    public void deleteRoute(Long id) {

        Route route = routeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No Route found"));

        User currentUser = getCurrentUser();

        if(currentUser.getRole().equals(Role.ROLE_ADMIN) ||
                currentUser.getEmail().equals(route.getContributedBy().getEmail())){
            routeRepository.delete(route);
        } else throw new AccessDeniedException("No Access to delete this route");
    }

}
