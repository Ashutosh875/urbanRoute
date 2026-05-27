package com.urbanroute.service;

import com.urbanroute.dto.request.UserLoginRequestDto;
import com.urbanroute.dto.request.UserRegisterRequestDto;
import com.urbanroute.entity.User;
import com.urbanroute.enums.Role;
import com.urbanroute.exception.EmailAlreadyExistsException;
import com.urbanroute.repository.UserRepository;
import com.urbanroute.security.JwtUtil;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder encoder;
    private final UserDetailsService userDetailsService;
    private final JwtUtil jwtUtil;
    private final AuthenticationManager manager;

    @Transactional
    public String register(UserRegisterRequestDto registerDto){

        if(userRepository.findByEmail(registerDto.getEmail()).isPresent()){
            throw new EmailAlreadyExistsException("Email is Already registered");
        }

        String password = encoder.encode(registerDto.getPassword());
        User user = new User();
        user.setName(registerDto.getName());
        user.setEmail(registerDto.getEmail());
        user.setPassword(password);
        user.setRole(Role.ROLE_USER);

        userRepository.save(user);

        return "Registered SuccessFully";

    }

    public String login(UserLoginRequestDto loginDto){

        manager.authenticate(
                new UsernamePasswordAuthenticationToken(loginDto.getEmail() , loginDto.getPassword())
        );

        UserDetails user = userDetailsService.loadUserByUsername(loginDto.getEmail());

        return jwtUtil.generateToken(user);
    }
}
