package com.example.salles.Controller.auth;


import com.example.salles.Config.JwtService;
import com.example.salles.Entity.User;
import com.example.salles.Repository.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepo repo;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    public AuthenticationResponse register(RegisterRequest request) {
        var user = User.builder()
                .firstName(request.getFirstname())
                .lastName(request.getLastname())
                .phone(request.getPhone())
                .role(request.getRole())
                .enabled(request.getEnabled())
                .password(passwordEncoder.encode(request.getPassword()))
                .build();
        repo.save(user);

        var userDetails = new org.springframework.security.core.userdetails.User(
                user.getPhone(),
                user.getPassword(),
                new ArrayList<>()
        );
        var jwtToken = jwtService.generateToken(userDetails, String.valueOf(user.getRole()), UUID.fromString(String.valueOf(user.getId())));
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    public AuthenticationResponse authenticate(AuthenticateRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getPhone(),
                        request.getPassword()
                )
        );
        var user = repo.findByPhone(request.getPhone()).orElseThrow();

        var userDetails = new org.springframework.security.core.userdetails.User(
                user.getPhone(),
                user.getPassword(),
                new ArrayList<>()
        );
        var jwtToken = jwtService.generateToken(userDetails, String.valueOf(user.getRole()), UUID.fromString(String.valueOf(user.getId())));
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }
}
