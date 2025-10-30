package com.petshop.api.service;

import com.petshop.api.dto.response.AuthResponseDTO;
import com.petshop.api.dto.request.LoginDTO;
import com.petshop.api.dto.request.RegisterDTO;
import com.petshop.api.model.entities.User;
import com.petshop.api.repository.UserRepository;
import com.petshop.api.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    @Transactional
    public AuthResponseDTO register(RegisterDTO registerDTO) {
        var user = new User();
        user.setEmail(registerDTO.getEmail());
        user.setName(registerDTO.getName());
        user.setPassword(passwordEncoder.encode(registerDTO.getPassword()));

        User savedUser = userRepository.save(user);


        var jwtToken = jwtService.generateToken(user);

        return AuthResponseDTO.builder()
                .acessToken(jwtToken)
                .build();
    }

    public AuthResponseDTO login(LoginDTO request){
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        var user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(()-> new BadCredentialsException("Invalid email or password"));

        var jwtToken = jwtService.generateToken(user);

        return AuthResponseDTO.builder()
                .acessToken(jwtToken)
                .build();
    }
}
