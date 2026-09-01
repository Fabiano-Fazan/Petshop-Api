package com.petshop.api.config;

import com.petshop.api.model.entities.User;
import com.petshop.api.model.enums.UserRole;
import com.petshop.api.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class AdminUserInitializer implements ApplicationRunner {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Value("${application.admin.email:}")
    private String email;

    @Value("${application.admin.password:}")
    private String password;

    @Value("${application.admin.name:Administrator}")
    private String name;

    @Override
    @Transactional
    public void run(ApplicationArguments args) {
        if (email.isBlank() || password.isBlank() || userRepository.findByEmail(email).isPresent()) {
            return;
        }
        userRepository.save(User.builder()
                .name(name)
                .email(email)
                .password(passwordEncoder.encode(password))
                .role(UserRole.ADMIN)
                .build());
    }
}
