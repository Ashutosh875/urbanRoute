package com.urbanroute.service;

import com.urbanroute.dto.response.UserDto;
import com.urbanroute.entity.User;
import com.urbanroute.exception.ResourceNotFoundException;
import com.urbanroute.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminService {

    private final UserRepository userRepository;

    public List<UserDto> fetchAllUsers(){

        return userRepository.findAll()
                .stream()
                .map(UserDto::toDto)
                .toList();
    }

    public void deleteUser(Long id){

        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("no user found with id: " + id));
        userRepository.delete(user);
    }
}
